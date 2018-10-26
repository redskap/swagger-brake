package com.arnoldgalovics.blog.swagger.breaker.core.model.transformer;

import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameter;
import com.arnoldgalovics.blog.swagger.breaker.core.model.RequestParameterType;
import com.arnoldgalovics.blog.swagger.breaker.core.model.service.RequestParameterTypeResolver;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParameterTransformer implements Transformer<Parameter, RequestParameter> {
    private final SchemaTransformer schemaTransformer;
    private final RequestParameterTypeResolver requestParameterTypeResolver;

    @Override
    public RequestParameter transform(Parameter from) {
        String name = from.getName();
        RequestParameterType type = requestParameterTypeResolver.resolve(from.getIn());
        Schema swSchema = from.getSchema();
        if (swSchema != null) {
            return new RequestParameter(type, name, schemaTransformer.transform(swSchema));
        }
        return new RequestParameter(type, name);
    }
}
