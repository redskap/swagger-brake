package io.redskap.swagger.brake.maven.maven2;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.url.UrlFactory;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpUriRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LatestJarArtifactDownloader {
    private final UrlFactory urlFactory;
    private final TemporaryJarFileDownloader temporaryJarFileDownloader;
    private final RepositoryRequestFactory requestFactory;

    File download(DownloadOptions options, String latestSnapshotName, String latestVersion) {
        String url = urlFactory.createLatestArtifactUrl(options, latestVersion, latestSnapshotName);
        HttpUriRequest httpRequest = requestFactory.create(url, options);
        return temporaryJarFileDownloader.download(httpRequest);
    }
}
