package io.redskap.swagger.brake.core.model.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchemaStore {
    private final Map<String, Schema> nativeSchemas;
    private final Map<String, io.redskap.swagger.brake.core.model.Schema> transformerSchemas = new HashMap<>();

    public Optional<Schema> getNative(String name) {
        return Optional.ofNullable(nativeSchemas.get(name));
    }

    public Optional<io.redskap.swagger.brake.core.model.Schema> getTransformer(String name, Supplier<io.redskap.swagger.brake.core.model.Schema> provider) {
        return Optional.ofNullable(transformerSchemas.computeIfAbsent(name, (k) -> provider.get()));
    }
}
