package io.redskap.swagger.brake.report;

import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.springframework.stereotype.Component;

@Component
public class ReporterFactory {
    public Reporter create(Options options) {
        OutputFormat outputFormat = options.getOutputFormat();
        if (OutputFormat.JSON.equals(outputFormat)) {
            return new JsonReporter(options.getOutputFilePath());
        }
        return new StdOutReporter();
    }
}
