package com.arnoldgalovics.blog.swagger.breaker.api;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;

public class ApiClientMock implements ApiClient {
    @Override
    public void upload(String apiServer, Collection<BreakingChange> breakingChanges) {
        // no implementation on purpose
    }
}
