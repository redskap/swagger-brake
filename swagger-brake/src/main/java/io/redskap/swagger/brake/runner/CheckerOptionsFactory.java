package io.redskap.swagger.brake.runner;

import io.redskap.swagger.brake.core.CheckerOptions;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Component;

@Component
public class CheckerOptionsFactory {
    public CheckerOptions create(Options options) {
        CheckerOptions checkerOptions = new CheckerOptions();
        checkerOptions.setDeprecatedApiDeletionAllowed(isDeprecatedApiDeletionAllowed(options));
        return checkerOptions;
    }

    private boolean isDeprecatedApiDeletionAllowed(Options options) {
        return BooleanUtils.toBooleanDefaultIfNull(options.getDeprecatedApiDeletionAllowed(), true);
    }
}
