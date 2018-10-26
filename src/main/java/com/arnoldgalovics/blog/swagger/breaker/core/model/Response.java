package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Collection;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Response {
    private final String code;
    private final Collection<SchemaRef> schemaRefs;

    public Optional<SchemaRef> getSchemaRefByMediaType(String mediaType) {
        return schemaRefs.stream().filter(r -> mediaType.equals(r.getMediaType())).findAny();
    }
}
