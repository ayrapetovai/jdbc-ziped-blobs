package org.example.util;

import org.junit.jupiter.api.extension.ExtensionContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestResultRegistry {
    private static final Map<String, MyTestResult> results = new HashMap<>();

    public static void registerResult(TestContext testContext, int collectionSize, Duration createTime, Duration writeTime, Duration readTime, long largeObjectSizeBytes) {
        String testKey = createTestKey(testContext.getExtensionContext());
        results.put(testKey, new MyTestResult(
                testContext.getExtensionContext().getTestClass().get().getSimpleName(),
                collectionSize,
                createTime,
                writeTime,
                readTime,
                largeObjectSizeBytes
        ));
    }

    public static String createTestKey(ExtensionContext extensionContext) {
        return extensionContext.getUniqueId();
    }

    public static MyTestResult result(ExtensionContext context) {
        String testKey = createTestKey(context);
        return results.get(testKey);
    }

    public static Map<String, MyTestResult> getResults() {
        return results;
    }
}
