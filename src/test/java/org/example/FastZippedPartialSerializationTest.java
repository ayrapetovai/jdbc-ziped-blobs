package org.example;

import org.example.strategy.process.FastZippingStrategy;
import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.serialization.PartialJsonStrategy;
import org.example.strategy.serialization.SerializationStrategy;

public class FastZippedPartialSerializationTest extends BasePostgresBlobTest {
    @Override
    public SerializationStrategy serializationStrategy() {
        return new PartialJsonStrategy();
    }

    @Override
    public ProcessingStrategy processingStrategy() {
        return new FastZippingStrategy();
    }
}
