FROM maven:3.8.4-openjdk-17-slim AS builder
COPY pom.xml /app/
COPY src /app/src
RUN mvn -f /app/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy
COPY --from=builder /app/target/*.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]