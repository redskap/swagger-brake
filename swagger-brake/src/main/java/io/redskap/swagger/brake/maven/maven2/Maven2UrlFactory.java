package io.redskap.swagger.brake.maven.maven2;

import static java.lang.String.format;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.springframework.stereotype.Component;

@Component
public class Maven2UrlFactory {
    public String createLatestArtifactSnapshotMetadataUrl(DownloadOptions options, String latestVersion) {
        String artifactBasePathUrl = createLatestArtifactBasePathUrl(options, options.getSnapshotRepoUrl());
        return format("%s/%s/maven-metadata.xml", artifactBasePathUrl, latestVersion);
    }

    public String createLatestArtifactVersionMetadataUrl(DownloadOptions options) {
        String repoUrl = getRepoUrl(options);
        String artifactBasePathUrl = createLatestArtifactBasePathUrl(options, repoUrl);
        return format("%s/maven-metadata.xml", artifactBasePathUrl);
    }

    public String createLatestArtifactUrl(DownloadOptions options, String latestVersion, String latestSnapshotName) {
        String repoUrl = getRepoUrl(options);
        String artifactBasePathUrl = createLatestArtifactBasePathUrl(options, repoUrl);
        return format("%s/%s/%s.jar", artifactBasePathUrl, latestVersion, latestSnapshotName);
    }

    private String createLatestArtifactBasePathUrl(DownloadOptions options, String repoUrl) {
        String groupPath = options.getGroupId().replaceAll("\\.", "/");
        return format("%s/%s/%s", repoUrl, groupPath, options.getArtifactId());
    }

    private String getRepoUrl(DownloadOptions options) {
        String repoUrl = options.getRepoUrl();
        if (ArtifactVersionDecider.isSnapshot(options.getCurrentArtifactVersion())) {
            repoUrl = options.getSnapshotRepoUrl();
        }
        return repoUrl;
    }
}
