package org.example.strategy.serialization;

import org.example.DataDbo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface SerializationStrategy {
    void serialize(OutputStream out, List<DataDbo> dataDboList ) throws IOException;

    List<DataDbo> deserialize(InputStream in) throws IOException;
}
