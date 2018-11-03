package io.redskap.swagger.brake.maven;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NexusLatestArtifactDownloaderTest {
    @Mock
    private MavenMetadataDownloader metadataDownloader;

    @Mock
    private TemporaryJarFileDownloader temporaryJarFileDownloader;

    @InjectMocks
    private NexusLatestArtifactDownloader underTest;

    @Test
    public void testDownloadWorksCorrectly() {
        // given
        String repoUrl = "https://oss.sonatype.org/content/repositories/snapshots";
        String groupId = "org.spockframework";
        String artifactId = "spock-core";
        String latestVersion = "1.3-groovy-2.5-SNAPSHOT";
        MavenMetadata artifactMetadata = new MavenMetadata(groupId, artifactId,
            new MavenVersioning(latestVersion, null));
        String snapshotTimestamp = "20181102.135329";
        String snapshotBuildNumber = "25";
        MavenMetadata latestArtifactMetadata = new MavenMetadata(groupId, artifactId,
            new MavenVersioning(latestVersion, new MavenSnapshot(snapshotTimestamp, snapshotBuildNumber)));
        File expectedFile = mock(File.class);
        String groupIdWithSlashes = groupIdWithSlashes(groupId);
        given(metadataDownloader.download(format("%s/%s/%s", repoUrl, groupIdWithSlashes, artifactId))).willReturn(artifactMetadata);
        given(metadataDownloader.download(format("%s/%s/%s/%s", repoUrl, groupIdWithSlashes, artifactId, latestVersion))).willReturn(latestArtifactMetadata);
        given(temporaryJarFileDownloader.download(groupId, artifactId, latestVersion,
            format("%s/%s/%s/%s/spock-core-1.3-groovy-2.5-20181102.135329-25.jar", repoUrl, groupIdWithSlashes, artifactId, latestVersion))).willReturn(expectedFile);
        // when
        File result = underTest.download(repoUrl, groupId, artifactId);
        // then
        assertThat(result).isEqualTo(expectedFile);
    }

    private String groupIdWithSlashes(String groupId) {
        return groupId.replaceAll("\\.", "/");
    }
}