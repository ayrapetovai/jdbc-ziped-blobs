package org.example.strategy.serialization;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import org.example.DataDbo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class EntireJsonStrategy implements SerializationStrategy {

    private static final ObjectMapper om = new ObjectMapper();

    @Override
    public void serialize(OutputStream out, List<DataDbo> dataDboList) throws IOException {
        out.write(om.writeValueAsBytes(dataDboList));
    }

    @Override
    public List<DataDbo> deserialize(InputStream in) throws IOException {
        return om.readValue(in, CollectionType.construct(ArrayList.class, TypeBindings.emptyBindings(), SimpleType.constructUnsafe(Object.class), new JavaType[]{}, SimpleType.constructUnsafe(DataDbo.class)));
    }
}
