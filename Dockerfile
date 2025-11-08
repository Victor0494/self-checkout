FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o JAR gerado
COPY target/mobile-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta usada pelo Spring Boot
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
