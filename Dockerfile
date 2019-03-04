FROM adoptopenjdk/openjdk11:latest

MAINTAINER Silas Maciel Ferreira

VOLUME /tmp

COPY weatherfy-api/target/weatherfy-api.jar app.jar

EXPOSE 8080

ENTRYPOINT java -jar /app.jar