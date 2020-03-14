package io.redskap.swagger.brake.runner.download;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.File;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.LatestArtifactDownloader;
import io.redskap.swagger.brake.maven.LatestArtifactDownloaderFactory;
import io.redskap.swagger.brake.maven.jar.ApiFileJarResolver;
import io.redskap.swagger.brake.maven.jar.ApiFileResolverParameter;
import io.redskap.swagger.brake.runner.Options;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArtifactDownloaderHandlerTest {
    @Mock
    private LatestArtifactDownloaderFactory downloaderFactory;

    @Mock
    private ApiFileJarResolver swaggerFileResolver;

    @Mock
    private DownloadOptionsFactory downloadOptionsFactory;

    @InjectMocks
    private ArtifactDownloaderHandler underTest;

    @Test
    public void testHandleShouldNotDoAnythingWhenLatestArtifactDownloadIsDisabled() {
        // given
        Options options = new Options();
        options.setOldApiPath("oldApi");
        options.setNewApiPath("newApi");
        // when
        underTest.handle(options);
        // then
        verifyNoMoreInteractions(downloaderFactory, swaggerFileResolver);
    }

    @Test
    public void testHandleShouldWorkCorrectlyWhenLatestArtifactDownloadIsEnabled() {
        // given
        String groupId = "groupId";
        String artifactId = "artifactId";
        String mavenRepoUrl = "mavenRepoUrl";
        String mavenRepoUsername = "username";
        String mavenRepoPassword = "password";
        String apiFilename = "apiFilename";

        DownloadOptions downloadOptions = new DownloadOptions();
        downloadOptions.setRepoUrl(mavenRepoUrl);
        downloadOptions.setGroupId(groupId);
        downloadOptions.setArtifactId(artifactId);
        downloadOptions.setUsername(mavenRepoUsername);
        downloadOptions.setPassword(mavenRepoPassword);


        Options options = new Options();
        options.setNewApiPath("newApi");
        options.setGroupId(groupId);
        options.setArtifactId(artifactId);
        options.setMavenRepoUrl(mavenRepoUrl);
        options.setMavenRepoUsername(mavenRepoUsername);
        options.setMavenRepoPassword(mavenRepoPassword);
        options.setApiFilename(apiFilename);

        File mockDownloadedFile = mock(File.class);
        ApiFileResolverParameter apiFileResolverParameter = new ApiFileResolverParameter(mockDownloadedFile, apiFilename);

        File mockSwaggerFile = mock(File.class);
        String swaggerFilePath = "absoluteSwaggerFilePath";
        LatestArtifactDownloader downloader = mock(LatestArtifactDownloader.class);
        given(downloadOptionsFactory.create(mavenRepoUrl, groupId, artifactId, mavenRepoUsername, mavenRepoPassword)).willReturn(downloadOptions);
        given(downloaderFactory.create(options)).willReturn(downloader);
        given(downloader.download(downloadOptions)).willReturn(mockDownloadedFile);
        given(swaggerFileResolver.resolve(apiFileResolverParameter)).willReturn(mockSwaggerFile);
        given(mockSwaggerFile.getAbsolutePath()).willReturn(swaggerFilePath);
        // when
        underTest.handle(options);
        // then
        assertThat(options).extracting(Options::getOldApiPath).isEqualTo(swaggerFilePath);
    }
}