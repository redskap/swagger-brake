package io.redskap.swagger.brake.maven.maven2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Maven2LatestArtifactDownloaderTest {
    @Mock
    private LatestArtifactVersionResolver latestArtifactVersionResolver;

    @Mock
    private LatestSnapshotNameResolver latestSnapshotNameResolver;

    @Mock
    private LatestJarArtifactDownloader latestJarArtifactDownloader;

    @InjectMocks
    private Maven2LatestArtifactDownloader underTest;

    @Test
    public void testDownloadWorksCorrectly() {
        // given
        String latestVersion = "a";
        String latestSnapshotName = "b";
        File expectedFile = mock(File.class);
        DownloadOptions downloadOptions = mock(DownloadOptions.class);

        given(latestArtifactVersionResolver.resolve(downloadOptions)).willReturn(latestVersion);
        given(latestSnapshotNameResolver.resolve(downloadOptions, latestVersion)).willReturn(latestSnapshotName);
        given(latestJarArtifactDownloader.download(downloadOptions, latestSnapshotName, latestVersion)).willReturn(expectedFile);
        // when
        File result = underTest.download(downloadOptions);
        // then
        assertThat(result).isEqualTo(expectedFile);
    }
}