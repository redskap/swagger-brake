package com.arnoldgalovics.blog.swagger.breaker.cli.options.handler;

import com.arnoldgalovics.blog.swagger.breaker.cli.options.CliOptions;
import com.arnoldgalovics.blog.swagger.breaker.runner.Options;
import org.springframework.stereotype.Component;

@Component
public class UploadEnabledHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (propertyValue == null) {
            return;
        }

        if (propertyValue.isEmpty()) {
            options.setUploadEnabled(true);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.UPLOAD_ENABLED;
    }
}
