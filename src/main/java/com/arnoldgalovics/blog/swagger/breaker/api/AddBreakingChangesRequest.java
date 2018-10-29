package com.arnoldgalovics.blog.swagger.breaker.api;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBreakingChangesRequest {
    private Collection<AddBreakingChangeRequest> breakingChanges;
    private String project;
}
