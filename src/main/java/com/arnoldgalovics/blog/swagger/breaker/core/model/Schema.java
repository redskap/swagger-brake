package com.arnoldgalovics.blog.swagger.breaker.core.model;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Schema {
    private final String name;
    private final Collection<SchemaAttribute> schemaAttributes;

    public Collection<String> getAttributeNames() {
        return schemaAttributes.stream().map(SchemaAttribute::getName).collect(toList());
    }
}
