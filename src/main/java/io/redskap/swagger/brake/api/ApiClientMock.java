package io.redskap.swagger.brake.api;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;

public class ApiClientMock implements ApiClient {
    @Override
    public void upload(Collection<BreakingChange> breakingChanges, String apiServer, String project) {
        // no implementation on purpose
    }
}
