package io.redskap.swagger.brake.maven.maven2;

import static java.lang.String.format;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import io.redskap.swagger.brake.maven.model.MavenSnapshot;
import io.redskap.swagger.brake.maven.url.UrlFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class LatestSnapshotNameResolver {
    private final UrlFactory urlFactory;
    private final MavenMetadataDownloader metadataDownloader;
    private final RepositoryRequestFactory requestFactory;

    String resolve(DownloadOptions options, String latestVersion) {
        String metadataUrl = urlFactory.createLatestArtifactSnapshotMetadataUrl(options, latestVersion);
        MavenMetadata snapshotMetadata = metadataDownloader.download(requestFactory.create(metadataUrl, options));
        MavenSnapshot snapshot = snapshotMetadata.getVersioning().getSnapshot();
        String snapshotVersion = latestVersion.replaceAll("SNAPSHOT", snapshot.getTimestamp());
        return format("%s-%s-%s", snapshotMetadata.getArtifactId(), snapshotVersion, snapshot.getBuildNumber());
    }
}
