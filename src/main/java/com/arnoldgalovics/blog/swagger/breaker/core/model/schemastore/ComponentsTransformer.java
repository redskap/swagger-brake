package com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore;

import com.arnoldgalovics.blog.swagger.breaker.core.model.transformer.SchemaTransformer;
import com.arnoldgalovics.blog.swagger.breaker.core.model.transformer.Transformer;
import io.swagger.v3.oas.models.Components;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComponentsTransformer implements Transformer<Components, SchemaStore> {
    private final SchemaTransformer schemaTransformer;

    @Override
    public SchemaStore transform(Components from) {
        return new SchemaStore(from.getSchemas());
    }
}
