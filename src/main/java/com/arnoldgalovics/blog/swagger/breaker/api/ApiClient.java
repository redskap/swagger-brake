package com.arnoldgalovics.blog.swagger.breaker.api;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;

public interface ApiClient {
    void upload(String apiServer, Collection<BreakingChange> breakingChanges);
}
