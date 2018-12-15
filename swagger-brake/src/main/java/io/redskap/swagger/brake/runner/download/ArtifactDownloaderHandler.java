package io.redskap.swagger.brake.runner.download;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.LatestArtifactDownloaderFactory;
import io.redskap.swagger.brake.maven.http.UnauthorizedException;
import io.redskap.swagger.brake.maven.jar.SwaggerFileJarResolver;
import io.redskap.swagger.brake.runner.Options;
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
    private final DownloadOptionsFactory downloadOptionsFactory;

    public void handle(Options options) {
        if (isLatestArtifactDownloadEnabled(options)) {
            String url = options.getMavenRepoUrl();
            String username = options.getMavenRepoUsername();
            String password = options.getMavenRepoPassword();
            String groupId = options.getGroupId();
            String artifactId = options.getArtifactId();
            try {
                log.info("Downloading latest artifact from repository '{}' with groupId '{}' artifactId '{}'", url, groupId, artifactId);
                DownloadOptions downloadOptions = downloadOptionsFactory.create(url, groupId, artifactId, username, password);
                File apiJar = downloaderFactory.create(options).download(downloadOptions);
                File swaggerFile = swaggerFileResolver.resolve(apiJar);
                options.setOldApiPath(swaggerFile.getAbsolutePath());
            } catch (UnauthorizedException e) {
                throw new LatestArtifactDownloadException("Cannot access Maven repository due to insufficient privileges. Consider providing username and password.", e);
            } catch (Exception e) {
                throw new LatestArtifactDownloadException("Error while downloading the latest version of the artifact", e);
            }
        }
    }

    private boolean isLatestArtifactDownloadEnabled(Options options) {
        return isNotBlank(options.getMavenRepoUrl()) && isNotBlank(options.getGroupId()) && isNotBlank(options.getArtifactId());
    }
}
