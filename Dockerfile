# Stage 1: Build the JAR using Maven
FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM openjdk:17-jdk-slim
COPY --from=build /target/telegram-moderation-bot-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]