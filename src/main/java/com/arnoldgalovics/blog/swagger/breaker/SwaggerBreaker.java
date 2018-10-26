package com.arnoldgalovics.blog.swagger.breaker;

import static java.lang.System.err;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.SwaggerBreakerCoreConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.runner.SwaggerBreakerRunner;
import com.arnoldgalovics.blog.swagger.breaker.runner.SwaggerBreakerRunnerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@Slf4j
public class SwaggerBreaker {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SwaggerBreakerCoreConfiguration.class, SwaggerBreakerRunnerConfiguration.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
        SwaggerBreakerRunner executor = context.getBean(SwaggerBreakerRunner.class);
        String oldApiPath = environment.getProperty("old-api");
        String newApiPath = environment.getProperty("new-api");
        if (StringUtils.isBlank(oldApiPath)) {
            err.println("old-api must be set");
            return;
        }
        if (StringUtils.isBlank(newApiPath)) {
            err.println("new-api must be set");
            return;
        }
        executor.execute(oldApiPath, newApiPath).stream().map(BreakingChange::getMessage).forEach(log::info);
    }
}
