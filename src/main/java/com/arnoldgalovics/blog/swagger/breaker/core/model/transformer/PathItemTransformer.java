package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.function.Function;

import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Response;
import com.arnoldgalovics.blog.swagger.breaker.core.model.SchemaRef;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.ResponseTypeRefNameResolver;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PathItemTransformer implements Transformer<PathItem, Collection<PathDetail>> {
    private static final Map<HttpMethod, Function<PathItem, Operation>> MAPPERS = new HashMap<>();

    static {
        MAPPERS.put(HttpMethod.GET, PathItem::getGet);
        MAPPERS.put(HttpMethod.POST, PathItem::getPost);
        MAPPERS.put(HttpMethod.PUT, PathItem::getPut);
        MAPPERS.put(HttpMethod.PATCH, PathItem::getPatch);
        MAPPERS.put(HttpMethod.DELETE, PathItem::getDelete);
        MAPPERS.put(HttpMethod.HEAD, PathItem::getHead);
        MAPPERS.put(HttpMethod.OPTIONS, PathItem::getOptions);
        MAPPERS.put(HttpMethod.TRACE, PathItem::getTrace);
    }

    private final ResponseTypeRefNameResolver responseTypeRefNameResolver;

    @Override
    public Collection<PathDetail> transform(PathItem from) {
        Collection<PathDetail> result = new ArrayList<>();
        for (Map.Entry<HttpMethod, Function<PathItem, Operation>> e : MAPPERS.entrySet()) {
            Operation operation = e.getValue().apply(from);
            if (operation != null) {
                HttpMethod key = e.getKey();
                List<Response> responses = operation.getResponses().entrySet().stream().map(this::transformResponse).collect(toList());
                result.add(new PathDetail(key, responses));
            }
        }
        return result;
    }

    private Response transformResponse(Map.Entry<String, ApiResponse> entry) {

        Collection<SchemaRef> schemaRefs = entry.getValue().getContent().entrySet().stream().map(this::transformMediaType).collect(toList());
        return new Response(entry.getKey(), schemaRefs);
    }

    private SchemaRef transformMediaType(Map.Entry<String, MediaType> entry) {
        String schemaTypeName = entry.getValue().getSchema().getClass().getSimpleName();
        String refName = responseTypeRefNameResolver.resolve(entry.getValue().getSchema());
        return new SchemaRef(entry.getKey(), schemaTypeName, refName);
    }
}