package org.example.util;

import java.time.Duration;

public class MyTestResult {
    private final String testName;
    private final int collectionSize;
    private final Duration createDuration;
    private final Duration writeDuration;
    private final Duration readDuration;
    private final long largeObjectSizeBytes;

    public MyTestResult(String testName, int collectionSize, Duration createDuration, Duration writeDuration, Duration readDuration, long largeObjectSizeBytes) {
        this.testName = testName;
        this.collectionSize = collectionSize;
        this.createDuration = createDuration;
        this.writeDuration = writeDuration;
        this.readDuration = readDuration;
        this.largeObjectSizeBytes = largeObjectSizeBytes;
    }

    public String getTestName() {
        return testName;
    }

    public int getCollectionSize() {
        return collectionSize;
    }

    public Duration getCreateDuration() {
        return createDuration;
    }

    public Duration getWriteDuration() {
        return writeDuration;
    }

    public Duration getReadDuration() {
        return readDuration;
    }

    public long getLargeObjectSizeBytes() {
        return largeObjectSizeBytes;
    }

    @Override
    public String toString() {
        return "test=" + testName +
                ", collection=" + collectionSize +
                ", create=" + createDuration.toMillis() +
                ", write=" + writeDuration.toMillis() +
                ", read=" + readDuration.toMillis() +
                ", lob=" + FormatUtils.formatByteSize(largeObjectSizeBytes);
    }
}
