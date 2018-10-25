package com.arnoldgalovics.blog.swagger.breaker.core.rule.path;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import com.arnoldgalovics.blog.swagger.breaker.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.model.Specification;

import java.util.ArrayList;
import java.util.Collection;

public class PathDeletedRule implements BreakingChangeRule {
    @Override
    public Collection<BreakingChange> checkRule(Specification oldApi, Specification newApi) {
        Collection<BreakingChange> breakingChanges = new ArrayList<>();
        for (Path p : oldApi.getPaths()) {
            if (!newApi.getPaths().contains(p)) {
                breakingChanges.add(new PathDeletedBreakingChange(p.getPath(), p.getMethod()));
            }
        }
        return breakingChanges;
    }
}
