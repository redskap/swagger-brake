package io.redskap.swagger.brake.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "name")
@ToString
public class SchemaAttribute {
    private final String name;
    private final Schema schema;
}
