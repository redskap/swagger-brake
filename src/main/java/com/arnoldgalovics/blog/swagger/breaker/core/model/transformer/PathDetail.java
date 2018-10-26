package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import java.util.Collection;

import com.arnoldgalovics.blog.swagger.breaker.core.model.HttpMethod;
import com.arnoldgalovics.blog.swagger.breaker.core.model.Response;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
class PathDetail {
    private final HttpMethod method;
    private final Collection<Response> responses;
}
