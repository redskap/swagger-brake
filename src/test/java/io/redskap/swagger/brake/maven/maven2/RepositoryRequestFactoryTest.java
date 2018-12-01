package io.redskap.swagger.brake.maven.maven2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import io.redskap.swagger.brake.maven.DownloadOptions;
import io.redskap.swagger.brake.maven.http.HttpRequestFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryRequestFactoryTest {
    @Mock
    private HttpRequestFactory requestFactory;

    @InjectMocks
    private RepositoryRequestFactory underTest;

    @Test
    public void testCreateShouldReturnAuthenticatedRequestWhenAuthenticationIsNeeded() throws MalformedURLException, URISyntaxException {
        // given
        String url = "url";
        String username = "username";
        String password = "password";

        DownloadOptions options = new DownloadOptions();
        options.setUsername(username);
        options.setPassword(password);

        HttpGet expected = mock(HttpGet.class);
        given(requestFactory.authenticatedGet(url, username, password)).willReturn(expected);

        // when
        HttpUriRequest result = underTest.create(url, options);
        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void testCreateShouldReturnUnauthenticatedRequestWhenAuthenticationIsNeeded() throws MalformedURLException, URISyntaxException {
        // given
        String url = "url";

        DownloadOptions options = new DownloadOptions();

        HttpGet expected = mock(HttpGet.class);
        given(requestFactory.get(url)).willReturn(expected);

        // when
        HttpUriRequest result = underTest.create(url, options);
        // then
        assertThat(result).isEqualTo(expected);
    }


    @Test(expected = RuntimeException.class)
    public void testCreateShouldThrowExceptionWhenRequestCannotBeCreated() throws MalformedURLException, URISyntaxException {
        // given
        String url = "url";
        DownloadOptions options = new DownloadOptions();
        given(requestFactory.get(url)).willThrow(MalformedURLException.class);
        // when
        underTest.create(url, options);
        // then exception thrown
    }
}