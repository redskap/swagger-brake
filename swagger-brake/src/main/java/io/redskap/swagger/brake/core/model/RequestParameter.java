package io.redskap.swagger.brake.core.model;

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
    private final boolean required;

    public RequestParameter(RequestParameterInType inType, String name, boolean required) {
        this(inType, name, required, null);
    }

    public RequestParameter(RequestParameterInType inType, String name, boolean required, Schema schema) {
        this.inType = inType;
        this.name = name;
        this.required = required;
        this.schema = schema;
    }

    public Optional<Schema> getSchema() {
        return Optional.ofNullable(schema);
    }
}
