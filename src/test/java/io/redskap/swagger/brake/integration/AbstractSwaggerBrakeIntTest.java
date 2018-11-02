package io.redskap.swagger.brake.integration;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.CoreConfiguration;
import io.redskap.swagger.brake.report.ReporterConfiguration;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import io.redskap.swagger.brake.runner.Runner;
import io.redskap.swagger.brake.runner.RunnerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {CoreConfiguration.class, RunnerConfiguration.class, ReporterConfiguration.class})
public abstract class AbstractSwaggerBrakeIntTest {
    @Autowired
    protected Runner underTest;

    protected Collection<BreakingChange> execute(String oldApiPath, String newApiPath) {
        Options options = new Options();
        options.setOldApiPath(oldApiPath);
        options.setNewApiPath(newApiPath);
        options.setOutputFormat(OutputFormat.STDOUT);
        return underTest.run(options);
    }
}
