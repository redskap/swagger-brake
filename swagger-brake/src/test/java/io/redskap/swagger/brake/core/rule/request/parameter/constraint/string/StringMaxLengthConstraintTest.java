package io.redskap.swagger.brake.core.rule.request.parameter.constraint.string;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.model.parameter.StringRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class StringMaxLengthConstraintTest {
    private StringMaxLengthConstraint underTest = new StringMaxLengthConstraint();

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullOldRequestParamIsGiven() {
        // given
        StringRequestParameter oldRequestParameter = null;
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullNewRequestParamIsGiven() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            1
        );
        StringRequestParameter newRequestParameter = null;
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenRequestParameterIsNotStringTyped() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            1,
            1
        );
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            1,
            1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMaxLengthIsExtended() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            1
        );
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            2,
            1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMaxLengthIsRemoved() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            1
        );
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            null,
            1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaxLengthGetsLimited() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            2,
            1
        );
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            1
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maxLength", 2, 1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaxLengthGetsSet() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            null,
            1
        );
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            1
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maxLength", null, 1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }
}