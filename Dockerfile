# Use lightweight Java 17 runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy built JAR
COPY target/telegram-moderation-bot.jar app.jar

# Run the bot
CMD ["java", "-jar", "app.jar"]