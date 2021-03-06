package io.redskap.swagger.brake.core.rule;

import java.util.Set;

import io.redskap.swagger.brake.core.CheckerOptions;
import io.redskap.swagger.brake.core.CheckerOptionsProvider;
import io.redskap.swagger.brake.core.model.Path;
import io.redskap.swagger.brake.core.util.PathNormalizer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PathSkipper {
    private final CheckerOptionsProvider checkerOptionsProvider;

    public boolean shouldSkip(Path path) {
        return path.isBetaApi() || isPathExcluded(path);
    }

    private boolean isPathExcluded(Path path) {
        CheckerOptions checkerOptions = checkerOptionsProvider.get();
        if (checkerOptions != null) {
            Set<String> excludedPaths = checkerOptions.getExcludedPaths();
            for (String excludedPath : excludedPaths) {
                String normalizedExcludedPath = PathNormalizer.normalizePathSlashes(excludedPath);
                String normalizedPath = PathNormalizer.normalizePathSlashes(path.getPath());
                if (normalizedPath.startsWith(normalizedExcludedPath)) {
                    return true;
                }
            }
        }
        return false;
    }
}
