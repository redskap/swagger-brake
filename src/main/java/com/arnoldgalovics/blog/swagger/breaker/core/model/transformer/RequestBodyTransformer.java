package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Set;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Request;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Schema;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestBodyTransformer implements Transformer<RequestBody, Request> {
    private final MediaTypeTransformer mediaTypeTransformer;

    @Override
    public Request transform(RequestBody from) {
        Set<Map.Entry<String, MediaType>> entries = from.getContent().entrySet();
        Map<String, Schema> mediaTypes = entries.stream().collect(toMap(Map.Entry::getKey, e -> mediaTypeTransformer.transform(e.getValue())));
        return new Request(mediaTypes);
    }
}
