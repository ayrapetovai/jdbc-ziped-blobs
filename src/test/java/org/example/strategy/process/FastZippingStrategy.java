package org.example.strategy.process;

import org.apache.commons.compress.archivers.zip.*;

import java.io.*;

public class FastZippingStrategy implements ProcessingStrategy {
    private static class Wrapper extends OutputStream {
        ZipArchiveOutputStream zipped;
        ZipArchiveEntry entry;

        public Wrapper(ZipArchiveOutputStream zipped, ZipArchiveEntry entry) {
            this.zipped = zipped;
            this.entry = entry;
        }

        @Override
        public void write(int b) throws IOException {
            zipped.write(b);
        }

        @Override
        public void write(byte b[]) throws IOException {
            zipped.write(b, 0, b.length);
        }

        @Override
        public void close() throws IOException {
            zipped.closeArchiveEntry();
            zipped.close();
        }
    }

    @Override
    public OutputStream preprocess(OutputStream out) throws IOException {
        ZipArchiveOutputStream zipped = new ZipArchiveOutputStream(out);
        zipped.setUseZip64(Zip64Mode.Always);
        ZipArchiveEntry entry = new ZipArchiveEntry("data.json");
        zipped.putArchiveEntry(entry);

        return new Wrapper(zipped, entry);
    }

    @Override
    public InputStream postprocess(InputStream in) throws IOException {
        ZipArchiveInputStream zis = new ZipArchiveInputStream(in);
        ZipArchiveEntry ze = (ZipArchiveEntry) zis.getNextEntry();
        return zis;
    }
}
