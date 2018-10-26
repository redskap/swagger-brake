package com.arnoldgalovics.blog.swagger.breaker.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RequestParameter {
    private final RequestParameterType type;
    private final String name;
    private final Schema schema;

    public RequestParameter(RequestParameterType type, String name) {
        this(type, name, null);
    }

    public RequestParameter(RequestParameterType type, String name, Schema schema) {
        this.type = type;
        this.name = name;
        this.schema = schema;
    }
}
