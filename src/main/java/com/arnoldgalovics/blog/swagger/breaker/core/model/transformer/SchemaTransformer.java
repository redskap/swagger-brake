package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaAttribute;
import com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore.SchemaStore;
import com.arnoldgalovics.blog.swagger.breaker.core.model.schemastore.SchemaStoreProvider;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.TypeRefNameResolver;
import io.swagger.v3.oas.models.media.ArraySchema;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchemaTransformer implements Transformer<io.swagger.v3.oas.models.media.Schema, Schema> {
    private final TypeRefNameResolver typeRefNameResolver;

    @Override
    public Schema transform(io.swagger.v3.oas.models.media.Schema from) {
        return internalTransform(from);
    }

    private Schema internalTransform(io.swagger.v3.oas.models.media.Schema swSchema) {
        if (swSchema == null) {
            return null;
        }
        if (swSchema instanceof ArraySchema) {
            Schema schema = internalTransform(((ArraySchema) swSchema).getItems());
            return new Schema.Builder(swSchema.getType()).schema(schema).schemaAttributes(getSchemaAttributes(swSchema)).build();
        } else {
            return transformSchema(swSchema);
        }
    }

    private Schema transformSchema(io.swagger.v3.oas.models.media.Schema swSchema) {
        if (!StringUtils.isBlank(swSchema.get$ref())) {
            io.swagger.v3.oas.models.media.Schema resolvedSchema = getSchemaForRef(swSchema.get$ref());
            return internalTransform(resolvedSchema);
        }

        Schema.Builder schemaBuilder = new Schema.Builder(swSchema.getType());
        schemaBuilder.schemaAttributes(getSchemaAttributes(swSchema));
        List<String> enumValues = swSchema.getEnum();
        if (CollectionUtils.isNotEmpty(enumValues)) {
            schemaBuilder.enumValues(enumValues);
        }
        return schemaBuilder.build();
    }

    private List<SchemaAttribute> getSchemaAttributes(io.swagger.v3.oas.models.media.Schema swSchema) {
        Map<String, io.swagger.v3.oas.models.media.Schema> properties = swSchema.getProperties();
        if (properties == null) {
            return Collections.emptyList();
        }
        return properties.entrySet()
                    .stream()
                    .map(e -> {
                        io.swagger.v3.oas.models.media.Schema newInternalSchema = e.getValue();
                        Schema schema = internalTransform(newInternalSchema);
                        return new SchemaAttribute(e.getKey(), schema);
                    })
                    .collect(toList());
    }

    private io.swagger.v3.oas.models.media.Schema getSchemaForRef(String originalRefName) {
        if (originalRefName == null) {
            return null;
        }
        String refName = typeRefNameResolver.resolve(originalRefName);
        SchemaStore schemaStore = SchemaStoreProvider.provide();
        if (schemaStore == null) {
            return null;
        }
        return schemaStore.get(refName).orElseThrow(() -> new IllegalStateException("Reference not found for " + refName));
    }
}
