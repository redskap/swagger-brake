package com.arnoldgalovics.blog.swagger.breaker.core.rule.path;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.rule.BreakingChangeRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
@Slf4j
public class PathDeletedRule implements BreakingChangeRule {
    @Override
    public Collection<BreakingChange> checkRule(Specification oldApi, Specification newApi) {
        log.debug("Checking for path deletions..");
        Collection<BreakingChange> breakingChanges = new ArrayList<>();
        for (Path p : oldApi.getPaths()) {
            if (!newApi.getPaths().contains(p)) {
                log.debug("Path {} is not included in the new API", p);
                breakingChanges.add(new PathDeletedBreakingChange(p.getPath(), p.getMethod()));
            } else {
                log.debug("Path {} is present in the new API as well", p);
            }
        }
        log.debug("Found path deletions: {}", breakingChanges);
        return breakingChanges;
    }
}
