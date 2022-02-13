package org.example.strategy.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NoprocessingStrategy implements ProcessingStrategy{
    @Override
    public OutputStream preprocess(OutputStream out) throws IOException {
        return out;
    }

    @Override
    public InputStream postprocess(InputStream in) throws IOException {
        return in;
    }
}
