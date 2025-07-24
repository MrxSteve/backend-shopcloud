# Etapa 1: build
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copiamos dependencias primero para cachear
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copiamos el resto del proyecto y lo construimos
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiamos el JAR desde la etapa anterior
COPY --from=build /app/target/notification-mservice-*.jar app.jar

# Exponemos el puerto
EXPOSE 8081

# Comando para ejecutar
ENTRYPOINT ["java", "-jar", "app.jar"]