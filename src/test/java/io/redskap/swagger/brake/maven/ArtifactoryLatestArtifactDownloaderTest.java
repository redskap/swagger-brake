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
public class ArtifactoryLatestArtifactDownloaderTest {
    @Mock
    private MavenMetadataDownloader metadataDownloader;

    @Mock
    private TemporaryJarFileDownloader temporaryJarFileDownloader;

    @InjectMocks
    private ArtifactoryLatestArtifactDownloader underTest;

    @Test
    public void testDownloadWorksCorrectly() {
        // given
        String repoUrl = "https://oss.jfrog.org/oss-snapshot-local";
        String groupId = "io.reactivex";
        String artifactId = "rxjava";
        String latestVersion = "2.0.0-DP0-SNAPSHOT";
        MavenMetadata artifactMetadata = new MavenMetadata(groupId, artifactId,
            new MavenVersioning(latestVersion, null));
        File expectedFile = mock(File.class);
        String groupIdWithSlashes = groupIdWithSlashes(groupId);
        given(metadataDownloader.download(format("%s/%s/%s", repoUrl, groupIdWithSlashes, artifactId))).willReturn(artifactMetadata);
        given(temporaryJarFileDownloader.download(groupId, artifactId, latestVersion,
            format("%s/%s/%s/%s/rxjava-2.0.0-DP0-SNAPSHOT.jar", repoUrl, groupIdWithSlashes, artifactId, latestVersion))).willReturn(expectedFile);
        // when
        File result = underTest.download(repoUrl, groupId, artifactId);
        // then
        assertThat(result).isEqualTo(expectedFile);
    }

    private String groupIdWithSlashes(String groupId) {
        return groupId.replaceAll("\\.", "/");
    }
}