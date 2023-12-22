# Build Stage
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:17-jdk-slim
COPY --from=build /target/RjtechXDash-0.0.1-SNAPSHOT.jar RjtechXDash.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "RjtechXDash.jar"]