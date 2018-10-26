package com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore;

import java.util.Map;
import java.util.Optional;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SchemaStore {
    private final Map<String, Schema> schemas;

    public Optional<Schema> get(String name) {
        return Optional.ofNullable(schemas.get(name));
    }
}
