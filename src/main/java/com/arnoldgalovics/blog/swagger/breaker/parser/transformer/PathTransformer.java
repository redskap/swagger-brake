package com.arnoldgalovics.blog.swagger.breaker.parser.transformer;

import com.arnoldgalovics.blog.swagger.breaker.parser.Path;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class PathTransformer implements Transformer<Paths, Collection<Path>> {
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

    @Override
    public Collection<Path> transform(Paths from) {
        return from.entrySet().stream().map(e -> transform(e.getKey(), e.getValue())).flatMap(Collection::stream).collect(Collectors.toList());
    }
}
