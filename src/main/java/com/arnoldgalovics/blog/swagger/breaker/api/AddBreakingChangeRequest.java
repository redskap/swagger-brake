package com.arnoldgalovics.blog.swagger.breaker.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddBreakingChangeRequest {
    private String message;
}
