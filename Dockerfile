FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/site_pl_99-0.1.36.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
