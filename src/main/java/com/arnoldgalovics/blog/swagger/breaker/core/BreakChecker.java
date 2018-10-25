package com.arnoldgalovics.blog.swagger.breaker.core;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;

public interface BreakChecker {
    Collection<BreakingChange> check(Specification oldApi, Specification newApi);
}
