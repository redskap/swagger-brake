# Build
FROM openjdk:21-jdk as baseimage

RUN microdnf install findutils
WORKDIR swagger-brake
COPY . .
RUN sh gradlew clean build shadowJar -x test

# Actual container
FROM openjdk:21-jdk
WORKDIR swagger-brake
COPY --from=baseimage /swagger-brake/swagger-brake-cli/build/libs/swagger-brake-*-cli.jar swagger-brake.jar

ENTRYPOINT ["java", "-jar", "swagger-brake.jar"]

