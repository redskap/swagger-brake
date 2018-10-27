package com.arnoldgalovics.blog.swagger.breaker.cli.options;

public abstract class CliOptions {
    public static final String OLD_API_PATH = "old-api";
    public static final String NEW_API_PATH = "new-api";
    public static final String HELP = "help";

    public static String getAsCliOption(String option) {
        return "--" + option;
    }
}
