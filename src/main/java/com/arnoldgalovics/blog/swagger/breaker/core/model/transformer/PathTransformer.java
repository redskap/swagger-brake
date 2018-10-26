package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PathTransformer implements Transformer<Paths, Collection<Path>> {
    private final PathItemTransformer pathItemTransformer;

    @Override
    public Collection<Path> transform(Paths from) {
        if (from == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        return from.entrySet().stream().map(e -> transform(e.getKey(), e.getValue())).flatMap(Collection::stream).collect(toList());
    }

    private Collection<Path> transform(String path, PathItem pathItem) {
        return pathItemTransformer.transform(pathItem).stream().map(detail -> new Path(path, detail.getMethod(), detail.getResponses())).collect(toList());
    }
}
