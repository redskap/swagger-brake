package com.arnoldgalovics.blog.swagger.breaker.integration;

import com.arnoldgalovics.blog.swagger.breaker.core.SwaggerBreakerCoreConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.runner.SwaggerBreakerRunner;
import com.arnoldgalovics.blog.swagger.breaker.runner.SwaggerBreakerRunnerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SwaggerBreakerCoreConfiguration.class, SwaggerBreakerRunnerConfiguration.class})
public abstract class AbstractSwaggerBreakerTest {
    @Autowired
    protected SwaggerBreakerRunner underTest;
}
