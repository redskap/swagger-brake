package io.redskap.swagger.brake.runner;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.File;

import io.redskap.swagger.brake.maven.LatestArtifactDownloaderFactory;
import io.redskap.swagger.brake.maven.jar.SwaggerFileJarResolver;
import io.redskap.swagger.brake.runner.exception.LatestArtifactDownloadException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArtifactDownloaderHandler {
    private final LatestArtifactDownloaderFactory downloaderFactory;
    private final SwaggerFileJarResolver swaggerFileResolver;

    public void handle(Options options) {
        if (isLatestArtifactDownloadEnabled(options)) {
            String url = options.getMavenRepoUrl();
            String groupId = options.getGroupId();
            String artifactId = options.getArtifactId();
            try {
                log.info("Downloading latest artifact from repository '{}' with groupId '{}' artifactId '{}'", url, groupId, artifactId);
                File apiJar = downloaderFactory.create(options).download(url, groupId, artifactId);
                File swaggerFile = swaggerFileResolver.resolve(apiJar);
                options.setOldApiPath(swaggerFile.getAbsolutePath());
            } catch (Exception e) {
                throw new LatestArtifactDownloadException("Error while downloading the latest version of the artifact", e);
            }
        }
    }

    private boolean isLatestArtifactDownloadEnabled(Options options) {
        return isNotBlank(options.getMavenRepoUrl()) && isNotBlank(options.getGroupId()) && isNotBlank(options.getArtifactId());
    }
}
