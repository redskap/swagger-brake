package io.redskap.swagger.brake.maven.url;

import static java.lang.String.format;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.springframework.stereotype.Component;

@Component
public class UrlFactory {
    public String createLatestArtifactSnapshotMetadataUrl(DownloadOptions options, String latestVersion) {
        String artifactBasePathUrl = createLatestArtifactBasePathUrl(options);
        return format("%s/%s/maven-metadata.xml", artifactBasePathUrl, latestVersion);
    }

    public String createLatestArtifactVersionMetadataUrl(DownloadOptions options) {
        String artifactBasePathUrl = createLatestArtifactBasePathUrl(options);
        return format("%s/maven-metadata.xml", artifactBasePathUrl);
    }

    public String createLatestArtifactUrl(DownloadOptions options, String latestVersion, String latestSnapshotName) {
        String artifactBasePathUrl = createLatestArtifactBasePathUrl(options);
        return format("%s/%s/%s.jar", artifactBasePathUrl, latestVersion, latestSnapshotName);
    }

    private String createLatestArtifactBasePathUrl(DownloadOptions options) {
        String groupPath = options.getGroupId().replaceAll("\\.", "/");
        return format("%s/%s/%s", options.getRepoUrl(), groupPath, options.getArtifactId());
    }
}
