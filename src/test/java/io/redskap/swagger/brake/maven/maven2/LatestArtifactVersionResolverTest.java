package io.redskap.swagger.brake.maven.maven2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.model.MavenMetadata;
import io.redskap.swagger.brake.maven.model.MavenVersioning;
import io.redskap.swagger.brake.maven.url.UrlFactory;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LatestArtifactVersionResolverTest {
    @Mock
    private UrlFactory urlFactory;

    @Mock
    private MavenMetadataDownloader metadataDownloader;

    @Mock
    private RepositoryRequestFactory requestFactory;

    @InjectMocks
    private LatestArtifactVersionResolver underTest;

    @Test
    public void testResolveShouldWorkProperly() {
        // given
        String expected = "latest";
        MavenMetadata mavenMetadata = new MavenMetadata("groupId", "artifactId",
            new MavenVersioning(expected, null));

        String metadataUrl = "url";
        DownloadOptions options = mock(DownloadOptions.class);
        HttpUriRequest metadataRequest = mock(HttpUriRequest.class);
        given(urlFactory.createLatestArtifactVersionMetadataUrl(options)).willReturn(metadataUrl);
        given(requestFactory.create(metadataUrl, options)).willReturn(metadataRequest);
        given(metadataDownloader.download(metadataRequest)).willReturn(mavenMetadata);
        // when
        String result = underTest.resolve(options);
        // then
        assertThat(result).isEqualTo(expected);
    }
}