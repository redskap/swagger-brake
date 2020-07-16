package io.redskap.swagger.brake.runner;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.CheckerOptions;
import io.redskap.swagger.brake.report.ReporterFactory;
import io.redskap.swagger.brake.runner.download.ArtifactDownloaderHandler;
import io.redskap.swagger.brake.runner.openapi.OpenApiFactory;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * The main entrypoint of Swagger Brake that can be used programmatically.
 */
@RequiredArgsConstructor
@Component
@Slf4j
public class Runner {
    private final OptionsValidator optionsValidator;
    private final ArtifactDownloaderHandler artifactDownloaderHandler;
    private final OpenApiFactory openApiFactory;
    private final CheckerOptionsFactory checkerOptionsFactory;
    private final Checker checker;
    private final ReporterFactory reporterFactory;

    /**
     * Runs Swagger Brake with the specified {@link Options}.
     * @param options the options that should be used for the execution.
     * @return a collection of breaking changes or an empty collection if no breaking change has been detected.
     */
    public Collection<BreakingChange> run(Options options) {
        optionsValidator.validate(options);
        artifactDownloaderHandler.handle(options);
        String oldApiPath = options.getOldApiPath();
        if (StringUtils.isBlank(oldApiPath)) {
            throw new IllegalArgumentException("oldApiPath must be provided");
        }
        String newApiPath = options.getNewApiPath();
        if (StringUtils.isBlank(newApiPath)) {
            throw new IllegalArgumentException("newApiPath must be provided");
        }
        log.info("Loading old API from {}", oldApiPath);
        log.info("Loading new API from {}", newApiPath);
        OpenAPI oldApi = openApiFactory.fromFile(oldApiPath);
        OpenAPI newApi = openApiFactory.fromFile(newApiPath);
        log.info("Successfully loaded APIs");
        CheckerOptions checkerOptions = checkerOptionsFactory.create(options);
        Collection<BreakingChange> breakingChanges = checker.check(oldApi, newApi, checkerOptions);
        reporterFactory.create(options).report(breakingChanges, options);
        return breakingChanges;
    }
}
