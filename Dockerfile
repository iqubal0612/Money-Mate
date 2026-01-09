# Stage 1: Build the JAR
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/moneymate-0.0.1-SNAPSHOT.jar moneymate-v1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "moneymate-v1.jar"]
