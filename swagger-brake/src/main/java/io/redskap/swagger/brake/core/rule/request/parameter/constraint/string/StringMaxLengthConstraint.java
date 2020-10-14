package io.redskap.swagger.brake.core.rule.request.parameter.constraint.string;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.StringRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraint;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.springframework.stereotype.Component;

@Component
public class StringMaxLengthConstraint implements RequestParameterConstraint<StringRequestParameter> {
    public static final String MAX_LENGTH_ATTRIBUTE_NAME = "maxLength";

    @Override
    public Optional<RequestParameterConstraintChange> validateConstraints(StringRequestParameter oldRequestParameter, StringRequestParameter newRequestParameter) {
        RequestParameterConstraintChange result = null;
        if (oldRequestParameter != null && newRequestParameter != null) {
            Integer oldMaxLength = oldRequestParameter.getMaxLength();
            Integer newMaxLength = newRequestParameter.getMaxLength();
            if (oldMaxLength == null && newMaxLength != null) {
                result = new RequestParameterConstraintChange(
                    MAX_LENGTH_ATTRIBUTE_NAME, null, newMaxLength
                );
            }
            if (oldMaxLength != null && newMaxLength != null) {
                if (newMaxLength < oldMaxLength) {
                    result = new RequestParameterConstraintChange(
                      MAX_LENGTH_ATTRIBUTE_NAME, oldMaxLength, newMaxLength
                    );
                }
            }
        }
        return Optional.ofNullable(result);
    }

    @Override
    public Class<StringRequestParameter> handledRequestParameter() {
        return StringRequestParameter.class;
    }
}
