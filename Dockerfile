# stage 1: Use Maven for  build
#FROM maven:3.8.5-openjdk-17-slim as build
#WORKDIR /app
#COPY . ./base-java
#WORKDIR /app/base-java
#RUN mvn clean package -DskipTests
#
#
## Stage 2 : use OpenJDK for running
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=build /app/base-java/target/*.jar base-java.jar
#EXPOSE 8761
#ENTRYPOINT ["java","-jar","base-java.jar"]