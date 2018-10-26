package com.arnoldgalovics.blog.swagger.breaker.core.model.service;

public interface Resolver<S, R> {
    R resolve(S from);
}
