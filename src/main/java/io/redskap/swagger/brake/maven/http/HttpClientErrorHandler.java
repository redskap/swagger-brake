package io.redskap.swagger.brake.maven.http;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HttpContext;

public class HttpClientErrorHandler implements HttpResponseInterceptor {
    @Override
    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        int statusCode = response.getStatusLine().getStatusCode();
        if (HttpStatus.SC_UNAUTHORIZED == statusCode) {
            throw new UnauthorizedException("Request unauthorized");
        }
    }
}
