package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaRef;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.ResponseTypeRefNameResolver;
import io.swagger.v3.oas.models.media.MediaType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediaTypeTransformer implements Transformer<Pair<String, MediaType>, SchemaRef> {
    private final ResponseTypeRefNameResolver responseTypeRefNameResolver;

    @Override
    public SchemaRef transform(Pair<String, MediaType> from) {
        String schemaTypeName = from.getValue().getSchema().getClass().getSimpleName();
        String refName = responseTypeRefNameResolver.resolve(from.getValue().getSchema());
        return new SchemaRef(from.getKey(), schemaTypeName, refName);
    }
}
