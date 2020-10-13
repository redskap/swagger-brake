package io.redskap.swagger.brake.core.rule.request.parameter.constraint.number;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import io.redskap.swagger.brake.core.model.RequestParameterInType;
import io.redskap.swagger.brake.core.model.parameter.NumberRequestParameter;
import io.redskap.swagger.brake.core.model.parameter.RequestParameterType;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import org.junit.Test;

public class NumberMinimumConstraintTest {
    private NumberMinimumConstraint underTest = new NumberMinimumConstraint();

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullOldRequestParamIsGiven() {
        // given
        NumberRequestParameter oldRequestParameter = null;
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenNullNewRequestParamIsGiven() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = null;
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenRequestParameterIsNotIntegerTyped() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            BigDecimal.TEN,
            BigDecimal.ZERO,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.GENERIC,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMinimumValueSetIsExtended() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ONE,
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReturnEmptyOptionalWhenMinimumValueSetIsExtendedWithExclusiveMaximumSetting() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            false,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueSetIsLimitedWithExclusiveMaximumSetting() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            false,
            false
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            true,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "exclusiveMinimum", false, true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueSetIsLimitedWithExclusiveMaximumSettingEdgeCase1() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN.subtract(BigDecimal.ONE),
            true,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            false,
            false
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }
    
    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueSetIsLimitedWithExclusiveMaximumSettingEdgeCase2() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN,
            false,
            false
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.TEN.subtract(BigDecimal.ONE),
            true,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueSetGetsLimitedForInt64() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.TEN,
            BigDecimal.ONE,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_64,
            BigDecimal.ONE,
            BigDecimal.TEN,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minimum", BigDecimal.ONE, BigDecimal.TEN
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueSetGetsLimitedForInt32() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_32,
            BigDecimal.TEN,
            BigDecimal.ONE,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INT_32,
            BigDecimal.ONE,
            BigDecimal.TEN,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minimum", BigDecimal.ONE, BigDecimal.TEN
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueSetGetsLimitedForInteger() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.TEN,
            BigDecimal.ONE,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.ONE,
            BigDecimal.TEN,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minimum", BigDecimal.ONE, BigDecimal.TEN
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueGetsSet() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            null,
            null,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.ONE,
            BigDecimal.ONE,
            false,
            true
        );
        RequestParameterConstraintChange expected = new RequestParameterConstraintChange(
            "minimum", null, BigDecimal.ONE
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).get().isEqualTo(expected);
    }

    @Test
    public void testValidateConstraintsShouldReportConstraintChangeWhenMinimumValueGetsRemoved() {
        // given
        NumberRequestParameter oldRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            BigDecimal.ONE,
            BigDecimal.ZERO,
            false,
            true
        );
        NumberRequestParameter newRequestParameter = new NumberRequestParameter(
            RequestParameterInType.PATH,
            "testAttribute",
            true,
            null,
            RequestParameterType.INTEGER,
            null,
            null,
            false,
            true
        );
        // when
        Optional<RequestParameterConstraintChange> result = underTest.validateConstraints(oldRequestParameter, newRequestParameter);
        // then
        assertThat(result).isNotPresent();
    }
}