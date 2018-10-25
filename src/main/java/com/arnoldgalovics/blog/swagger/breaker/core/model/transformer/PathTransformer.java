package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import java.util.Collection;
import java.util.stream.Collectors;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PathTransformer implements Transformer<Paths, Collection<Path>> {
    private final PathItemMethodTransformer pathItemMethodTransformer;

    @Override
    public Collection<Path> transform(Paths from) {
        if (from == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        return from.entrySet().stream().map(e -> transform(e.getKey(), e.getValue())).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private Collection<Path> transform(String path, PathItem pathItem) {
        return pathItemMethodTransformer.transform(pathItem).stream().map(method -> new Path(path, method)).collect(Collectors.toList());
    }
}
