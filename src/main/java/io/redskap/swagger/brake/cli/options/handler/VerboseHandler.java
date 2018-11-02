package io.redskap.swagger.brake.cli.options.handler;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.redskap.swagger.brake.cli.options.CliOptions;
import io.redskap.swagger.brake.runner.Options;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class VerboseHandler implements CliOptionHandler {
    @Override
    public void handle(String propertyValue, Options options) {
        if (propertyValue == null) {
            return;
        }

        if (propertyValue.isEmpty()) {
            Logger logger = (Logger) LoggerFactory.getLogger("io.redskap.swagger.brake");
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
