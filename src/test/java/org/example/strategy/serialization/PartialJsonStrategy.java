package org.example.strategy.serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.DataDbo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PartialJsonStrategy implements SerializationStrategy {
    private final ObjectMapper om = new ObjectMapper();

    @Override
    public void serialize(OutputStream out, List<DataDbo> dataDboList) throws IOException {
        out.write("[".getBytes());
        int dataCounter = 0;
        for (DataDbo dataDbo : dataDboList) {
            out.write(om.writeValueAsBytes(dataDbo));

            dataCounter++;
            if (dataCounter < dataDboList.size()) {
                out.write(",".getBytes());
            }
        }
        out.write("]".getBytes());
    }

    @Override
    public List<DataDbo> deserialize(InputStream in) throws IOException {
        List<DataDbo> dataDboList = new ArrayList<>();

        try (JsonParser jsonParser = JsonFactory.builder().build().createParser(in)) {
            jsonParser.setCodec(JsonMapper.builder().build());

            JsonToken token = jsonParser.nextToken();
            do {
                if (token.isStructStart()) {
                    jsonParser.nextToken(); // TODO check if it is '{'
                    DataDbo deserializedDataDbo = jsonParser.readValueAs(DataDbo.class);
                    dataDboList.add(deserializedDataDbo);
                    token = jsonParser.nextToken(); // TODO check
                }
            } while (!token.isStructEnd());
        }
        return dataDboList;
    }
}
