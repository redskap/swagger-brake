package com.arnoldgalovics.blog.swagger.breaker.integration;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.CoreConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.runner.Options;
import com.arnoldgalovics.blog.swagger.breaker.runner.Runner;
import com.arnoldgalovics.blog.swagger.breaker.runner.RunnerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {CoreConfiguration.class, RunnerConfiguration.class, IntTestConfiguration.class})
public abstract class AbstractSwaggerBreakerTest {
    @Autowired
    protected Runner underTest;

    protected Collection<BreakingChange> execute(String oldApiPath, String newApiPath) {
        Options options = new Options();
        options.setOldApiPath(oldApiPath);
        options.setNewApiPath(newApiPath);
        return underTest.run(options);
    }
}
