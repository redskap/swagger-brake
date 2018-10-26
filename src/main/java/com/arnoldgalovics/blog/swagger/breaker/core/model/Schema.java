package com.arnoldgalovics.blog.swagger.breaker.core.model;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Schema {
    private final String type;
    private final Collection<SchemaAttribute> schemaAttributes;
    private final Schema schema;

    public Schema(String type, Collection<SchemaAttribute> schemaAttributes) {
        this(type, schemaAttributes, null);
    }

    public Schema(String type, Collection<SchemaAttribute> schemaAttributes, Schema schema) {
        this.type = type;
        this.schemaAttributes = schemaAttributes;
        this.schema = schema;
    }

    public Optional<Schema> getUnderlyingSchema() {
        return Optional.ofNullable(schema);
    }

    public Collection<String> getAttributeNames() {
        if (schema != null) {
            return schema.getAttributeNames();
        }
        return schemaAttributes.stream().map(SchemaAttribute::getName).collect(toList());
    }
}
