package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaAttribute;
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
            Schema childSchema = internalTransform(((ArraySchema) swSchema).getItems());
            return transformSchema(swSchema, childSchema);
        } else {
            return transformSchema(swSchema);
        }
    }

    private Schema transformSchema(io.swagger.v3.oas.models.media.Schema swSchema) {
        return transformSchema(swSchema, null);
    }

    private Schema transformSchema(io.swagger.v3.oas.models.media.Schema swSchema, Schema childSchema) {
        if (!StringUtils.isBlank(swSchema.get$ref())) {
            String refName = typeRefNameResolver.resolve(swSchema.get$ref());
            return SchemaStoreProvider.provide().get(refName).orElseThrow(() -> new IllegalStateException("Reference not found for " + swSchema.get$ref()));
        }

        Schema.Builder schemaBuilder = new Schema.Builder(swSchema.getType()).schema(childSchema);
        Map<String, io.swagger.v3.oas.models.media.Schema> properties = swSchema.getProperties();
        if (properties != null) {
            List<SchemaAttribute> attributes = properties.entrySet()
                .stream()
                .map(e -> new SchemaAttribute(e.getKey()))
                .collect(toList());
            schemaBuilder.schemaAttributes(attributes);
        }
        List<String> enumValues = swSchema.getEnum();
        if (CollectionUtils.isNotEmpty(enumValues)) {
            schemaBuilder.enumValues(enumValues);
        }
        return schemaBuilder.build();
    }
}
