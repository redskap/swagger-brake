FROM mcr.microsoft.com/java/jre:8-zulu-alpine
ENV VERSION 0.3.0

ADD https://github.com/redskap/swagger-brake/releases/download/$VERSION/swagger-brake-$VERSION-cli.jar swagger-brake.jar

WORKDIR app

ENTRYPOINT ["java", "-jar", "/swagger-brake.jar"]
