package io.redskap.swagger.brake.cli;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

public class SwaggerBrakeMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CliConfiguration.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
        Cli cli = context.getBean(Cli.class);
        cli.start();
    }
}
