package io.redskap.swagger.brake.runner;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.CoreConfiguration;
import io.redskap.swagger.brake.maven.MavenConfiguration;
import io.redskap.swagger.brake.report.ReporterConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {
    public static Collection<BreakingChange> start(Options options) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunnerConfiguration.class, ReporterConfiguration.class,
            MavenConfiguration.class, CoreConfiguration.class);
        return context.getBean(Runner.class).run(options);
    }
}
