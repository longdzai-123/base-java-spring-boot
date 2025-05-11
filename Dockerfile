# Stage 1: Build the Spring Boot application with Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy Maven descriptor files first (tận dụng cache tốt hơn)
COPY pom.xml .
COPY src src

# Build application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17

WORKDIR /app

# Copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar /app/app.jar

# Run the jar
CMD ["java", "-jar", "app.jar"]