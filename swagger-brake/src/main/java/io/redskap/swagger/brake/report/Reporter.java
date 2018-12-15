package io.redskap.swagger.brake.report;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.runner.Options;

public interface Reporter {
    void report(Collection<BreakingChange> breakingChanges, Options options);
}
