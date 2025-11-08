# Etapa 1: Build da aplicação
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia o wrapper e arquivos de dependência primeiro (para otimizar cache)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dá permissão de execução ao Maven Wrapper
RUN chmod +x mvnw

# Baixa as dependências antes do código-fonte
RUN ./mvnw dependency:go-offline -B

# Copia o código fonte e builda
COPY src src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final e leve
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/mobile-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
