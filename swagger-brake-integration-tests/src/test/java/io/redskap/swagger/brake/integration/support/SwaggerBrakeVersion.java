package io.redskap.swagger.brake.integration.support;

import lombok.Getter;

@Getter
public class SwaggerBrakeVersion {
    public static final SwaggerBrakeVersion V_1_1_0 = new SwaggerBrakeVersion("1.1.0");
    public static final SwaggerBrakeVersion V_2_0_0 = new SwaggerBrakeVersion("2.0.0");
    public static final SwaggerBrakeVersion V_2_1_0 = new SwaggerBrakeVersion("2.1.0");
    public static final SwaggerBrakeVersion V_2_2_0 = new SwaggerBrakeVersion("2.2.0");
    public static final SwaggerBrakeVersion V_2_3_0 = new SwaggerBrakeVersion("2.3.0");
    public static final SwaggerBrakeVersion V_LATEST_VERSION = new SwaggerBrakeVersion("2.3.0");

    private final String version;

    private SwaggerBrakeVersion(String version) {
        this.version = version;
    }
}
