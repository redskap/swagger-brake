package io.redskap.swagger.brake.api;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiFacade {
    private final ApiClient apiClient;

    public void upload(Collection<BreakingChange> breakingChanges, String apiServer, String project) {
        if (StringUtils.isBlank(apiServer)) {
            throw new IllegalArgumentException("apiServer must not be empty");
        }
        if (StringUtils.isBlank(project)) {
            throw new IllegalArgumentException("project must not be empty");
        }
        apiClient.upload(breakingChanges, apiServer, project);
    }
}
