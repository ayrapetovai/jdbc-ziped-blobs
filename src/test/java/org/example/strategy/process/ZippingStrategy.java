package org.example.strategy.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZippingStrategy implements ProcessingStrategy {
    @Override
    public OutputStream preprocess(OutputStream out) throws IOException {
        ZipOutputStream zipped = new ZipOutputStream(out);
        ZipEntry zipEntry = new ZipEntry("data.json");
        zipped.putNextEntry(zipEntry);
        return zipped;
    }

    @Override
    public InputStream postprocess(InputStream in) throws IOException {
        ZipInputStream unzip = new ZipInputStream(in);
        unzip.getNextEntry();
        return unzip;
    }
}
