package io.redskap.swagger.brake.integration.validation;

import io.redskap.swagger.brake.integration.AbstractSwaggerBrakeIntTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(SpringRunner.class)
public class GeneralRequestValidationTest extends AbstractSwaggerBrakeIntTest {

    @Test
    public void avoidNPERequestParameterRefTest() {

        // without io.redskap.swagger.brake.runner.openapi.OpenApiFactory.loadV3Api parseOptions.setResolve(true); it throws NPE
        String oldApiPath = "request/generalrequest/petstore.yaml";
        String newApiPath = oldApiPath;

        execute(oldApiPath, newApiPath);
    }

    @Test
    /**
     * Should not throw java.lang.IllegalStateException: Reference not found for successNoResponse
     */
    public void emptyResponseRefTest() {

        // without io.redskap.swagger.brake.runner.openapi.OpenApiFactory.loadV3Api parseOptions.setResolve(true); it throws NPE
        // 204 - empty - no body - response
        // see https://swagger.io/docs/specification/describing-responses/  - Empty Response Body section
        String oldApiPath = "request/generalrequest/petstore.yaml";
        String newApiPath = oldApiPath;

        execute(oldApiPath, newApiPath);
    }

    @Test
    public void avoidAllOfExWhenTheSamePropertyNameTest() {

        // with io.redskap.swagger.brake.runner.openapi.OpenApiFactory.loadV3Api parseOptions.setResolve(true); it throws additional ex.
        // allOf from 2 definitions, containing same named parameters
        String oldApiPath = "request/generalrequest/petstoreAllOf.yaml";
        String newApiPath = oldApiPath;

        execute(oldApiPath, newApiPath);
    }

}
