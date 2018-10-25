package com.arnoldgalovics.blog.swagger.breaker.core;

import com.arnoldgalovics.blog.swagger.breaker.parser.Specification;

import java.util.Collection;

public interface BreakChecker {
    Collection<BreakingChange> check(Specification oldApi, Specification newApi);
}
