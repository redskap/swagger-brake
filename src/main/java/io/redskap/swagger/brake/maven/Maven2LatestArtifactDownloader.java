package io.redskap.swagger.brake.maven;

import static java.lang.String.format;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import io.redskap.swagger.brake.maven.http.HttpRequestFactory;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import io.redskap.swagger.brake.maven.model.MavenSnapshot;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class Maven2LatestArtifactDownloader implements LatestArtifactDownloader {
    private final MavenMetadataDownloader metadataDownloader;
    private final TemporaryJarFileDownloader temporaryJarFileDownloader;
    private final HttpRequestFactory httpRequestFactory;

    @Override
    public File download(DownloadOptions options) {
        String groupPath = options.getGroupId().replaceAll("\\.", "/");
        String artifactBaseUrl = format("%s/%s/%s", options.getRepoUrl(), groupPath, options.getArtifactId());
        String latestVersion = getLatestArtifactVersion(options, artifactBaseUrl);
        String latestSnapshotName = getLatestSnapshotName(options, artifactBaseUrl, latestVersion);
        String latestArtifactUrl = format("%s/%s/%s.jar", artifactBaseUrl, latestVersion, latestSnapshotName);
        return downloadLatestArtifact(options, latestArtifactUrl, latestVersion);
    }

    private File downloadLatestArtifact(DownloadOptions options, String url, String latestVersion) {
        HttpUriRequest httpRequest = createRepoRequest(options, url);
        return temporaryJarFileDownloader.download(httpRequest, options.getGroupId(), options.getArtifactId(), latestVersion);
    }

    private String getLatestSnapshotName(DownloadOptions options, String artifactUrl, String latestVersion) {
        String metadataUrl = format("%s/%s/maven-metadata.xml", artifactUrl, latestVersion);
        MavenMetadata snapshotMetadata = metadataDownloader.download(createRepoRequest(options, metadataUrl));
        MavenSnapshot snapshot = snapshotMetadata.getVersioning().getSnapshot();
        String snapshotVersion = latestVersion.replaceAll("SNAPSHOT", snapshot.getTimestamp());
        return format("%s-%s-%s", options.getArtifactId(), snapshotVersion, snapshot.getBuildNumber());
    }

    private String getLatestArtifactVersion(DownloadOptions options, String artifactUrl) {
        String metadataUrl = format("%s/maven-metadata.xml", artifactUrl);
        MavenMetadata mavenMetadata = metadataDownloader.download(createRepoRequest(options, metadataUrl));
        return mavenMetadata.getVersioning().getLatest();
    }

    private HttpUriRequest createRepoRequest(DownloadOptions options, String url) {
        try {
            if (options.isAuthenticationNeeded()) {
                return httpRequestFactory.authenticatedGet(url, options.getUsername(), options.getPassword());
            } else {
                return httpRequestFactory.get(url);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while creating the http request", e);
        }
    }
}
