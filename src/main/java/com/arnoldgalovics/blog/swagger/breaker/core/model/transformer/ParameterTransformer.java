package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springframework.stereotype.Component;

@Component
public class ParameterTransformer implements Transformer<Parameter, RequestParameter> {
    @Override
    public RequestParameter transform(Parameter from) {
        return null;
    }
}
