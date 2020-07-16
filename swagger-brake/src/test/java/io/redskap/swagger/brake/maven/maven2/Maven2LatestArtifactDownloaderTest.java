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
    private LatestArtifactNameResolver latestArtifactNameResolver;

    @Mock
    private LatestJarArtifactDownloader latestJarArtifactDownloader;

    @InjectMocks
    private Maven2LatestArtifactDownloader underTest;

    @Test
    public void testDownloadWorksCorrectlyForSnapshot() {
        // given
        String latestVersion = "1.0.0-SNAPSHOT";
        String latestArtifactName = "b";
        File expectedFile = mock(File.class);
        DownloadOptions downloadOptions = mock(DownloadOptions.class);

        given(latestArtifactVersionResolver.resolve(downloadOptions)).willReturn(latestVersion);
        given(latestArtifactNameResolver.resolveSnapshot(downloadOptions, latestVersion)).willReturn(latestArtifactName);
        given(latestJarArtifactDownloader.download(downloadOptions, latestArtifactName, latestVersion)).willReturn(expectedFile);
        // when
        File result = underTest.download(downloadOptions);
        // then
        assertThat(result).isEqualTo(expectedFile);
    }

    @Test
    public void testDownloadWorksCorrectlyForRelease() {
        // given
        String latestVersion = "1.0.0";
        String latestArtifactName = "b";
        File expectedFile = mock(File.class);
        DownloadOptions downloadOptions = mock(DownloadOptions.class);

        given(latestArtifactVersionResolver.resolve(downloadOptions)).willReturn(latestVersion);
        given(latestArtifactNameResolver.resolveRelease(downloadOptions, latestVersion)).willReturn(latestArtifactName);
        given(latestJarArtifactDownloader.download(downloadOptions, latestArtifactName, latestVersion)).willReturn(expectedFile);
        // when
        File result = underTest.download(downloadOptions);
        // then
        assertThat(result).isEqualTo(expectedFile);
    }

    @Test
    public void testDownloadWorksCorrectlyForReleaseWhenOnlyReleaseRepoIsSet() {
        // given
        String latestArtifactName = "b";
        File expectedFile = mock(File.class);
        DownloadOptions downloadOptions = mock(DownloadOptions.class);

        given(latestArtifactNameResolver.resolveRelease(downloadOptions, latestVersion)).willReturn(latestArtifactName);
        given(latestJarArtifactDownloader.download(downloadOptions, latestArtifactName, latestVersion)).willReturn(expectedFile);
        // when
        File result = underTest.download(downloadOptions);
        // then
        assertThat(result).isEqualTo(expectedFile);
    }
}