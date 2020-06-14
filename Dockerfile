# Build
FROM openjdk:8-jdk as baseimage

WORKDIR swagger-brake
COPY . .
RUN sh gradlew clean build shadowJar

# Actual container
FROM openjdk:8-jdk
WORKDIR swagger-brake
COPY --from=baseimage /swagger-brake/swagger-brake-cli/build/libs/swagger-brake-*.jar swagger-brake.jar

ENTRYPOINT ["java", "-jar", "swagger-brake.jar"]

