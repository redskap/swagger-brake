package com.arnoldgalovics.blog.swagger.breaker.runner;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakChecker;
import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.model.transformer.Transformer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class SwaggerBreakerRunner {
    private final Transformer<OpenAPI, Specification> transformer;
    private final BreakChecker breakChecker;

    public Collection<BreakingChange> execute(String oldApiPath, String newApiPath) {
        if (StringUtils.isBlank(oldApiPath)) {
            throw new IllegalArgumentException("oldApiPath must be provided");
        }
        if (StringUtils.isBlank(newApiPath)) {
            throw new IllegalArgumentException("newApiPath must be provided");
        }
        log.debug("Loading old API from {}", oldApiPath);
        log.debug("Loading new API from {}", newApiPath);
        OpenAPI oldApi = loadApi(oldApiPath);
        OpenAPI newApi = loadApi(newApiPath);
        log.debug("Successfully loaded APIs");
        return breakChecker.check(transformer.transform(oldApi), transformer.transform(newApi));
    }

    private OpenAPI loadApi(String oldApiPath) {
        OpenAPI loadedApi = new OpenAPIV3Parser().read(oldApiPath);
        if (loadedApi == null) {
            throw new IllegalStateException("API cannot be loaded from path " + oldApiPath);
        }
        return loadedApi;
    }
}
