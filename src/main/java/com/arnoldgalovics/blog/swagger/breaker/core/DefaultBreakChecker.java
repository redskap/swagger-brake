package com.arnoldgalovics.blog.swagger.breaker.core;

import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.path.PathDeletedRule;
import com.arnoldgalovics.blog.swagger.breaker.parser.Specification;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class DefaultBreakChecker implements BreakChecker {
    @Override
    public Collection<BreakingChange> check(Specification oldApi, Specification newApi) {
        Collection<BreakingChangeRule> rules = Arrays.asList(new PathDeletedRule());
        return rules.stream().map(rule -> rule.checkRule(oldApi, newApi)).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
