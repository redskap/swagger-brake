package io.redskap.swagger.brake.maven.maven2;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.LatestArtifactDownloader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("maven2LatestArtifactDownloader")
@RequiredArgsConstructor
class Maven2LatestArtifactDownloader implements LatestArtifactDownloader {
    private final LatestArtifactVersionResolver latestArtifactVersionResolver;
    private final LatestSnapshotNameResolver latestSnapshotNameResolver;
    private final LatestJarArtifactDownloader latestJarArtifactDownloader;

    @Override
    public File download(DownloadOptions options) {
        String latestVersion = latestArtifactVersionResolver.resolve(options);
        String latestSnapshotName = latestSnapshotNameResolver.resolve(options, latestVersion);
        return latestJarArtifactDownloader.download(options, latestSnapshotName, latestVersion);
    }
}
