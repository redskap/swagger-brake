package io.redskap.swagger.brake.core.model;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import java.util.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

@Getter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
public class Schema {
    private final String type;
    private final Set<String> enumValues;
    private final Set<SchemaAttribute> schemaAttributes;
    private final Schema schema;

    public Optional<Schema> getSchema() {
        return Optional.ofNullable(schema);
    }

    /**
     * Returns all the enum attributes recursively within the schema.
     * @return the enum attribute names.
     */
    public Collection<String> getEnums() {
        Collection<SchemaAttribute> schemaAttrs = schemaAttributes;
        if (CollectionUtils.isEmpty(schemaAttrs)) {
            schemaAttrs = Optional.ofNullable(schema).map(Schema::getSchemaAttributes).orElse(Collections.emptySet());
        }
        Collection<String> result = internalGetEnums(schemaAttrs, "");
        result.addAll(Optional.ofNullable(schema).map(Schema::getEnumValues).orElse(enumValues));
        return result;
    }

    private Collection<String> internalGetEnums(Collection<SchemaAttribute> schemaAttributes, String levelName) {
        Collection<String> result = schemaAttributes
            .stream()
            .filter(a -> a.getSchema() != null)
            .map(a -> a.getSchema().getEnumValues().stream().map(e -> generateLeveledName(e, a.getName())).collect(toList()))
            .flatMap(Collection::stream)
            .collect(toList());

        for (SchemaAttribute schemaAttribute : schemaAttributes) {
            Schema childSchema = schemaAttribute.getSchema();
            if (childSchema != null) {
                Collection<SchemaAttribute> childSchemaAttributes = childSchema.getSchemaAttributes();
                if (isEmpty(childSchemaAttributes)) {
                    childSchemaAttributes = childSchema.getSchema().map(Schema::getSchemaAttributes).orElse(Collections.emptySet());
                }
                result.addAll(internalGetEnums(childSchemaAttributes, generateLeveledName(schemaAttribute.getName(), levelName)));
            }
        }
        return result;
    }

    /**
     * Returns all the types recursively in this schema.
     * @return the types.
     */
    public Map<String, String> getTypes() {
        Collection<SchemaAttribute> schemaAttrs = schemaAttributes;
        if (CollectionUtils.isEmpty(schemaAttrs)) {
            schemaAttrs = Optional.ofNullable(schema).map(Schema::getSchemaAttributes).orElse(Collections.emptySet());
        }
        Map<String, String> types = internalGetTypes(schemaAttrs, "");
        types.put("", type);
        return types;
    }

    private Map<String, String> internalGetTypes(Collection<SchemaAttribute> schemaAttributes, String levelName) {
        Map<String, String> result = schemaAttributes
            .stream()
            .filter(a -> a.getSchema() != null)
            .collect(toMap(a -> generateLeveledName(a.getName(), levelName), a -> a.getSchema().getType()));
        for (SchemaAttribute schemaAttribute : schemaAttributes) {
            Schema childSchema = schemaAttribute.getSchema();
            if (childSchema != null) {
                Collection<SchemaAttribute> childSchemaAttributes = childSchema.getSchemaAttributes();
                if (isEmpty(childSchemaAttributes)) {
                    childSchemaAttributes = childSchema.getSchema().map(Schema::getSchemaAttributes).orElse(Collections.emptySet());
                }
                result.putAll(internalGetTypes(childSchemaAttributes, levelName));
            }
        }
        return result;
    }

    /**
     * Returns all the attribute names recursively.
     * @return the attribute names.
     */
    public Collection<String> getAttributeNames() {
        Collection<SchemaAttribute> schemaAttrs = schemaAttributes;
        if (CollectionUtils.isEmpty(schemaAttrs)) {
            schemaAttrs = Optional.ofNullable(schema).map(Schema::getSchemaAttributes).orElse(Collections.emptySet());
        }
        return internalGetAttributeNames(schemaAttrs, "");
    }

    private Collection<String> internalGetAttributeNames(Collection<SchemaAttribute> schemaAttributes, String levelName) {
        List<String> result = schemaAttributes.stream().map(SchemaAttribute::getName).map(name -> generateLeveledName(name, levelName)).collect(toList());
        for (SchemaAttribute schemaAttribute : schemaAttributes) {
            Schema childSchema = schemaAttribute.getSchema();
            if (childSchema != null) {
                Collection<SchemaAttribute> childSchemaAttributes = childSchema.getSchemaAttributes();
                if (isEmpty(childSchemaAttributes)) {
                    childSchemaAttributes = childSchema.getSchema().map(Schema::getSchemaAttributes).orElse(Collections.emptySet());
                }
                result.addAll(internalGetAttributeNames(childSchemaAttributes, generateLeveledName(schemaAttribute.getName(), levelName)));
            }
        }
        return result;
    }

    private String generateLeveledName(String name, String levelName) {
        if (!StringUtils.isBlank(levelName)) {
            return format("%s.%s", levelName, name);
        }
        return name;
    }

    public static class Builder {
        private final String type;
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

        /**
         * Builds a {@link Schema} instance.
         * @return the constructed {@link Schema} instance.
         */
        public Schema build() {
            Collection<SchemaAttribute> attributes = Collections.emptyList();
            if (schemaAttributes != null) {
                attributes = schemaAttributes;
            }
            Collection<String> enValues = Collections.emptyList();
            if (enumValues != null) {
                enValues = enumValues;
            }
            return new Schema(type, new TreeSet<>(enValues), new TreeSet<>(attributes), schema);
        }
    }
}
