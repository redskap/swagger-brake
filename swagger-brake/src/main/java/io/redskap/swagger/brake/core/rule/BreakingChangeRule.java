package io.redskap.swagger.brake.core.rule;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.Specification;

public interface BreakingChangeRule<T extends BreakingChange> {
    Collection<T> checkRule(Specification oldApi, Specification newApi);
}
