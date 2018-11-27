package io.redskap.swagger.brake.runner;

import io.redskap.swagger.brake.core.BreakChecker;
import io.redskap.swagger.brake.core.model.Specification;
import io.redskap.swagger.brake.core.model.transformer.Transformer;
import io.redskap.swagger.brake.report.ReporterFactory;
import io.swagger.v3.oas.models.OpenAPI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RunnerTest {
    @Mock
    private Transformer<OpenAPI, Specification> transformer;

    @Mock
    private BreakChecker breakChecker;

    @Mock
    private ReporterFactory reporterFactory;

    @Mock
    private ArtifactDownloaderHandler artifactDownloaderHandler;

    @InjectMocks
    private Runner underTest;

    @Test(expected = IllegalArgumentException.class)
    public void testRunShouldThrowExceptionWhenOldApiPathIsNotPresent() {
        // given
        Options options = new Options();
        options.setNewApiPath("newApi");
        // when
        underTest.run(options);
        // then exception thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRunShouldThrowExceptionWhenNewApiPathIsNotPresent() {
        // given
        Options options = new Options();
        options.setOldApiPath("oldApi");
        // when
        underTest.run(options);
        // then exception thrown
    }
}