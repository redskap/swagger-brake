package io.redskap.swagger.brake.maven.http;

import static java.lang.String.format;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.springframework.stereotype.Component;

@Component
public class HttpRequestFactory {
    public HttpGet authenticatedGet(String url, String username, String password) throws MalformedURLException, URISyntaxException {
        HttpGet result = get(url);
        result.addHeader(HttpHeaders.AUTHORIZATION, getBasicAuthHeaderValue(username, password));
        return result;
    }

    public HttpGet get(String url) throws URISyntaxException, MalformedURLException {
        return new HttpGet(new URL(url).toURI());
    }

    private String getBasicAuthHeaderValue(String username, String password) {
        String auth = format("%s:%s", username, password);
        String encodedBasicAuth = Base64Utils.encode(auth);
        return format("Basic %s", encodedBasicAuth);
    }
}
