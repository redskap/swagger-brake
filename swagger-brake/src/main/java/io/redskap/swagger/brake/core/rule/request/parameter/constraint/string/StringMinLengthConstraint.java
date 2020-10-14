package io.redskap.swagger.brake.core.rule.request.parameter.constraint.string;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.parameter.StringRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraint;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.springframework.stereotype.Component;

@Component
public class StringMinLengthConstraint implements RequestParameterConstraint<StringRequestParameter> {
    public static final String MIN_LENGTH_ATTRIBUTE_NAME = "minLength";

    @Override
    public Optional<RequestParameterConstraintChange> validateConstraints(StringRequestParameter oldRequestParameter, StringRequestParameter newRequestParameter) {
        RequestParameterConstraintChange result = null;
        if (oldRequestParameter != null && newRequestParameter != null) {
            Integer oldMinLength = oldRequestParameter.getMinLength();
            Integer newMinLength = newRequestParameter.getMinLength();
            if (oldMinLength == null && newMinLength != null) {
                result = new RequestParameterConstraintChange(
                    MIN_LENGTH_ATTRIBUTE_NAME, null, newMinLength
                );
            }
            if (oldMinLength != null && newMinLength != null) {
                if (oldMinLength < newMinLength) {
                    result = new RequestParameterConstraintChange(
                        MIN_LENGTH_ATTRIBUTE_NAME, oldMinLength, newMinLength
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
