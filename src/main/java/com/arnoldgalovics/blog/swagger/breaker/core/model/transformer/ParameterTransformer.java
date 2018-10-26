package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParameterTransformer implements Transformer<Parameter, RequestParameter> {
    private final SchemaTransformer schemaTransformer;

    @Override
    public RequestParameter transform(Parameter from) {
        //Schema schema = schemaTransformer.transform(from.getSchema());
        return null;
    }
}
