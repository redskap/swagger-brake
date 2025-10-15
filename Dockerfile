# Build
FROM amazoncorretto:21-alpine-jdk AS baseimage

WORKDIR swagger-brake
COPY . .
RUN ./gradlew --no-daemon clean build shadowJar -x test

# Actual container
FROM amazoncorretto:21-alpine-jdk
WORKDIR swagger-brake
COPY --from=baseimage /swagger-brake/swagger-brake-cli/build/libs/swagger-brake-*-cli.jar swagger-brake.jar

ENTRYPOINT ["java", "-jar", "swagger-brake.jar"]

