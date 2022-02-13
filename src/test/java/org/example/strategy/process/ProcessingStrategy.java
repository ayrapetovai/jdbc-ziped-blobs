package org.example.strategy.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ProcessingStrategy {
    OutputStream preprocess(OutputStream out) throws IOException;

    InputStream postprocess(InputStream in) throws IOException;
}