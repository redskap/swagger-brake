package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RequestParameter {
    private final RequestParameterInType inType;
    private final String name;
    private final Schema schema;

    public RequestParameter(RequestParameterInType inType, String name) {
        this(inType, name, null);
    }

    public RequestParameter(RequestParameterInType inType, String name, Schema schema) {
        this.inType = inType;
        this.name = name;
        this.schema = schema;
    }

    public Optional<Schema> getSchema() {
        return Optional.ofNullable(schema);
    }
}
