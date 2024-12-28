# Builing
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app
COPY ./apigateway/apigateway ./apigateway

WORKDIR /app/apigateway
RUN mvn clean package -DskipTests

# Running
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/apigateway/target/*.jar apigateway.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "apigateway.jar"]