# Use a lightweight base image with Java 17 preinstalled
FROM openjdk:17-jdk-slim

# Set an argument for the JAR file location
ARG JAR_FILE=target/*.jar

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file into the container
COPY ${JAR_FILE} /app/application.jar

# Specify any runtime environment variables here
ENV SPRING_PROFILES_ACTIVE=docker

# Expose the port that your application listens on
EXPOSE 8080

# Start the application when the container launches
CMD ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "/app/application.jar"]