# Stage 1: Build the 'Fat' JAR with all dependencies
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# This runs the assembly plugin from your pom.xml
RUN mvn clean package -DskipTests

# Stage 2: Create the final image to run the bot
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# IMPORTANT: This line specifically grabs the version of the JAR
# that contains the Telegram libraries (the "fat" JAR)
COPY --from=build /app/target/*-jar-with-dependencies.jar app.jar

# Run the bot
ENTRYPOINT ["java", "-jar", "app.jar"]