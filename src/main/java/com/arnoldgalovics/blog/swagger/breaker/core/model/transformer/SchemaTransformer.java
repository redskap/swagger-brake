package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Map;

import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaAttribute;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.stereotype.Component;

@Component
public class SchemaTransformer implements Transformer<Schema, com.arnoldgalovics.blog.swagger.breaker.core.model.Schema> {
    @Override
    public com.arnoldgalovics.blog.swagger.breaker.core.model.Schema transform(Schema from) {
        String type = from.getType();
        Collection<SchemaAttribute> schemaAttributes = transformAttributes(from);
        return new com.arnoldgalovics.blog.swagger.breaker.core.model.Schema(type, schemaAttributes);
    }

    private Collection<SchemaAttribute> transformAttributes(io.swagger.v3.oas.models.media.Schema value) {
        return ((Map<String, Schema>) value.getProperties()).keySet().stream().map(SchemaAttribute::new).collect(toList());
    }
}
