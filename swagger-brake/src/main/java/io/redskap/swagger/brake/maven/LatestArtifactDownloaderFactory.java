package io.redskap.swagger.brake.maven;

import io.redskap.swagger.brake.runner.Options;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LatestArtifactDownloaderFactory {
    private final ApplicationContext applicationContext;

    public LatestArtifactDownloader create(Options options) {
        if (StringUtils.isNotBlank(options.getMavenRepoUrl())) {
            return applicationContext.getBean("maven2LatestArtifactDownloader", LatestArtifactDownloader.class);
        } else {
            throw new RuntimeException("Cannot create downloader");
        }
    }
}
