package io.redskap.swagger.brake.maven.maven2;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.LatestArtifactDownloader;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("maven2LatestArtifactDownloader")
@RequiredArgsConstructor
class Maven2LatestArtifactDownloader implements LatestArtifactDownloader {
    private final LatestArtifactVersionResolver latestArtifactVersionResolver;
    private final LatestArtifactNameResolver latestArtifactNameResolver;
    private final LatestJarArtifactDownloader latestJarArtifactDownloader;

    @Override
    public File download(DownloadOptions options) {
        boolean isSnapshot;
        if (StringUtils.isNotBlank(options.getCurrentArtifactVersion())) {
            String latestVersion = latestArtifactVersionResolver.resolve(options);
            isSnapshot = ArtifactVersionDecider.isSnapshot(latestVersion);
        } else {

        }
        String latestFilename;
        if (isSnapshot) {
            latestFilename = latestArtifactNameResolver.resolveSnapshot(options, latestVersion);
        } else {
            latestFilename = latestArtifactNameResolver.resolveRelease(options, latestVersion);
        }
        return latestJarArtifactDownloader.download(options, latestFilename, latestVersion);
    }
}
