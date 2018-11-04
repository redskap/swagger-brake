package io.redskap.swagger.brake.report;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StdOutReporter implements Reporter {
    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options) {
        if (!breakingChanges.isEmpty()) {
            log.info("There were breaking API changes");
            breakingChanges.stream().map(BreakingChange::getMessage).forEach(log::info);
        } else {
            log.info("No breaking API changes detected");
        }
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.STDOUT.equals(format);
    }
}
