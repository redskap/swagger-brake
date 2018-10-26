package com.arnoldgalovics.blog.swagger.breaker.core.model;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Schema {
    private final String type;
    private final Collection<String> enumValues;
    private final Collection<SchemaAttribute> schemaAttributes;
    private final Schema schema;

    public Optional<Schema> getUnderlyingSchema() {
        return Optional.ofNullable(schema);
    }

    public Collection<String> getEnumValues() {
        if (schema != null) {
            return schema.getEnumValues();
        }
        return enumValues;
    }

    public Collection<String> getAttributeNames() {
        if (schema != null) {
            return schema.getAttributeNames();
        }
        return schemaAttributes.stream().map(SchemaAttribute::getName).collect(toList());
    }

    public static class Builder {
        private String type;
        private Collection<String> enumValues;
        private Collection<SchemaAttribute> schemaAttributes;
        private Schema schema;

        public Builder(String type) {
            this.type = type;
        }

        public Builder enumValues(Collection<String> enumValues) {
            this.enumValues = enumValues;
            return this;
        }

        public Builder schemaAttributes(Collection<SchemaAttribute> schemaAttributes) {
            this.schemaAttributes = schemaAttributes;
            return this;
        }

        public Builder schema(Schema schema) {
            this.schema = schema;
            return this;
        }

        public Schema build() {
            Collection<SchemaAttribute> attributes = Collections.emptyList();
            if (schemaAttributes != null) {
                attributes = schemaAttributes;
            }
            Collection<String> enValues = Collections.emptyList();
            if (enumValues != null) {
                enValues = enumValues;
            }
            return new Schema(type, enValues, attributes, schema);
        }
    }
}
