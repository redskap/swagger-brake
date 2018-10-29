package com.arnoldgalovics.blog.swagger.breaker;

import com.arnoldgalovics.blog.swagger.breaker.api.ApiConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.cli.Cli;
import com.arnoldgalovics.blog.swagger.breaker.cli.CliConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.core.CoreConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.runner.RunnerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

public class SwaggerBreaker {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
            CoreConfiguration.class, RunnerConfiguration.class, CliConfiguration.class, ApiConfiguration.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
        Cli cli = context.getBean(Cli.class);
        cli.start();
    }
}
