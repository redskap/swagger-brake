package io.redskap.swagger.brake.core.rule.request.parameter.constraint.array;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.ArrayRequestParameter;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class ArrayMaxItemsConstraintTest {
    private ArrayMaxItemsConstraint underTest = new ArrayMaxItemsConstraint();

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullOldRequestParamIsGiven() {
        // given
        ArrayRequestParameter oldRequestParameter = null;
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullNewRequestParamIsGiven() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = null;
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenRequestParameterIsNotArrayTyped() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            1,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMaxItemsIsExtended() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMaxItemsIsRemoved() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            null,
            1,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaxItemsGetsLimited() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            2,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maxItems", 2, 1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMaxItemsGetsSet() {
        // given
        ArrayRequestParameter oldRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            null,
            1,
            false
        );
        ArrayRequestParameter newRequestParameter = new ArrayRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.ARRAY,
            1,
            1,
            false
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "maxItems", null, 1
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }
}