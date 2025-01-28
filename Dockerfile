## Use the official OpenJDK image as the base image
#FROM openjdk:21-jdk-slim
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the Spring Boot application JAR file to the container
#COPY target/*.war /app/quizehive.war
#
## Command to run the Spring Boot application
#ENTRYPOINT ["java", "-jar", "/app/quizehive.war"]

# Use the official OpenJDK image as the base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle build output (WAR file) to the container
COPY build/libs/*.war /app/quizehive.war

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/quizehive.war"]
