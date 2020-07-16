package io.redskap.swagger.brake.runner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.Collection;
import java.util.Collections;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.CheckerOptions;
import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.LatestArtifactDownloader;
import io.redskap.swagger.brake.maven.LatestArtifactDownloaderFactory;
import io.redskap.swagger.brake.maven.jar.ApiFileJarResolver;
import io.redskap.swagger.brake.maven.jar.ApiFileResolverParameter;
import io.redskap.swagger.brake.report.Reporter;
import io.redskap.swagger.brake.report.ReporterFactory;
import io.redskap.swagger.brake.runner.download.ArtifactDownloaderHandler;
import io.redskap.swagger.brake.runner.download.DownloadOptionsFactory;
import io.redskap.swagger.brake.runner.openapi.OpenApiFactory;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
public class RunnerIntTest {
    @Mock
    private ReporterFactory reporterFactory;

    @Mock
    private OpenApiFactory openApiFactory;

    @Mock
    private Checker checker;

    @Spy
    private CheckerOptionsFactory checkerOptionsFactory;

    @Mock
    private LatestArtifactDownloaderFactory downloaderFactory;

    @Mock
    private ApiFileJarResolver apiFileResolver;

    @Mock
    private DownloadOptionsFactory downloadOptionsFactory;

    private ArtifactDownloaderHandler artifactDownloaderHandler;

    @Spy
    private OptionsValidator optionsValidator;

    private Runner underTest;

    @Before
    public void setUp() {
        artifactDownloaderHandler = new ArtifactDownloaderHandler(downloaderFactory, apiFileResolver, downloadOptionsFactory);
        underTest = new Runner(optionsValidator, artifactDownloaderHandler, openApiFactory, checkerOptionsFactory, checker, reporterFactory);
    }

