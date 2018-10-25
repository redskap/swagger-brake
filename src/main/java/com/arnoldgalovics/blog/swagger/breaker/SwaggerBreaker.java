package com.arnoldgalovics.blog.swagger.breaker;

import com.arnoldgalovics.blog.swagger.breaker.core.BreakingChange;
import com.arnoldgalovics.blog.swagger.breaker.core.SwaggerBreakerCoreConfiguration;
import com.arnoldgalovics.blog.swagger.breaker.runner.SwaggerBreakerRunner;
import com.arnoldgalovics.blog.swagger.breaker.runner.SwaggerBreakerRunnerConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class SwaggerBreaker {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SwaggerBreakerCoreConfiguration.class, SwaggerBreakerRunnerConfiguration.class);
        SwaggerBreakerRunner executor = context.getBean(SwaggerBreakerRunner.class);
        String oldApiPath = "F:\\work\\git\\swagger-breaker\\src\\test\\resources\\pathdeletion\\petstore.yaml";
        String newApiPath = "F:\\work\\git\\swagger-breaker\\src\\test\\resources\\pathdeletion\\petstore_v2.yaml";
        executor.execute(oldApiPath, newApiPath)
                .stream().map(BreakingChange::getMessage).forEach(log::info);
    }
}
