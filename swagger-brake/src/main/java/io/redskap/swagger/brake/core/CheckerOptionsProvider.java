package io.redskap.swagger.brake.core;

import org.springframework.stereotype.Component;

@Component
public class CheckerOptionsProvider {
    private CheckerOptions checkerOptions;

    void set(CheckerOptions options) {
        if (options == null) {
            throw new IllegalArgumentException("options cannot be null");
        }
        checkerOptions = options;
    }


    public CheckerOptions get() {
        if (checkerOptions == null) {
            throw new RuntimeException("CheckerOptions has not been set up yet.");
        }
        return checkerOptions;
    }

}
