package io.redskap.swagger.brake.report;

import io.redskap.swagger.brake.core.ApiInfo;
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

    @Override
    public void report(Collection<BreakingChange> breakingChanges, Options options, ApiInfo apiInfo) {
        delegates.forEach(d -> d.report(breakingChanges, options, apiInfo));
    }
}
