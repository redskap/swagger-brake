package com.arnoldgalovics.blog.swagger.breaker.core.model;

import java.util.Collection;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class Path {
    private final String path;
    private final HttpMethod method;
    private final Collection<RequestParameter> requestParameters;
    private final Collection<Response> responses;

    public Optional<Response> getResponseByCode(String code) {
        return responses.stream().filter(r -> code.equals(r.getCode())).findAny();
    }

    public Optional<RequestParameter> getRequestParameterByName(String name) {
        return requestParameters.stream().filter(p -> name.equals(p.getName())).findAny();
    }
}
