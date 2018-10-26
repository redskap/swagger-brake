package com.arnoldgalovics.blog.swagger.breaker.core.rule;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;

public interface BreakingChangeRule {
    Collection<? extends BreakingChange> checkRule(Specification oldApi, Specification newApi);
}
