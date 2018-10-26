package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.*;
import java.util.function.Function;

import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Response;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.ImmutablePair;
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

    private final ApiResponseTransformer apiResponseTransformer;

    @Override
    public Collection<PathDetail> transform(PathItem from) {
        Collection<PathDetail> result = new ArrayList<>();
        for (Map.Entry<HttpMethod, Function<PathItem, Operation>> e : MAPPERS.entrySet()) {
            Operation operation = e.getValue().apply(from);
            if (operation != null) {
                HttpMethod key = e.getKey();
                Set<Map.Entry<String, ApiResponse>> responses = operation.getResponses().entrySet();
                List<Response> transformerResponses = responses.stream()
                    .map(entry -> new ImmutablePair<>(entry.getKey(), entry.getValue()))
                    .map(apiResponseTransformer::transform)
                    .collect(toList());
                result.add(new PathDetail(key, transformerResponses));
            }
        }
        return result;
    }
}