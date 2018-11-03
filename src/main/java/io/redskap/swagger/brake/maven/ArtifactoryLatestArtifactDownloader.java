package io.redskap.swagger.brake.maven;

import static java.lang.String.format;

import java.io.File;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArtifactoryLatestArtifactDownloader implements LatestArtifactDownloader {
    private final MavenMetadataDownloader metadataDownloader;
    private final TemporaryJarFileDownloader temporaryJarFileDownloader;

    @Override
    public File download(String repoUrl, String groupId, String artifactId) {
        String groupPath = groupId.replaceAll("\\.", "/");
        String artifactBaseUrl = format("%s/%s/%s", repoUrl, groupPath, artifactId);
        String latestVersion = getLatestArtifactVersion(artifactBaseUrl);
        String latestArtifactUrl = format("%s/%s/%s-%s.jar", artifactBaseUrl, latestVersion, artifactId, latestVersion);
        return temporaryJarFileDownloader.download(groupId, artifactId, latestVersion, latestArtifactUrl);
    }

    private String getLatestArtifactVersion(String artifactUrl) {
        MavenMetadata mavenMetadata = metadataDownloader.download(artifactUrl);
        return mavenMetadata.getVersioning().getLatest();
    }
}
