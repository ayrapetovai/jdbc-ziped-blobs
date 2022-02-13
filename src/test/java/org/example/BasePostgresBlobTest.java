package org.example;

import org.example.util.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.lang.RandomStringUtils;
import org.testcontainers.utility.DockerImageName;
import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.serialization.SerializationStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.example.util.TestResultRegistry.registerResult;

@Testcontainers
@ExtendWith(TestResultLoggerExtension.class)
@ExtendWith(MethodParameterResolver.class)
public abstract class BasePostgresBlobTest {

    @Container
    private final JdbcDatabaseContainer<?> postgresqlContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withDatabaseName("public")
            .withUsername("postgres")
            .withPassword("postgres")
            .withInitScript("init-script.sql");

    @ParameterizedTest
//    @ValueSource(ints = {2, 10, 100, 1_000, 100_000, 1_000_000})
    @ValueSource(ints = {2, 10, 100, 1_000, 100_000})
    public void writeAndRead(int collectionSize, TestContext ctx) throws Exception {
        assertTrue(postgresqlContainer.isRunning());

        long createStart = System.currentTimeMillis();
        List<DataDbo> writeData = createData(collectionSize);
        Duration createDuration = Duration.ofMillis(System.currentTimeMillis() - createStart);

        long startWrite = System.currentTimeMillis();
        int recordId = write(writeData);
        Duration writeDuration = Duration.ofMillis(System.currentTimeMillis() - startWrite);

        int writeDataSize = writeData.size();
        if (collectionSize >= 10) {
            writeData.clear();
        }

        long startRead = System.currentTimeMillis();
        List<DataDbo> readData = read(recordId);
        Duration readDuration = Duration.ofMillis(System.currentTimeMillis() - startRead);

        if (collectionSize < 10) {
            assertEquals(writeData, readData);
        }
        assertEquals(writeDataSize, readData.size());

        long loSize = retrieveLargeObjectSize(recordId);

        registerResult(ctx, collectionSize, createDuration, writeDuration, readDuration, loSize);
    }

    private List<DataDbo> createData(int size) {
        List<DataDbo> dataDboList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            dataDboList.add(new DataDbo(RandomStringUtils.random(10), i));
        }
        return dataDboList;
    }

    private int write(List<DataDbo> dataDboList) throws SQLException, IOException {
        final int recordId;
        try (Connection connection = DriverManager.getConnection(
                postgresqlContainer.getJdbcUrl(),
                postgresqlContainer.getUsername(),
                postgresqlContainer.getPassword())) {
            connection.setAutoCommit(false);

            try (LargeObject lob = createLargeObject(connection)) {
                recordId = insertRecord(connection, lob);
                try (OutputStream out = processingStrategy().preprocess(lob.getOutputStream())) {
                    serializationStrategy().serialize(out, dataDboList);
                }
            }

            connection.commit();
        }
        return recordId;
    }

    protected LargeObject createLargeObject(Connection connection) throws SQLException {
        LargeObjectManager lob = connection.unwrap(org.postgresql.PGConnection.class).getLargeObjectAPI();
        long oid = lob.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE); // Create a new large object
        return lob.open(oid, LargeObjectManager.WRITE); // Open the large object for writing
    }

    private int insertRecord(Connection connection, LargeObject obj) throws SQLException {
        final int id;
        try (PreparedStatement stmt = connection.prepareStatement(
                "insert into big_blobs(a_big_blob) values (?) returning id")) {
            stmt.setLong(1, obj.getLongOID());

            try (ResultSet resultSet = stmt.executeQuery()) {
                resultSet.next();
                id = resultSet.getInt("id");
            }
        }
        return id;
    }

    private List<DataDbo> read(int recordId) throws SQLException, IOException {
        final List<DataDbo> dataDboList;
        try (Connection connection = DriverManager.getConnection(
                postgresqlContainer.getJdbcUrl(),
                postgresqlContainer.getUsername(),
                postgresqlContainer.getPassword())) {
            connection.setAutoCommit(false);

            long lobId = selectLargeObjectId(connection, recordId);

            try (LargeObject lob = retrieveLargeObject(connection, lobId)) {
                try (InputStream in = processingStrategy().postprocess(lob.getInputStream())) {
                    dataDboList = serializationStrategy().deserialize(in);
                }
            }

            connection.commit();
        }

        return dataDboList;
    }

    private long selectLargeObjectId(Connection connection, int recordId) throws SQLException {
        final long oid;
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT a_big_blob FROM big_blobs where id = ?")) {
            stmt.setLong(1, recordId);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (!resultSet.next()) {
                    throw new IllegalStateException("no data");
                }
                oid = resultSet.getLong("a_big_blob");
            }
        }
        return oid;
    }

    private LargeObject retrieveLargeObject(Connection connection, long lobId) throws SQLException {
        // Get the Large Object Manager to perform operations with
        LargeObjectManager lob = connection.unwrap(org.postgresql.PGConnection.class).getLargeObjectAPI();
        return lob.open(lobId, LargeObjectManager.READ);
    }

    public abstract SerializationStrategy serializationStrategy();

    public abstract ProcessingStrategy processingStrategy();

    private long retrieveLargeObjectSize(int recordId) throws SQLException {
        try (Connection connection = DriverManager.getConnection(
                postgresqlContainer.getJdbcUrl(),
                postgresqlContainer.getUsername(),
                postgresqlContainer.getPassword())) {
            final long lobId = selectLargeObjectId(connection, recordId);

            try (PreparedStatement stmt = connection.prepareStatement(
                    "select pg_column_size(lo_get(?)) as lo_size from big_blobs")) {
                stmt.setLong(1, lobId);
                try (ResultSet resultSet = stmt.executeQuery()) {
                    resultSet.next();
                    return resultSet.getLong("lo_size");
                }
            }
        }
    }
}
