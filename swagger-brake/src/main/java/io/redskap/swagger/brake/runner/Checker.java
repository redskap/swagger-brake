package io.redskap.swagger.brake.runner;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakChecker;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.CheckerOptions;
import io.redskap.swagger.brake.core.CheckerOptionsProvider;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.transformer.Transformer;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class Checker {
    private final Transformer<OpenAPI, Specification> transformer;
    private final BreakChecker breakChecker;
    private final CheckerOptionsProvider checkerOptionsProvider;

    public Collection<BreakingChange> check(OpenAPI oldApi, OpenAPI newApi, CheckerOptions checkerOptions) {
        log.info("Starting the check for breaking API changes");
        if (checkerOptions == null) {
            throw new IllegalArgumentException("checkerOptions must be provided");
        }
        checkerOptionsProvider.set(checkerOptions);
        Specification oldApiSpec = transformer.transform(oldApi);
        Specification newApiSpec = transformer.transform(newApi);
        Collection<BreakingChange> breakingChanges = breakChecker.check(oldApiSpec, newApiSpec);
        log.info("Check is finished");
        return breakingChanges;
    }
}
