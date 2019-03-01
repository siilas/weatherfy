FROM adoptopenjdk/openjdk11:latest

MAINTAINER Silas Maciel Ferreira

VOLUME /tmp

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT java -jar /app.jar