package io.redskap.swagger.brake.core;

import org.springframework.stereotype.Component;

@Component
public class CheckerOptionsProvider {
    private CheckerOptions checkerOptions;

    public void set(CheckerOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        checkerOptions = options;
    }

    /**
     * Returns the {@link CheckerOptions} from the context.
     * @return the {@link CheckerOptions} if set, exception otherwise.
     * @throws RuntimeException if no {@link CheckerOptions} has been set.
     */
    public CheckerOptions get() {
        if (checkerOptions == null) {
            throw new RuntimeException("CheckerOptions has not been set up yet.");
        }
        return checkerOptions;
    }

}
