package io.redskap.swagger.brake.runner;

import static java.lang.System.err;
import static java.lang.System.out;

import java.util.Collection;

import io.redskap.swagger.brake.api.ApiFacade;
import io.redskap.swagger.brake.api.UploadException;
import io.redskap.swagger.brake.core.BreakChecker;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.transformer.Transformer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
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
    private final ApiFacade apiFacade;

    public Collection<BreakingChange> run(Options options) {
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
        OpenAPI oldApi = loadApi(oldApiPath);
        OpenAPI newApi = loadApi(newApiPath);
        log.info("Successfully loaded APIs");
        Collection<BreakingChange> breakingChanges = breakChecker.check(transformer.transform(oldApi), transformer.transform(newApi));
        if (options.isUploadEnabled()) {
            upload(options, breakingChanges);
        }
        return breakingChanges;
    }

    private void upload(Options options, Collection<BreakingChange> breakingChanges) {
        try {
            out.println("Starting upload..");
            apiFacade.upload(breakingChanges, options.getApiServer(), options.getProject());
            out.println("Upload successful");
        } catch (UploadException e) {
            log.error("Uploading error", e);
            err.println(e.getMessage());
        }
    }

    private OpenAPI loadApi(String apiPath) {
        try {
            OpenAPI loadedApi = new OpenAPIV3Parser().read(apiPath);
            if (loadedApi == null) {
                throw new IllegalStateException("API cannot be loaded from path " + apiPath);
            }
            return loadedApi;
        } catch (Exception e) {
            throw new IllegalStateException("API cannot be loaded from path " + apiPath);
        }
    }
}
