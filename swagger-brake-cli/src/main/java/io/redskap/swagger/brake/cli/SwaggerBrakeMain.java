package io.redskap.swagger.brake.cli;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@SuppressFBWarnings("DM_EXIT")
public class SwaggerBrakeMain {
    public static void main(String[] args) {
        Cli cli = createCliInterface(args);
        int exitCode = cli.start();
        if (exitCode > 0) {
            System.exit(exitCode);
        }
    }

    public static Cli createCliInterface(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CliConfiguration.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.getPropertySources().addFirst(new SimpleCommandLinePropertySource(args));
        return context.getBean(Cli.class);
    }
}
