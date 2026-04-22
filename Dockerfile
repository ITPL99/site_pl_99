FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy any built jar (works regardless of version)
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
