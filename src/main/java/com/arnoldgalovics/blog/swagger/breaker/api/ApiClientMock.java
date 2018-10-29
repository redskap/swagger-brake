package com.arnoldgalovics.blog.swagger.breaker.api;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;

public class ApiClientMock implements ApiClient {
    @Override
    public void upload(Collection<BreakingChange> breakingChanges, String apiServer, String project) {
        // no implementation on purpose
    }
}
