package io.redskap.swagger.brake.report;

import io.redskap.swagger.brake.runner.Options;
import org.springframework.stereotype.Component;

@Component
public class ReporterFactory {
    public Reporter create(Options options) {
        return new StdOutReporter();
    }
}
