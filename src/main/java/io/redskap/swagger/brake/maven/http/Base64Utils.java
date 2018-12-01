package io.redskap.swagger.brake.maven.http;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public abstract class Base64Utils {
    public static String encode(String s) {
        byte[] sBytes = s.getBytes(StandardCharsets.UTF_8);
        byte[] encodedBytes = Base64.getEncoder().encode(sBytes);
        return new String(encodedBytes);
    }

    public static String decode(String s) {
        byte[] encodedBytes = s.getBytes(StandardCharsets.UTF_8);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        return new String(decodedBytes);
    }
}
