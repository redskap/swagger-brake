package com.arnoldgalovics.blog.swagger.breaker.core.model.service;

import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.stereotype.Component;

@Component
public class ResponseTypeRefNameResolver implements Resolver<Schema, String> {
    @Override
    public String resolve(Schema from) {
        String refName = from.get$ref();
        if (from instanceof ArraySchema) {
            refName = ((ArraySchema) from).getItems().get$ref();
        }
        return getNameFromRef(refName);
    }

    private String getNameFromRef(String refName) {
        if (refName == null) {
            return null;
        }
        return refName.substring(refName.lastIndexOf("/") + 1);
    }
}
