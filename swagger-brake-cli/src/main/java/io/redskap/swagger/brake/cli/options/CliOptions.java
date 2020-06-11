package io.redskap.swagger.brake.cli.options;

public abstract class CliOptions {
    public static final String OLD_API_PATH = "old-api";
    public static final String NEW_API_PATH = "new-api";
    public static final String HELP = "help";
    public static final String VERBOSE = "verbose";

    public static final String OUTPUT_FORMAT = "output-format";
    public static final String OUTPUT_PATH = "output-path";

    public static final String MAVEN_REPO_URL = "maven-repo-url";
    public static final String MAVEN_SNAPSHOT_REPO_URL = "maven-snapshot-repo-url";
    public static final String ARTIFACT_ID = "artifactId";
    public static final String GROUP_ID = "groupId";
    public static final String CURRENT_ARTIFACT_VERSION = "current-artifact-version";

    public static final String DEPRECATED_API_DELETION_ALLOWED = "deprecated-api-deletion-allowed";

    public static final String API_FILENAME = "api-filename";
    public static final String BETA_API_EXTENSION_NAME = "beta-api-extension-name";

    public static String getAsCliOption(String option) {
        return "--" + option;
    }
}
