package io.redskap.swagger.brake.api;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBreakingChangesRequest {
    private Collection<AddBreakingChangeRequest> breakingChanges;
    private String project;
}
