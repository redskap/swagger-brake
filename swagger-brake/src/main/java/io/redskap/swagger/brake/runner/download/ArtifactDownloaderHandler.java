package io.redskap.swagger.brake.runner.download;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.LatestArtifactDownloaderFactory;
import io.redskap.swagger.brake.maven.http.UnauthorizedException;
import io.redskap.swagger.brake.maven.jar.ApiFileJarResolver;
import io.redskap.swagger.brake.maven.jar.ApiFileResolverParameter;
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
    private final ApiFileJarResolver apiFileResolver;
    private final DownloadOptionsFactory downloadOptionsFactory;

    /**
     * Orchestrates the resolution and downloading the latest artifact based on the {@link Options} provided.
     * @param options the {@link Options}.
     */
    public void handle(Options options) {
        if (isLatestArtifactDownloadEnabled(options)) {
            try {
                String groupId = options.getGroupId();
                String artifactId = options.getArtifactId();
                log.info("Downloading latest artifact with groupId '{}' artifactId '{}'", groupId, artifactId);
                DownloadOptions downloadOptions = downloadOptionsFactory.create(options);
                File apiJar = downloaderFactory.create(options).download(downloadOptions);
                ApiFileResolverParameter apiFileResolverParameter = new ApiFileResolverParameter(apiJar, options.getApiFilename());
                File swaggerFile = apiFileResolver.resolve(apiFileResolverParameter);
                options.setOldApiPath(swaggerFile.getAbsolutePath());
            } catch (UnauthorizedException e) {
                throw new LatestArtifactDownloadException("Cannot access Maven repository due to insufficient privileges. Consider providing username and password.", e);
            } catch (Exception e) {
                throw new LatestArtifactDownloadException("Error while downloading the latest version of the artifact", e);
            }
        } else if (isLatestArtifactDownloadWronglyConfigured(options)) {
            log.warn("Seems like latest artifact resolution is intended to be used but missing some of the parameters");
        }
    }

    private boolean isLatestArtifactDownloadEnabled(Options options) {
        return isNotBlank(options.getMavenRepoUrl())
            && isNotBlank(options.getMavenSnapshotRepoUrl())
            && isNotBlank(options.getGroupId())
            && isNotBlank(options.getArtifactId())
            && isNotBlank(options.getCurrentArtifactVersion());
    }

    private boolean isLatestArtifactDownloadWronglyConfigured(Options options) {
        return isNotBlank(options.getMavenRepoUrl())
            || isNotBlank(options.getMavenSnapshotRepoUrl())
            || isNotBlank(options.getGroupId())
            || isNotBlank(options.getArtifactId())
            || isNotBlank(options.getCurrentArtifactVersion());
    }
}
