package io.redskap.swagger.brake.core.rule.request.parameter.constraint;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.RequestParameter;

public interface RequestParameterConstraint<T extends RequestParameter> {
    Optional<RequestParameterConstraintChange> validateConstraints(T oldRequestParameter, T newRequestParameter);

    Class<T> handledRequestParameter();
}
