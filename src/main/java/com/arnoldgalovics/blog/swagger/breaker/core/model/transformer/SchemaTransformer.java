package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Map;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaAttribute;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.SchemaStore;
import io.swagger.v3.oas.models.Components;
import org.springframework.stereotype.Component;

@Component
public class SchemaTransformer implements Transformer<Components, SchemaStore> {
    @Override
    public SchemaStore transform(Components from) {
        Collection<Schema> schemas = from.getSchemas().entrySet().stream().map(e -> new Schema(e.getKey(), transformAttributes(e.getValue()))).collect(toList());
        return new SchemaStore(schemas);
    }

    private Collection<SchemaAttribute> transformAttributes(io.swagger.v3.oas.models.media.Schema value) {
        return ((Map<String, io.swagger.v3.oas.models.media.Schema>) value.getProperties()).keySet().stream().map(SchemaAttribute::new).collect(toList());
    }
}
