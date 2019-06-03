FROM openjdk:8-jdk-alpine
VOLUME  /tmp
ARG VERSION
LABEL version=$VERSION
ARG JAR-FILE
COPY ${JAR-FILE}  app.jar
ENTRYPOINT  ["java" ,"-jar","app.jar"]