package org.example;

import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.process.ZippingStrategy;
import org.example.strategy.serialization.EntireJsonStrategy;
import org.example.strategy.serialization.SerializationStrategy;

public class ZippedEntireSerializationTest extends BasePostgresBlobTest {
    @Override
    public SerializationStrategy serializationStrategy() {
        return new EntireJsonStrategy();
    }

    @Override
    public ProcessingStrategy processingStrategy() {
        return new ZippingStrategy();
    }
}
