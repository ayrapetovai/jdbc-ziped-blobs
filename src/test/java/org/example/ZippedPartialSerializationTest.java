package org.example;

import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.serialization.PartialJsonStrategy;
import org.example.strategy.process.ZippingStrategy;
import org.example.strategy.serialization.SerializationStrategy;

public class ZippedPartialSerializationTest extends BasePostgresBlobTest {

    @Override
    public SerializationStrategy serializationStrategy() {
        return new PartialJsonStrategy();
    }

    @Override
    public ProcessingStrategy processingStrategy() {
        return new ZippingStrategy();
    }
}
