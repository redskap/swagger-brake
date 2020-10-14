package io.redskap.swagger.brake.core.rule.request.parameter.constraint.string;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.model.parameter.StringRequestParameter;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class StringMinLengthConstraintTest {
    private StringMinLengthConstraint underTest = new StringMinLengthConstraint();

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
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMinLengthIsExtended() {
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
            1,
            0
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMinLengthIsRemoved() {
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
            1,
            null
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinLengthGetsLimited() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            5,
            1
        );
        StringRequestParameter newRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            5,
            2
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minLength", 1, 2
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinLengthGetsSet() {
        // given
        StringRequestParameter oldRequestParameter = new StringRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.STRING,
            1,
            null
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
            "minLength", null, 1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

}