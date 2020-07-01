package io.redskap.swagger.brake.core.model.store;

import java.util.Map;
import java.util.Optional;

import io.swagger.v3.oas.models.media.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchemaStore {
    private final Map<String, Schema> schemas;

    public Optional<Schema> get(String name) {
        return Optional.ofNullable(schemas.get(name));
    }
}
