package io.redskap.swagger.brake.maven;

import lombok.Data;

@Data
public class DownloadOptions {
    private String repoUrl;
    private String groupId;
    private String artifactId;
    private String username;
    private String password;
}
