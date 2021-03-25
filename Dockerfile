FROM maven:3.6.0-jdk-11-slim AS build

COPY api/src /api/src
COPY api/pom.xml /api

COPY service/src /service/src
COPY service/pom.xml /service

COPY pom.xml /

RUN mvn -f pom.xml clean package

FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=api/target/*.jar
COPY --from=build ${JAR_FILE} /app.jar
COPY metrodb.mv.db /
ENTRYPOINT ["java","-jar","/app.jar"]