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

    /**
     * Factory that constructs a {@link LatestArtifactDownloader} instance.
     * @param options the {@link Options} with which the {@link LatestArtifactDownloader} can be configured.
     * @return the created {@link LatestArtifactDownloader} instance.
     * @throws RuntimeException if the provided {@link Options} does not have the Options#mavenRepoUrl set.
     */
    public LatestArtifactDownloader create(Options options) {
        if (StringUtils.isNotBlank(options.getMavenRepoUrl())) {
            return applicationContext.getBean("maven2LatestArtifactDownloader", LatestArtifactDownloader.class);
        } else {
            throw new RuntimeException("Cannot create downloader");
        }
    }
}
