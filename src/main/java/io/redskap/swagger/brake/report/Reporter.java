package io.redskap.swagger.brake.report;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;

public interface Reporter {
    void report(Collection<BreakingChange> breakingChanges);
}
