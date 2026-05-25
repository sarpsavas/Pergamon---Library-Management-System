# 1. Aşama: Derleme (Build)
FROM maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Tüm katmanları içeri alıyoruz
COPY . .

# Projeyi derle
RUN mvn clean package -DskipTests

# 2. Aşama: Çalıştırma (Run)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Executable jar api katmanından geliyor
COPY --from=build /app/api/target/*.jar app.jar

EXPOSE 8000
ENTRYPOINT ["java","-jar","app.jar"]
