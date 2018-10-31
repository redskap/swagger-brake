package io.redskap.swagger.brake.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBreakingChangeRequest {
    private String message;
}
