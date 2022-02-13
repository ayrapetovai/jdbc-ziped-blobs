package org.example;

import org.example.strategy.process.FastZippingStrategy;
import org.example.strategy.process.ProcessingStrategy;
import org.example.strategy.serialization.EntireJsonStrategy;
import org.example.strategy.serialization.SerializationStrategy;

public class FastZippedEntireSerializationTest extends BasePostgresBlobTest {
    @Override
    public SerializationStrategy serializationStrategy() {
        return new EntireJsonStrategy();
    }

    @Override
    public ProcessingStrategy processingStrategy() {
        return new FastZippingStrategy();
    }
}
