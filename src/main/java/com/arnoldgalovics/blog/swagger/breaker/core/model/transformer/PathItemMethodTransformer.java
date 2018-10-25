package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import org.springframework.stereotype.Component;

@Component
public class PathItemMethodTransformer implements Transformer<PathItem, Collection<HttpMethod>> {
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

    @Override
    public Collection<HttpMethod> transform(PathItem from) {
        return MAPPERS.entrySet().stream().filter(e -> e.getValue().apply(from) != null).map(Map.Entry::getKey).collect(toList());
    }
}
