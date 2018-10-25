package com.arnoldgalovics.blog.swagger.breaker.core.rule;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.parser.Specification;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.Collection;

public interface BreakingChangeRule {
    Collection<BreakingChange> checkRule(Specification oldApi, Specification newApi);
}
