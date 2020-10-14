package io.redskap.swagger.brake.integration.v2.request;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;

import io.redskap.swagger.brake.core.BreakingChange;
import io.redskap.swagger.brake.core.model.HttpMethod;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChange;
import io.redskap.swagger.brake.core.rule.request.parameter.constraint.RequestParameterConstraintChangedBreakingChange;
import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RequestParameterConstraintChangeIntTest extends AbstractSwaggerBrakeIntTest {
    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForInteger64() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int64/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForInteger32() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/int32/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForIntegerBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/integer/base/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForDouble() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/double/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/double/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal("10.0"), new BigDecimal("5.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForFloat() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/float/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/float/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal("10.0"), new BigDecimal("5.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumChangesForNumberBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/base/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/base/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", new BigDecimal(10), new BigDecimal(5)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForDouble() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/double/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/double/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal("10.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForFloat() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/float/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/float/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal("10.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaximumIntroducedForNumberBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/base/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/base/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maximum", null, new BigDecimal(10)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForDouble() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/double/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/double/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal("1.0"), new BigDecimal("3.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForFloat() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/float/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/float/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal("1.0"), new BigDecimal("3.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumChangesForNumberBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/base/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/base/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", new BigDecimal(1), new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForDouble() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/double/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/double/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal("3.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForFloat() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/float/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/float/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal("3.0")));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinimumIntroducedForNumberBase() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/number/base/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/number/base/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minimum", null, new BigDecimal(3)));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinLengthIntroducedForString() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/string/newminimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/string/newminimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minLength", null, 1));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaxLengthIntroducedForString() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/string/newmaximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/string/newmaximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maxLength", null, 10));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMaxLengthGetsChangedForString() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/string/maximum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/string/maximum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("maxLength", 10, 5));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }

    @Test
    public void testRequestTypeConstraintChangeIsBreakingChangeWhenMinLengthGetsChangedForString() {
        // given
        String oldApiPath = "swaggers/v2/request/datatypeconstraint/string/minimum/swagger-old.json";
        String newApiPath = "swaggers/v2/request/datatypeconstraint/string/minimum/swagger-new.json";
        RequestParameterConstraintChangedBreakingChange bc1 = new RequestParameterConstraintChangedBreakingChange("/store/order/{orderId}",
            HttpMethod.GET, "orderId", new RequestParameterConstraintChange("minLength", 1, 2));
        Collection<BreakingChange> expected = Collections.singletonList(bc1);
        // when
        Collection<BreakingChange> result = execute(oldApiPath, newApiPath);
        // then
        assertThat(result).hasSize(1);
        assertThat(result).hasSameElementsAs(expected);
    }
}
