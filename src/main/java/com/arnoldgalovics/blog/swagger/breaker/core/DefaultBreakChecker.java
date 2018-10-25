package com.arnoldgalovics.blog.swagger.breaker.core;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DefaultBreakChecker implements BreakChecker {
    private final Collection<BreakingChangeRule> rules;

    @Override
    public Collection<BreakingChange> check(Specification oldApi, Specification newApi) {
        return rules.stream().map(rule -> rule.checkRule(oldApi, newApi)).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
