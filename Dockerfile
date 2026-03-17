# Use Java 17
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy project files
COPY . .

# Build the project
RUN apt-get update && apt-get install -y maven
RUN mvn clean package

# Run the bot
CMD ["java", "-cp", "target/telegram-moderation-bot-1.0.jar", "com.telegram.bot.TelegramBot"]