package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Specification;
import com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore.ComponentsTransformer;
import com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore.SchemaStore;
import com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore.SchemaStoreProvider;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenApiTransformer implements Transformer<OpenAPI, Specification> {
    private final PathTransformer pathTransformer;
    private final ComponentsTransformer componentsTransformer;

    @Override
    public Specification transform(OpenAPI from) {
        if (from == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        SchemaStore schemaStore = componentsTransformer.transform(from.getComponents());
        Collection<Path> paths;
        try {
            SchemaStoreProvider.setSchemaStore(schemaStore);
            paths = pathTransformer.transform(from.getPaths());
        } finally {
            SchemaStoreProvider.clear();
        }
        return new Specification(paths);
    }
}
