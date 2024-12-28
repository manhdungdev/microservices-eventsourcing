# Builing
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY ./commonservice/commonservice ./commonservice
COPY ./notificationservice ./notificationservice

WORKDIR /app/commonservice
RUN mvn clean install -DskipTests

WORKDIR /app/notificationservice
RUN mvn clean package -DskipTests

# Running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/notificationservice/target/*.jar notificationservice.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "notificationservice.jar"]