# Stage 1: Build the Spring Boot application with Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# ✅ Cấu hình UTF-8 locale
ENV LANG=C.UTF-8

# Copy Maven descriptor files first (tận dụng cache tốt hơn)
COPY pom.xml .
COPY src src

# Build application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]