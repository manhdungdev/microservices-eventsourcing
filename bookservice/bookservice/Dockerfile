# Builing
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY ./commonservice/commonservice ./commonservice
COPY ./bookservice/bookservice ./bookservice

WORKDIR /app/commonservice
RUN mvn clean install -DskipTests

WORKDIR /app/bookservice
RUN mvn clean package -DskipTests

# Running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/bookservice/target/*.jar bookservice.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "bookservice.jar"]