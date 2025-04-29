# Use official OpenJDK 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory


# Copy the JAR file into the container
COPY target/mail-scheduler.jar mail-scheduler.jar

# Expose the default Spring Boot port
EXPOSE 7821

# Run the application
ENTRYPOINT ["java", "-jar", "mail-scheduler.jar"]