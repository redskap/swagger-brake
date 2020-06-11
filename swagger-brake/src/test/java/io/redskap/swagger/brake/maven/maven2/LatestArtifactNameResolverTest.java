package io.redskap.swagger.brake.maven.maven2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import io.redskap.swagger.brake.maven.model.MavenSnapshot;
import io.redskap.swagger.brake.maven.model.MavenVersioning;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LatestArtifactNameResolverTest {
    @Mock
    private Maven2UrlFactory urlFactory;

    @Mock
    private MavenMetadataDownloader metadataDownloader;

    @Mock
    private RepositoryRequestFactory requestFactory;

    @InjectMocks
    private LatestArtifactNameResolver underTest;

    @Test
    public void testResolveSnapshotShouldWorkProperly() {
        // given
        String latestVersion = "1.2.0-SNAPSHOT";
        String artifactId = "swagger-brake-example";
        String latestTimestamp = "20181118.221312";
        String latestBuildNumber = "2";
        String expected = "swagger-brake-example-1.2.0-20181118.221312-2";
        MavenMetadata mavenMetadata = new MavenMetadata("groupId", artifactId,
            new MavenVersioning(expected, new MavenSnapshot(latestTimestamp, latestBuildNumber)));

        String metadataUrl = "url";
        DownloadOptions options = mock(DownloadOptions.class);
        HttpUriRequest metadataRequest = mock(HttpUriRequest.class);
        given(urlFactory.createLatestArtifactSnapshotMetadataUrl(options, latestVersion)).willReturn(metadataUrl);
        given(requestFactory.create(metadataUrl, options)).willReturn(metadataRequest);
        given(metadataDownloader.download(metadataRequest)).willReturn(mavenMetadata);
        // when
        String result = underTest.resolveSnapshot(options, latestVersion);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testResolveReleaseShouldWorkProperly() {
        // given
        String latestVersion = "1.2.0";
        String artifactId = "swagger-brake-example";
        String expected = "swagger-brake-example-1.2.0";
        DownloadOptions options = new DownloadOptions();
        options.setArtifactId(artifactId);
        // when
        String result = underTest.resolveRelease(options, latestVersion);
        // then
        assertThat(result).isEqualTo(expected);
    }
}