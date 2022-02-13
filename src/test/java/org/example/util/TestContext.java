package org.example.util;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;

public class TestContext {
    private final ParameterContext parameterContext;
    private final ExtensionContext extensionContext;

    public TestContext(ParameterContext parameterContext, ExtensionContext extensionContext) {
        this.parameterContext = parameterContext;
        this.extensionContext = extensionContext;
    }

    public ParameterContext getParameterContext() {
        return parameterContext;
    }

    public ExtensionContext getExtensionContext() {
        return extensionContext;
    }
}
