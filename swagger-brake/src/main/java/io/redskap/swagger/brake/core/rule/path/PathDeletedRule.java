package io.redskap.swagger.brake.core.rule.path;

import java.util.ArrayList;
import java.util.Collection;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PathDeletedRule implements BreakingChangeRule<PathDeletedBreakingChange> {
    @Override
    public Collection<PathDeletedBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        log.debug("Checking for path deletions..");
        Collection<PathDeletedBreakingChange> breakingChanges = new ArrayList<>();
        for (Path p : oldApi.getPaths()) {
            if (!newApi.getPath(p).isPresent()) {
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
