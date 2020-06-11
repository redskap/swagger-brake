package io.redskap.swagger.brake.maven.maven2;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LatestArtifactVersionResolver {
    private final Maven2UrlFactory urlFactory;
    private final MavenMetadataDownloader metadataDownloader;
    private final RepositoryRequestFactory requestFactory;

    String resolve(DownloadOptions options) {
        String metadataUrl = urlFactory.createLatestArtifactVersionMetadataUrl(options);
        MavenMetadata mavenMetadata = metadataDownloader.download(requestFactory.create(metadataUrl, options));
        return mavenMetadata.getVersioning().getLatest();
    }
}
