package io.redskap.swagger.brake.report;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
class StdOutReporter implements Reporter, CheckableReporter {
    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options) {
        if (!breakingChanges.isEmpty()) {
            System.err.println("There were breaking API changes");
            breakingChanges.stream().map(bc -> bc.getRuleCode() + bc.getMessage()).forEach(System.err::println);
        } else {
            System.out.println("No breaking API changes detected");
        }
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return true;
    }
}
