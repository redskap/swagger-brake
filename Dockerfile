# Build
FROM openjdk:17.0.2-jdk as baseimage

WORKDIR swagger-brake
COPY . .
RUN sh gradlew clean build shadowJar -x test

# Actual container
FROM openjdk:17.0.2-jdk
WORKDIR swagger-brake
COPY --from=baseimage /swagger-brake/swagger-brake-cli/build/libs/swagger-brake-*-cli.jar swagger-brake.jar

ENTRYPOINT ["java", "-jar", "swagger-brake.jar"]

