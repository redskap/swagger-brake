package io.redskap.swagger.brake.maven;

import io.redskap.swagger.brake.runner.Options;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LatestArtifactDownloaderFactory {
    private final NexusLatestArtifactDownloader nexusDownloader;
    private final ArtifactoryLatestArtifactDownloader artifactoryDownloader;

    public LatestArtifactDownloader create(Options options) {
        if (StringUtils.isNotBlank(options.getNexusRepoUrl())) {
            return nexusDownloader;
        } else if (StringUtils.isNotBlank(options.getArtifactoryRepoUrl())) {
            return artifactoryDownloader;
        } else {
            throw new RuntimeException("Cannot create downloader");
        }
    }
}
