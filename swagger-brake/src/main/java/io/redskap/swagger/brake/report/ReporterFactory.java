package io.redskap.swagger.brake.report;

import java.util.Collection;
import java.util.stream.Collectors;

import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReporterFactory {
    private final Collection<CheckableReporter> reporters;

    public Reporter create(Options options) {
        Collection<Reporter> reporters = options.getOutputFormats().stream().map(this::findReporter).collect(Collectors.toList());
        return new CompositeReporter(reporters);
    }

    private Reporter findReporter(OutputFormat outputFormat) {
        return reporters.stream().filter(r -> r.canReport(outputFormat)).findAny().orElseThrow(() -> new RuntimeException("Reporter cannot be found for format " + outputFormat));
    }
}
