package io.redskap.swagger.brake.integration;

import io.redskap.swagger.brake.api.ApiClient;
import io.redskap.swagger.brake.api.ApiClientMock;
import io.redskap.swagger.brake.api.ApiFacade;
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
