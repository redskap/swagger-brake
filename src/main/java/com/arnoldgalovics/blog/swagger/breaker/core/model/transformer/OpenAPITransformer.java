package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.stereotype.Component;

@Component
public class OpenAPITransformer implements Transformer<OpenAPI, Specification> {
    @Override
    public Specification transform(OpenAPI from) {
        return new Specification(new PathTransformer().transform(from.getPaths()));
    }
}
