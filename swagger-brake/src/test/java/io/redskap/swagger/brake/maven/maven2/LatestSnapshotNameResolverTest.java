package io.redskap.swagger.brake.maven.maven2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import io.redskap.swagger.brake.maven.model.MavenSnapshot;
import io.redskap.swagger.brake.maven.model.MavenVersioning;
import io.redskap.swagger.brake.maven.url.UrlFactory;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LatestSnapshotNameResolverTest {
    @Mock
    private UrlFactory urlFactory;

    @Mock
    private MavenMetadataDownloader metadataDownloader;

    @Mock
    private RepositoryRequestFactory requestFactory;

    @InjectMocks
    private LatestSnapshotNameResolver underTest;

    @Test
    public void testResolveShouldWorkProperly() {
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
        String result = underTest.resolve(options, latestVersion);
        // then
        assertThat(result).isEqualTo(expected);
    }
}