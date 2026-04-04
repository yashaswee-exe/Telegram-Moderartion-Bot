# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
# Force a clean build
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Using a broader wildcard to ensure we find the fat JAR
# This looks for ANY jar that has 'dependencies' in the name
COPY --from=build /app/target/*dependencies.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]