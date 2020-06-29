package io.redskap.swagger.brake.core.model;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RequestParameter {
    private final RequestParameterInType inType;
    private final String name;
    private final boolean required;
    private final Schema schema;

    public RequestParameter(RequestParameterInType inType, String name, boolean required) {
        this(inType, name, required, null);
    }

    public Optional<Schema> getSchema() {
        return Optional.ofNullable(schema);
    }
}
