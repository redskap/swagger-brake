package com.arnoldgalovics.blog.swagger.breaker.integration;

import com.arnoldgalovics.blog.swagger.breaker.api.ApiClient;
import com.arnoldgalovics.blog.swagger.breaker.api.ApiClientMock;
import com.arnoldgalovics.blog.swagger.breaker.api.ApiFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntTestConfiguration {
    @Bean
    public ApiFacade apiFacade(ApiClient apiClient) {
        return new ApiFacade(apiClient);
    }

    @Bean
    public ApiClient apiClient() {
        return new ApiClientMock();
    }
}
