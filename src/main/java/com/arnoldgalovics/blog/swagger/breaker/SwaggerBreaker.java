package com.arnoldgalovics.blog.swagger.breaker;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;

import static java.lang.System.out;

public class SwaggerBreaker {
    public static void main(String[] args) {
        SwaggerBreakerExecutor executor = new SwaggerBreakerExecutor("F:\\work\\git\\swagger-breaker\\src\\test\\resources\\pathdeletion\\petstore.yaml", "F:\\work\\git\\swagger-breaker\\src\\test\\resources\\pathdeletion\\petstore_v2.yaml");
        executor.execute().stream().map(BreakingChange::getMessage).forEach(out::println);
    }
}
