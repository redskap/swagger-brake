package io.redskap.swagger.brake.report;

import io.redskap.swagger.brake.core.ApiInfo;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import java.util.Collection;
import java.util.Collections;

public interface Reporter {
    default void report(Collection<BreakingChange> breakingChanges, Options options) {
        report(breakingChanges, options, null);
    }

    default void report(Collection<BreakingChange> breakingChanges, Options options, ApiInfo apiInfo) {
        report(breakingChanges, Collections.emptyList(), options, apiInfo);
    }

    void report(Collection<BreakingChange> breakingChanges, Collection<BreakingChange> ignoredBreakingChanges, Options options, ApiInfo apiInfo);
}
