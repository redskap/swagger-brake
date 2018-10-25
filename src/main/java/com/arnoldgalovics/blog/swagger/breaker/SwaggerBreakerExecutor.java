package com.arnoldgalovics.blog.swagger.breaker;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakChecker;
import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.DefaultBreakChecker;
import com.arnoldgalovics.blog.swagger.breaker.model.transformer.OpenAPITransformer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public class SwaggerBreakerExecutor {
    private final String oldApiPath;
    private final String newApiPath;

    public Collection<BreakingChange> execute() {
        OpenAPI oldApi = new OpenAPIV3Parser().read(oldApiPath);
        OpenAPI newApi = new OpenAPIV3Parser().read(newApiPath);
        OpenAPITransformer transformer = new OpenAPITransformer();
        BreakChecker breakChecker = new DefaultBreakChecker();
        return breakChecker.check(transformer.transform(oldApi), transformer.transform(newApi));
    }
}
