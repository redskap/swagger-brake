package io.redskap.swagger.brake.runner;

import lombok.Data;

@Data
public class Options {
    private String oldApiPath;
    private String newApiPath;

    private String project;
    private OutputFormat outputFormat;
    private String outputFilePath;

    private String nexusRepoUrl;
    private String artifactoryRepoUrl;
    private String groupId;
    private String artifactId;
}
