package io.redskap.swagger.brake.runner;

import io.redskap.swagger.brake.core.CheckerOptions;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CheckerOptionsFactory {
    public CheckerOptions create(Options options) {
        CheckerOptions checkerOptions = new CheckerOptions();
        checkerOptions.setDeprecatedApiDeletionAllowed(isDeprecatedApiDeletionAllowed(options));
        checkerOptions.setBetaApiExtensionName(getBetaApiExtensionName(checkerOptions.getBetaApiExtensionName(), options));
        return checkerOptions;
    }

    private String getBetaApiExtensionName(String defaultBetaApiExtensionName, Options options) {
        return StringUtils.defaultString(options.getBetaApiExtensionName(), defaultBetaApiExtensionName);
    }

    private boolean isDeprecatedApiDeletionAllowed(Options options) {
        return BooleanUtils.toBooleanDefaultIfNull(options.getDeprecatedApiDeletionAllowed(), true);
    }
}
