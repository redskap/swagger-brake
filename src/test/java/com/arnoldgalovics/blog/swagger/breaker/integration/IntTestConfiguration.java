package com.arnoldgalovics.blog.swagger.breaker.integration;

import com.arnoldgalovics.blog.swagger.breaker.api.ApiClient;
import com.arnoldgalovics.blog.swagger.breaker.api.ApiClientMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntTestConfiguration {
    @Bean
    public ApiClient apiClient() {
        return new ApiClientMock();
    }
}