    @Test
    public void testOptionValidationWorksWhenMavenConfigurationIsSetWithoutReleaseRepo() {
        // given
        String oldApiPath = "oldApiPath";
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenSnapshotRepoUrl("localhost:8080/repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        DownloadOptions downloadOptions = mock(DownloadOptions.class);
        given(downloadOptionsFactory.create(options)).willReturn(downloadOptions);
        LatestArtifactDownloader latestArtifactDownloader = mock(LatestArtifactDownloader.class);
        given(downloaderFactory.create(options)).willReturn(latestArtifactDownloader);
        File apiJar = mock(File.class);
        given(latestArtifactDownloader.download(downloadOptions)).willReturn(apiJar);
        File swaggerFile = mock(File.class);
        given(apiFileResolver.resolve(any(ApiFileResolverParameter.class))).willReturn(swaggerFile);
        given(swaggerFile.getAbsolutePath()).willReturn(oldApiPath);
        OpenAPI oldApi = mock(OpenAPI.class);
        OpenAPI newApi = mock(OpenAPI.class);
        given(openApiFactory.fromFile(oldApiPath)).willReturn(oldApi);
        given(openApiFactory.fromFile(options.getNewApiPath())).willReturn(newApi);
        given(checker.check(eq(oldApi), eq(newApi), any(CheckerOptions.class))).willReturn(Collections.emptyList());
        Reporter reporter = mock(Reporter.class);
        given(reporterFactory.create(options)).willReturn(reporter);
        // when
        Collection<BreakingChange> result = underTest.run(options);
        // then
        assertThat(result).isEmpty();
        verify(downloadOptionsFactory).create(options);
        verify(downloaderFactory).create(options);
        verify(latestArtifactDownloader).download(downloadOptions);
        verify(apiFileResolver).resolve(any(ApiFileResolverParameter.class));
        verify(openApiFactory).fromFile(oldApiPath);
        verify(openApiFactory).fromFile(options.getNewApiPath());
        verify(checker).check(eq(oldApi), eq(newApi), any(CheckerOptions.class));
        verify(reporter).report(anyList(), eq(options));
    }

    @Test
    public void testOptionValidationWorksWhenMavenConfigurationIsSetWithoutReleaseRepoAndVersion() {
        // given
        String oldApiPath = "oldApiPath";
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenSnapshotRepoUrl("localhost:8080/repo");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        DownloadOptions downloadOptions = mock(DownloadOptions.class);
        given(downloadOptionsFactory.create(options)).willReturn(downloadOptions);
        LatestArtifactDownloader latestArtifactDownloader = mock(LatestArtifactDownloader.class);
        given(downloaderFactory.create(options)).willReturn(latestArtifactDownloader);
        File apiJar = mock(File.class);
        given(latestArtifactDownloader.download(downloadOptions)).willReturn(apiJar);
        File swaggerFile = mock(File.class);
        given(apiFileResolver.resolve(any(ApiFileResolverParameter.class))).willReturn(swaggerFile);
        given(swaggerFile.getAbsolutePath()).willReturn(oldApiPath);
        OpenAPI oldApi = mock(OpenAPI.class);
        OpenAPI newApi = mock(OpenAPI.class);
        given(openApiFactory.fromFile(oldApiPath)).willReturn(oldApi);
        given(openApiFactory.fromFile(options.getNewApiPath())).willReturn(newApi);
        given(checker.check(eq(oldApi), eq(newApi), any(CheckerOptions.class))).willReturn(Collections.emptyList());
        Reporter reporter = mock(Reporter.class);
        given(reporterFactory.create(options)).willReturn(reporter);
        // when
        Collection<BreakingChange> result = underTest.run(options);
        // then
        assertThat(result).isEmpty();
        verify(downloadOptionsFactory).create(options);
        verify(downloaderFactory).create(options);
        verify(latestArtifactDownloader).download(downloadOptions);
        verify(apiFileResolver).resolve(any(ApiFileResolverParameter.class));
        verify(openApiFactory).fromFile(oldApiPath);
        verify(openApiFactory).fromFile(options.getNewApiPath());
        verify(checker).check(eq(oldApi), eq(newApi), any(CheckerOptions.class));
        verify(reporter).report(anyList(), eq(options));
    }

    @Test
    public void testOptionValidationWorksWhenMavenConfigurationIsSetWithoutSnapshotRepo() {
        // given
        String oldApiPath = "oldApiPath";
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setCurrentArtifactVersion("1.0.0");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        DownloadOptions downloadOptions = mock(DownloadOptions.class);
        given(downloadOptionsFactory.create(options)).willReturn(downloadOptions);
        LatestArtifactDownloader latestArtifactDownloader = mock(LatestArtifactDownloader.class);
        given(downloaderFactory.create(options)).willReturn(latestArtifactDownloader);
        File apiJar = mock(File.class);
        given(latestArtifactDownloader.download(downloadOptions)).willReturn(apiJar);
        File swaggerFile = mock(File.class);
        given(apiFileResolver.resolve(any(ApiFileResolverParameter.class))).willReturn(swaggerFile);
        given(swaggerFile.getAbsolutePath()).willReturn(oldApiPath);
        OpenAPI oldApi = mock(OpenAPI.class);
        OpenAPI newApi = mock(OpenAPI.class);
        given(openApiFactory.fromFile(oldApiPath)).willReturn(oldApi);
        given(openApiFactory.fromFile(options.getNewApiPath())).willReturn(newApi);
        given(checker.check(eq(oldApi), eq(newApi), any(CheckerOptions.class))).willReturn(Collections.emptyList());
        Reporter reporter = mock(Reporter.class);
        given(reporterFactory.create(options)).willReturn(reporter);
        // when
        Collection<BreakingChange> result = underTest.run(options);
        // then
        assertThat(result).isEmpty();
        verify(downloadOptionsFactory).create(options);
        verify(downloaderFactory).create(options);
        verify(latestArtifactDownloader).download(downloadOptions);
        verify(apiFileResolver).resolve(any(ApiFileResolverParameter.class));
        verify(openApiFactory).fromFile(oldApiPath);
        verify(openApiFactory).fromFile(options.getNewApiPath());
        verify(checker).check(eq(oldApi), eq(newApi), any(CheckerOptions.class));
        verify(reporter).report(anyList(), eq(options));
    }

    @Test
    public void testOptionValidationWorksWhenMavenConfigurationIsSetWithoutSnapshotRepoAndVersion() {
        // given
        String oldApiPath = "oldApiPath";
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        DownloadOptions downloadOptions = mock(DownloadOptions.class);
        given(downloadOptionsFactory.create(options)).willReturn(downloadOptions);
        LatestArtifactDownloader latestArtifactDownloader = mock(LatestArtifactDownloader.class);
        given(downloaderFactory.create(options)).willReturn(latestArtifactDownloader);
        File apiJar = mock(File.class);
        given(latestArtifactDownloader.download(downloadOptions)).willReturn(apiJar);
        File swaggerFile = mock(File.class);
        given(apiFileResolver.resolve(any(ApiFileResolverParameter.class))).willReturn(swaggerFile);
        given(swaggerFile.getAbsolutePath()).willReturn(oldApiPath);
        OpenAPI oldApi = mock(OpenAPI.class);
        OpenAPI newApi = mock(OpenAPI.class);
        given(openApiFactory.fromFile(oldApiPath)).willReturn(oldApi);
        given(openApiFactory.fromFile(options.getNewApiPath())).willReturn(newApi);
        given(checker.check(eq(oldApi), eq(newApi), any(CheckerOptions.class))).willReturn(Collections.emptyList());
        Reporter reporter = mock(Reporter.class);
        given(reporterFactory.create(options)).willReturn(reporter);
        // when
        Collection<BreakingChange> result = underTest.run(options);
        // then
        assertThat(result).isEmpty();
        verify(downloadOptionsFactory).create(options);
        verify(downloaderFactory).create(options);
        verify(latestArtifactDownloader).download(downloadOptions);
        verify(apiFileResolver).resolve(any(ApiFileResolverParameter.class));
        verify(openApiFactory).fromFile(oldApiPath);
        verify(openApiFactory).fromFile(options.getNewApiPath());
        verify(checker).check(eq(oldApi), eq(newApi), any(CheckerOptions.class));
        verify(reporter).report(anyList(), eq(options));
    }

    @Test
    public void testOptionValidationWorksWhenMavenConfigurationIsSetWithAllRepos() {
        // given
        String oldApiPath = "oldApiPath";
        Options options = new Options();
        options.setNewApiPath("something");
        options.setMavenSnapshotRepoUrl("localhost:8080/snapshot-repo");
        options.setMavenRepoUrl("localhost:8080/repo");
        options.setCurrentArtifactVersion("1.0.0-SNAPSHOT");
        options.setGroupId("io.redskap");
        options.setArtifactId("swagger-brake");
        DownloadOptions downloadOptions = mock(DownloadOptions.class);
        given(downloadOptionsFactory.create(options)).willReturn(downloadOptions);
        LatestArtifactDownloader latestArtifactDownloader = mock(LatestArtifactDownloader.class);
        given(downloaderFactory.create(options)).willReturn(latestArtifactDownloader);
        File apiJar = mock(File.class);
        given(latestArtifactDownloader.download(downloadOptions)).willReturn(apiJar);
        File swaggerFile = mock(File.class);
        given(apiFileResolver.resolve(any(ApiFileResolverParameter.class))).willReturn(swaggerFile);
        given(swaggerFile.getAbsolutePath()).willReturn(oldApiPath);
        OpenAPI oldApi = mock(OpenAPI.class);
        OpenAPI newApi = mock(OpenAPI.class);
        given(openApiFactory.fromFile(oldApiPath)).willReturn(oldApi);
        given(openApiFactory.fromFile(options.getNewApiPath())).willReturn(newApi);
        given(checker.check(eq(oldApi), eq(newApi), any(CheckerOptions.class))).willReturn(Collections.emptyList());
        Reporter reporter = mock(Reporter.class);
        given(reporterFactory.create(options)).willReturn(reporter);
        // when
        Collection<BreakingChange> result = underTest.run(options);
        // then
        assertThat(result).isEmpty();
        verify(downloadOptionsFactory).create(options);
        verify(downloaderFactory).create(options);
        verify(latestArtifactDownloader).download(downloadOptions);
        verify(apiFileResolver).resolve(any(ApiFileResolverParameter.class));
        verify(openApiFactory).fromFile(oldApiPath);
        verify(openApiFactory).fromFile(options.getNewApiPath());
        verify(checker).check(eq(oldApi), eq(newApi), any(CheckerOptions.class));
        verify(reporter).report(anyList(), eq(options));
    }
}
