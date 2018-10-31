package io.redskap.swagger.brake.core.model.transformer;

import java.util.Collection;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.schemastore.ComponentsTransformer;
import io.redskap.swagger.brake.core.model.schemastore.SchemaStore;
import io.redskap.swagger.brake.core.model.schemastore.SchemaStoreProvider;
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
