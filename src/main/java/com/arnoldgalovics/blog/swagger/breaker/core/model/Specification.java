package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Collection;
import java.util.Optional;

import com.arnoldgalovics.blog.swagger.breaker.core.model.service.SchemaStore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Specification {
    private final Collection<Path> paths;
    private final SchemaStore schemaStore;

    public Optional<Path> getPath(Path path) {
        return getPath(path.getPath(), path.getMethod());
    }

    public Optional<Path> getPath(String path, HttpMethod method) {
        return paths.stream().filter(p -> path.equals(p.getPath())).filter(p -> method.equals(p.getMethod())).findAny();
    }
}
