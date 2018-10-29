package com.arnoldgalovics.blog.swagger.breaker.cli.options.handler;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.arnoldgalovics.blog.swagger.breaker.cli.options.CliOptions;
import com.arnoldgalovics.blog.swagger.breaker.runner.Options;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class VerboseHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (propertyValue == null) {
            return;
        }

        if (propertyValue.isEmpty()) {
            Logger logger = (Logger) LoggerFactory.getLogger("com.arnoldgalovics.blog.swagger.breaker");
            logger.setLevel(Level.DEBUG);
        }
    }

    @Override
    public String getHandledPropertyName() {
        return CliOptions.VERBOSE;
    }

    @Override
    public String getHelpMessage() {
        return "Sets verbose logging";
    }
}
