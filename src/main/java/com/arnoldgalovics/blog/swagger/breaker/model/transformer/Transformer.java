package com.arnoldgalovics.blog.swagger.breaker.model.transformer;

public interface Transformer<S, R> {
    R transform(S from);
}
