package com.arnoldgalovics.blog.swagger.breaker;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakChecker;
import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.DefaultBreakChecker;
import com.arnoldgalovics.blog.swagger.breaker.parser.transformer.OpenAPITransformer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

import static java.lang.System.out;

public class SwaggerBreaker {
    public static void main(String[] args) {
        OpenAPI openAPI = new OpenAPIV3Parser().read("F:\\work\\git\\swagger-breaker\\petstore.yaml");
        OpenAPI openAPI2 = new OpenAPIV3Parser().read("F:\\work\\git\\swagger-breaker\\petstore_v2.yaml");
        OpenAPITransformer transformer = new OpenAPITransformer();
        BreakChecker breakChecker = new DefaultBreakChecker();
        breakChecker.check(transformer.transform(openAPI), transformer.transform(openAPI2)).stream().map(BreakingChange::getMessage).forEach(out::println);
    }
}
