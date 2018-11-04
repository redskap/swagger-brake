package io.redskap.swagger.brake.maven;

import io.redskap.swagger.brake.runner.Options;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LatestArtifactDownloaderFactory {
    private final Maven2LatestArtifactDownloader nexusDownloader;

    public LatestArtifactDownloader create(Options options) {
        if (StringUtils.isNotBlank(options.getMavenRepoUrl())) {
            return nexusDownloader;
        } else {
            throw new RuntimeException("Cannot create downloader");
        }
    }
}
