package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.SchemaStore;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenApiTransformer implements Transformer<OpenAPI, Specification> {
    private final PathTransformer pathTransformer;
    private final SchemaTransformer schemaTransformer;

    @Override
    public Specification transform(OpenAPI from) {
        if (from == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        Collection<Path> paths = pathTransformer.transform(from.getPaths());
        SchemaStore schemaStore = schemaTransformer.transform(from.getComponents());
        return new Specification(paths, schemaStore);
    }
}
