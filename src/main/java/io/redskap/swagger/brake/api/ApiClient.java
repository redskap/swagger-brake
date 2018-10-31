package io.redskap.swagger.brake.api;

import java.util.Collection;

import io.redskap.swagger.brake.core.BreakingChange;

public interface ApiClient {
    void upload(Collection<BreakingChange> breakingChanges, String apiServer, String project);
}
