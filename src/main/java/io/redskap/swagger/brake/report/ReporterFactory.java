package io.redskap.swagger.brake.report;

import java.util.Collection;

import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReporterFactory {
    private final StdOutReporter stdOutReporter;
    private final Collection<Reporter> reporters;

    public Reporter create(Options options) {
        OutputFormat outputFormat = options.getOutputFormat();
        return reporters.stream().filter(r -> r.canReport(outputFormat)).findAny().orElse(stdOutReporter);
    }
}
