# Builing
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY ./discoveryserver/discoveryserver ./discoveryserver
WORKDIR /app/discoveryserver
RUN mvn clean package -DskipTests

# Running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/discoveryserver/target/*.jar discoveryserver.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "discoveryserver.jar"]