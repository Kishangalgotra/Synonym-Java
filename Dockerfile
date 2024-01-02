FROM openjdk:11

# Run Gradle to build the project
RUN mvn package -DskipTests

# Create a new image based on OpenJDK 8
FROM openjdk:11-jre-slim-buster

# Expose the port that the application will use
EXPOSE 8090
COPY target/DemoSpring-0.0.1-SNAPSHOT.jar DemoSpring-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/DemoSpring-0.0.1-SNAPSHOT.jar"]