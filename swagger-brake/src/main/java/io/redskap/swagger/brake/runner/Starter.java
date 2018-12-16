package io.redskap.swagger.brake.runner;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.CoreConfiguration;
import io.redskap.swagger.brake.maven.MavenConfiguration;
import io.redskap.swagger.brake.report.ReporterConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Starter {
    public static Collection<BreakingChange> start(Options options) {
        return createApplicationContext().getBean(Runner.class).run(options);
    }

    /**
     * Checks breaking changes between two {@link OpenAPI} instances.
     * <br>
     * For library users who want to use the check functionality only.
     * @param oldApi the old API
     * @param newApi the new API
     * @return a collection of breaking changes. The collection is never null.
     */
    public static Collection<BreakingChange> check(OpenAPI oldApi, OpenAPI newApi) {
        return createApplicationContext().getBean(Checker.class).check(oldApi, newApi);
    }

    private static AnnotationConfigApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(RunnerConfiguration.class, ReporterConfiguration.class,
                MavenConfiguration.class, CoreConfiguration.class);
    }
}
