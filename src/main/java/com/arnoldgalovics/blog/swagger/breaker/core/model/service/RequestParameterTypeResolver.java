package com.arnoldgalovics.blog.swagger.breaker.core.model.service;

import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameterType;
import org.springframework.stereotype.Component;

@Component
public class RequestParameterTypeResolver implements Resolver<String, RequestParameterType> {
    @Override
    public RequestParameterType resolve(String from) {
        return RequestParameterType.fromName(from);
    }
}
