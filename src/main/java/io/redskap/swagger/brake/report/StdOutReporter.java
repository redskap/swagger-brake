package io.redskap.swagger.brake.report;

import static java.lang.System.out;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import io.redskap.swagger.brake.runner.OutputFormat;
import org.springframework.stereotype.Component;

@Component
public class StdOutReporter implements Reporter {
    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options) {
        if (!breakingChanges.isEmpty()) {
            System.out.println("There were breaking API changes");
            System.out.println();
            breakingChanges.stream().map(BreakingChange::getMessage).forEach(out::println);
        } else {
            System.out.println("No breaking API changes detected");
        }
    }

    @Override
    public boolean canReport(OutputFormat format) {
        return OutputFormat.STDOUT.equals(format);
    }
}
