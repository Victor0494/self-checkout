# Etapa 1: build da aplicação
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: imagem final e leve
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/mobile-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
