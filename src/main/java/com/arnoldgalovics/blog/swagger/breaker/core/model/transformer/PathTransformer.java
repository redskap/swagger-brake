package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.arnoldgalovics.blog.swagger.breaker.core.model.Path;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import org.springframework.stereotype.Component;

@Component
public class PathTransformer implements Transformer<Paths, Collection<Path>> {
    @Override
    public Collection<Path> transform(Paths from) {
        if (from == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        return from.entrySet().stream().map(e -> transform(e.getKey(), e.getValue())).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private Collection<Path> transform(String path, PathItem pathItem) {
        return getAvailableMethods(pathItem).stream().map(method -> new Path(path, method)).collect(Collectors.toList());
    }

    private Collection<String> getAvailableMethods(PathItem pathItem) {
        Collection<String> result = new ArrayList<>();
        Optional.ofNullable(pathItem.getGet()).ifPresent(e -> result.add("GET"));
        Optional.ofNullable(pathItem.getPost()).ifPresent(e -> result.add("POST"));
        Optional.ofNullable(pathItem.getPut()).ifPresent(e -> result.add("PUT"));
        Optional.ofNullable(pathItem.getDelete()).ifPresent(e -> result.add("DELETE"));
        return result;
    }


}
