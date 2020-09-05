package io.redskap.swagger.brake.core.rule;

import io.redskap.swagger.brake.core.model.Path;
import org.springframework.stereotype.Component;

@Component
public class PathSkipper {
    public boolean shouldSkip(Path path) {
        return path.isBetaApi();
    }
}
