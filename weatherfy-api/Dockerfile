FROM adoptopenjdk/openjdk11:latest

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app.jar" ]