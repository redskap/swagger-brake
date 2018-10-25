package com.arnoldgalovics.blog.swagger.breaker.core;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@Slf4j
public class DefaultBreakChecker implements BreakChecker {
    private final Collection<BreakingChangeRule> rules;

    @PostConstruct
    public void postConstruct() {
        if (log.isDebugEnabled()) {
            rules.stream().map(BreakingChangeRule::getClass).map(Class::getName).forEach(name -> log.debug("Rule configured: {}", name));
        }
    }

    @Override
    public Collection<BreakingChange> check(Specification oldApi, Specification newApi) {
        return rules.stream().map(rule -> rule.checkRule(oldApi, newApi)).flatMap(Collection::stream).collect(toList());
    }
}
