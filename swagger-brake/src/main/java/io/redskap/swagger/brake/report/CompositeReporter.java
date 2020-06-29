package io.redskap.swagger.brake.report;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CompositeReporter implements Reporter {
    private final Collection<Reporter> delegates;

    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options) {
        delegates.forEach(d -> d.report(breakingChanges, options));
    }
}
