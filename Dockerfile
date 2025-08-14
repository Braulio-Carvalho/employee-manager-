FROM openjdk:17-jdk-slim
WORKDIR /app

# Copia o JAR gerado pelo Maven
COPY target/employee-manager-service-0.0.1-SNAPSHOT.jar app.jar

# ✅ Copia o script wait-for-it.sh
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

EXPOSE 8080
