package io.redskap.swagger.brake.runner;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakChecker;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.transformer.Transformer;
import io.redskap.swagger.brake.report.ReporterFactory;
import io.redskap.swagger.brake.runner.download.ArtifactDownloaderHandler;
import io.redskap.swagger.brake.runner.openapi.OpenApiFactory;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class Runner {
    private final Transformer<OpenAPI, Specification> transformer;
    private final BreakChecker breakChecker;
    private final ReporterFactory reporterFactory;
    private final ArtifactDownloaderHandler artifactDownloaderHandler;
    private final OpenApiFactory openApiFactory;

    public Collection<BreakingChange> run(Options options) {
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
        log.info("Starting the check for breaking API changes");
        Collection<BreakingChange> breakingChanges = breakChecker.check(transformer.transform(oldApi), transformer.transform(newApi));
        log.info("Check is finished");
        reporterFactory.create(options).report(breakingChanges, options);
        return breakingChanges;
    }
}
