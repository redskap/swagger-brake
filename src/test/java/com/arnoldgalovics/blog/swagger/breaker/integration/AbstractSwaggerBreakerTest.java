package com.arnoldgalovics.blog.swagger.breaker.integration;

import com.arnoldgalovics.blog.swagger.breaker.SwaggerBreakerExecutor;
import org.junit.Before;

public abstract class AbstractSwaggerBreakerTest {
    protected SwaggerBreakerExecutor underTest;

    @Before
    public void setUp() {
        underTest = new SwaggerBreakerExecutor(getOldApiPath(), getNewApiPath());
    }

    protected abstract String getOldApiPath();

    protected abstract String getNewApiPath();
}
