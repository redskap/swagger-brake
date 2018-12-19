package io.redskap.swagger.brake.core.model.transformer;

import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.Set;

import io.redskap.swagger.brake.core.model.MediaType;
import io.redskap.swagger.brake.core.model.Response;
import io.redskap.swagger.brake.core.model.Schema;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiResponseTransformer implements Transformer<Pair<String, ApiResponse>, Response> {
    private final MediaTypeTransformer mediaTypeTransformer;

    @Override
    public Response transform(Pair<String, ApiResponse> from) {
        Map<MediaType, Schema> schemaRefs = emptyMap();
        Content content = from.getValue().getContent();
        if (content != null) {
            Set<Map.Entry<String, io.swagger.v3.oas.models.media.MediaType>> entries = content.entrySet();
            schemaRefs = entries.stream().collect(toMap(e -> new MediaType(e.getKey()), e -> mediaTypeTransformer.transform(e.getValue())));
        }
        return new Response(from.getKey(), schemaRefs);
    }
}
