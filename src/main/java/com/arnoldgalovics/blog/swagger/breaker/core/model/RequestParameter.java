package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RequestParameter {
    private final RequestParameterInType type;
    private final String name;
    private final Schema schema;

    public RequestParameter(RequestParameterInType type, String name) {
        this(type, name, null);
    }

    public RequestParameter(RequestParameterInType type, String name, Schema schema) {
        this.type = type;
        this.name = name;
        this.schema = schema;
    }

    public Optional<Schema> getSchema() {
        return Optional.ofNullable(schema);
    }
}
