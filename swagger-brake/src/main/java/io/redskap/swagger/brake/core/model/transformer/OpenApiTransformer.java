package io.redskap.swagger.brake.core.model.transformer;

import java.util.Collection;

import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.store.*;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OpenApiTransformer implements Transformer<OpenAPI, Specification> {
    private final PathTransformer pathTransformer;
    private final ComponentsTransformer componentsTransformer;
    private final ParametersTransformer parametersTransformer;

    @Override
    public Specification transform(OpenAPI from) {
        if (from == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        SchemaStore schemaStore = componentsTransformer.transform(from.getComponents());
        ParameterStore parameterStore = parametersTransformer.transform(from.getComponents());
        Collection<Path> paths;
        try {
            StoreProvider.setSchemaStore(schemaStore);
            StoreProvider.setParameterStore(parameterStore);
            paths = pathTransformer.transform(from.getPaths());
        } finally {
            StoreProvider.clear();
        }
        return new Specification(paths);
    }
}
