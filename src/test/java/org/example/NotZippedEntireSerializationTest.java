package org.example;

import org.example.strategy.process.NoprocessingStrategy;
import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.serialization.EntireJsonStrategy;
import org.example.strategy.serialization.SerializationStrategy;


public class NotZippedEntireSerializationTest extends BasePostgresBlobTest {

    @Override
    public SerializationStrategy serializationStrategy() {
        return new EntireJsonStrategy();
    }

    @Override
    public ProcessingStrategy processingStrategy() {
        return new NoprocessingStrategy();
    }
}
