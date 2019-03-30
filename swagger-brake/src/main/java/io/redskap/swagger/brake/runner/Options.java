package io.redskap.swagger.brake.runner;

import java.util.Collections;
import java.util.Set;

import lombok.Data;

@Data
public class Options {
    private String oldApiPath;
    private String newApiPath;

    private Set<OutputFormat> outputFormats = Collections.emptySet();
    private String outputFilePath;

    private String mavenRepoUrl;
    private String groupId;
    private String artifactId;
    private String mavenRepoUsername;
    private String mavenRepoPassword;

    private Boolean deprecatedApiDeletionAllowed;
}
