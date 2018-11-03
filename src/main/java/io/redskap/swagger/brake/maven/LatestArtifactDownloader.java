package io.redskap.swagger.brake.maven;

import java.io.File;

public interface LatestArtifactDownloader {
    File download(String repoUrl, String groupId, String artifactId);
}
