package org.example;

import org.junit.jupiter.api.Disabled;
import org.example.strategy.process.NoprocessingStrategy;
import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.serialization.PartialJsonStrategy;
import org.example.strategy.serialization.SerializationStrategy;

public class NotZippedPartialSerializationTest extends BasePostgresBlobTest {

    @Override
    public SerializationStrategy serializationStrategy() {
        return new PartialJsonStrategy();
    }

    @Override
    public ProcessingStrategy processingStrategy() {
        return new NoprocessingStrategy();
    }
}
