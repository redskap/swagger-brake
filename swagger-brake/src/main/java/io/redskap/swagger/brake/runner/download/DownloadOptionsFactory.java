package io.redskap.swagger.brake.runner.download;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.runner.Options;
import org.springframework.stereotype.Component;

@Component
class DownloadOptionsFactory {
    public DownloadOptions create(Options options) {
        DownloadOptions result = new DownloadOptions();
        result.setRepoUrl(options.getMavenRepoUrl());
        result.setSnapshotRepoUrl(options.getMavenSnapshotRepoUrl());
        result.setGroupId(options.getGroupId());
        result.setArtifactId(options.getArtifactId());
        result.setUsername(options.getMavenRepoUsername());
        result.setPassword(options.getMavenRepoPassword());
        result.setCurrentArtifactVersion(options.getCurrentArtifactVersion());
        return result;
    }
}
