package org.example.util;

import net.steppschuh.markdowngenerator.table.Table;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.testcontainers.shaded.org.apache.commons.lang.time.DurationFormatUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


public class TestResultLoggerExtension implements TestWatcher, AfterAllCallback {

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        Table.Builder tableBuilder = new Table.Builder()
                .withAlignments(Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT, Table.ALIGN_LEFT)
                .addRow("name", "size", "create", "write", "read", "lob");

        String durationFormat = "H:mm:ss.SSSSSS";
        List<MyTestResult> testResultList = new ArrayList<>(TestResultRegistry.getResults().values());
        testResultList.sort(Comparator.comparing(MyTestResult::getTestName).thenComparingInt(MyTestResult::getCollectionSize));

        for (MyTestResult mtr: testResultList) {
            tableBuilder.addRow(
                    mtr.getTestName(),
                    mtr.getCollectionSize(),
                    DurationFormatUtils.formatDuration(mtr.getCreateDuration().toMillis(), durationFormat, true),
                    DurationFormatUtils.formatDuration(mtr.getWriteDuration().toMillis(), durationFormat, true),
                    DurationFormatUtils.formatDuration(mtr.getReadDuration().toMillis(), durationFormat, true),
                    FormatUtils.formatByteSize(mtr.getLargeObjectSizeBytes())
            );
        }
        String table = tableBuilder.build().toString();

        File output = new File("RESULT.md");
        if (output.exists()) {
            output.delete();
        } else {
            output.createNewFile();
        }
        try (FileOutputStream fos = new FileOutputStream(output)) {
            fos.write(table.getBytes(StandardCharsets.UTF_8));
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        MyTestResult testResult = TestResultRegistry.result(context);
        System.out.println("Result: " + testResult);
        TestWatcher.super.testSuccessful(context);
    }
}
