package io.redskap.swagger.brake.maven.http;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class Base64Utils {
    public static String encode(String s) {
        byte[] pureBytes = s.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = Base64.getEncoder().encode(pureBytes);
        return new String(encodedBytes, StandardCharsets.UTF_8);
    }
}
