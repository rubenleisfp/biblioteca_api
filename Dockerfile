# Stage 1: Build the application using Maven and Java 21
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller image for running the application
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar biblioteca.jar
ENTRYPOINT ["java", "-jar", "biblioteca.jar"]