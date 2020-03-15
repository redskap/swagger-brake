package io.redskap.swagger.brake.core.rule.beta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import io.redskap.swagger.brake.core.CheckerOptionsProvider;
import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.rule.BreakingChangeRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StandardApiToBetaApiRule implements BreakingChangeRule<StandardApiToBetaApiBreakingChange> {
    private final CheckerOptionsProvider checkerOptionsProvider;

    @Override
    public Collection<StandardApiToBetaApiBreakingChange> checkRule(Specification oldApi, Specification newApi) {
        log.debug("Checking for standard API to beta API change..");
        Collection<StandardApiToBetaApiBreakingChange> breakingChanges = new ArrayList<>();
        for (Path p : oldApi.getPaths()) {
            Optional<Path> newApiPathOpt = newApi.getPath(p);
            if (newApiPathOpt.isPresent()) {
                Path newApiPath = newApiPathOpt.get();
                if (!p.isBetaApi() && newApiPath.isBetaApi()) {
                    breakingChanges.add(new StandardApiToBetaApiBreakingChange(p.getPath(), p.getMethod()));
                }
            }
        }
        log.debug("Found breaking standard API to beta API changes: {}", breakingChanges);
        return breakingChanges;
    }
}
