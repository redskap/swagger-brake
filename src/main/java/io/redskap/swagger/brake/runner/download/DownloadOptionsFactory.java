package io.redskap.swagger.brake.runner.download;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.springframework.stereotype.Component;

@Component
class DownloadOptionsFactory {
    public DownloadOptions create(String mavenRepoUrl, String groupId, String artifactId, String mavenRepoUsername, String mavenRepoPassword) {
        DownloadOptions result = new DownloadOptions();
        result.setRepoUrl(mavenRepoUrl);
        result.setGroupId(groupId);
        result.setArtifactId(artifactId);
        result.setUsername(mavenRepoUsername);
        result.setPassword(mavenRepoPassword);
        return result;
    }
}
