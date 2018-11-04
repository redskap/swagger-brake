package io.redskap.swagger.brake.cli.options;

public abstract class CliOptions {
    public static final String OLD_API_PATH = "old-api";
    public static final String NEW_API_PATH = "new-api";
    public static final String HELP = "help";
    public static final String VERBOSE = "verbose";

    public static final String PROJECT = "project";
    public static final String OUTPUT_FORMAT = "output-format";
    public static final String OUTPUT_PATH = "output-path";

    public static final String MAVEN_REPO_URL = "maven-repo-url";
    public static final String ARTIFACT_ID = "artifactId";
    public static final String GROUP_ID = "groupId";

    public static String getAsCliOption(String option) {
        return "--" + option;
    }
}
