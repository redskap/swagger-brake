package io.redskap.swagger.brake.core;

import java.util.Collection;

import io.redskap.swagger.brake.core.model.Specification;

public interface BreakChecker {
    Collection<BreakingChange> check(Specification oldApi, Specification newApi);
}
