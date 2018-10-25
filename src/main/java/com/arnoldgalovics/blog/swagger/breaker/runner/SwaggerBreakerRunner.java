package com.arnoldgalovics.blog.swagger.breaker.runner;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakChecker;
import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.model.transformer.Transformer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
public class SwaggerBreakerRunner {
    private final Transformer<OpenAPI, Specification> transformer;
    private final BreakChecker breakChecker;

    public Collection<BreakingChange> execute(String oldApiPath, String newApiPath) {
        OpenAPI oldApi = new OpenAPIV3Parser().read(oldApiPath);
        OpenAPI newApi = new OpenAPIV3Parser().read(newApiPath);
        return breakChecker.check(transformer.transform(oldApi), transformer.transform(newApi));
    }
}
