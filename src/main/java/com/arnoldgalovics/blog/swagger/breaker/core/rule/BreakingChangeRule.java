package com.arnoldgalovics.blog.swagger.breaker.core.rule;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.model.Specification;

import java.util.Collection;

public interface BreakingChangeRule {
    Collection<BreakingChange> checkRule(Specification oldApi, Specification newApi);
}
