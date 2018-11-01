package io.redskap.swagger.brake.report;

import static java.lang.System.out;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;

public class StdOutReporter implements Reporter {
    @Override
    public void report(Collection<BreakingChange> breakingChanges) {
        if (!breakingChanges.isEmpty()) {
            System.out.println("There were breaking API changes");
            System.out.println();
            breakingChanges.stream().map(BreakingChange::getMessage).forEach(out::println);
        } else {
            System.out.println("No breaking API changes detected");
        }
    }
}
