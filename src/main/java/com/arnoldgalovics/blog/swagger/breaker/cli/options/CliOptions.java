package com.arnoldgalovics.blog.swagger.breaker.cli.options;

public abstract class CliOptions {
    public static final String OLD_API_PATH = "old-api";
    public static final String NEW_API_PATH = "new-api";
    public static final String HELP = "help";
    public static final String VERBOSE = "verbose";

    public static final String UPLOAD_ENABLED = "upload";
    public static final String API_SERVER = "api-server";
    public static final String PROJECT = "project";

    public static String getAsCliOption(String option) {
        return "--" + option;
    }
}
