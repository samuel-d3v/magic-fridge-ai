# ==== STAGE 1: BUILD ====
FROM maven:3.9.12 AS build
WORKDIR /app

# Copia apenas o pom para cache de dependências
COPY pom.xml .
RUN mvn -B dependency:go-offline

# Copia o código
COPY /src ./src

# Build
RUN mvn clean package -DskipTests

# ==== STAGE 2: RUN ====
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=build /app/target/MagicFridgeAI-0.0.1-SNAPSHOT.jar magicfridge-app.jar

EXPOSE 8080
LABEL maintainer="contato@contato.com"
ENTRYPOINT ["java", "-jar", "magicfridge-app.jar"]