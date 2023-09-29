package io.redskap.swagger.brake.report;

import io.redskap.swagger.brake.core.ApiInfo;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;
import java.util.Collection;

public interface Reporter {
    default void report(Collection<BreakingChange> breakingChanges, Options options) {
        report(breakingChanges, options, null);
    };

    void report(Collection<BreakingChange> breakingChanges, Options options, ApiInfo apiInfo);
}
