package com.arnoldgalovics.blog.swagger.breaker.api;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;

public interface ApiClient {
    void upload(Collection<BreakingChange> breakingChanges, String apiServer, String project);
}
