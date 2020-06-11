package io.redskap.swagger.brake.maven.maven2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LatestJarArtifactDownloaderTest {
    @Mock
    private Maven2UrlFactory urlFactory;

    @Mock
    private TemporaryJarFileDownloader temporaryJarFileDownloader;

    @Mock
    private RepositoryRequestFactory requestFactory;

    @InjectMocks
    private LatestJarArtifactDownloader underTest;

    @Test
    public void testDownloadShouldWorkProperly() {
        // given
        String url = "url";
        String latestVersion = "lv";
        String latestSnapshotName = "lsn";
        DownloadOptions options = mock(DownloadOptions.class);
        HttpUriRequest request = mock(HttpUriRequest.class);
        File expected = mock(File.class);
        given(urlFactory.createLatestArtifactUrl(options, latestVersion, latestSnapshotName)).willReturn(url);
        given(requestFactory.create(url, options)).willReturn(request);
        given(temporaryJarFileDownloader.download(request)).willReturn(expected);
        // when
        File result = underTest.download(options, latestSnapshotName, latestVersion);
        // then
        assertThat(result).isEqualTo(expected);
    }
}