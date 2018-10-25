package com.arnoldgalovics.blog.swagger.breaker.parser.transformer;

public interface Transformer<S, R> {
    R transform(S from);
}
