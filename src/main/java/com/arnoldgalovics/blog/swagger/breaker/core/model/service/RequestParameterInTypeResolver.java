package com.arnoldgalovics.blog.swagger.breaker.core.model.service;

import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameterInType;
import org.springframework.stereotype.Component;

@Component
public class RequestParameterInTypeResolver implements Resolver<String, RequestParameterInType> {
    @Override
    public RequestParameterInType resolve(String from) {
        return RequestParameterInType.fromName(from);
    }
}
