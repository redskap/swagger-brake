package io.redskap.swagger.brake.core.model.schemastore;

import io.redskap.swagger.brake.core.model.transformer.SchemaTransformer;
import io.redskap.swagger.brake.core.model.transformer.Transformer;
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
