package com.arnoldgalovics.blog.swagger.breaker.api;

public class UploadException extends RuntimeException {
    public UploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
