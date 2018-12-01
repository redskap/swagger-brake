package io.redskap.swagger.brake.runner;

import lombok.Data;

@Data
public class Options {
    private String oldApiPath;
    private String newApiPath;

    private OutputFormat outputFormat;
    private String outputFilePath;

    private String mavenRepoUrl;
    private String groupId;
    private String artifactId;
    private String mavenRepoUsername;
    private String mavenRepoPassword;
}
