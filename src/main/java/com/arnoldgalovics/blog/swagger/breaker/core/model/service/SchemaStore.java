package com.arnoldgalovics.blog.swagger.breaker.core.model.service;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import org.springframework.stereotype.Component;

@Component
public class SchemaStore {
    private final Map<String, Schema> schemas;

    public SchemaStore(Collection<Schema> schemas) {
        this.schemas = schemas.stream().collect(toMap(Schema::getName, identity()));
    }

    public Optional<Schema> get(String name) {
        return Optional.ofNullable(schemas.get(name));
    }
}
