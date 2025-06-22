FROM maven:3.9-eclipse-temurin-22-jammy AS build
COPY . .
RUN mvn clean package -DskipTests

# Imagen base oficial de Java 17
FROM openjdk:24-jdk
COPY --from=build /target/platform-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo.jar"]