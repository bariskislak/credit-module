# Build stage
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app

# Kaynak kodları ve SQL dosyalarını kopyala
COPY . .
COPY .env /app/.env

RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# JAR ve diğer dosyaları kopyala
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/.env .env
COPY --from=build /app/src/main/resources/db/init/ /docker-entrypoint-initdb.d/

EXPOSE 8080

# curl ve wait-for-it.sh kurulumu
RUN apt-get update && apt-get install -y curl \
    && curl -o /usr/local/bin/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh \
    && chmod +x /usr/local/bin/wait-for-it.sh

# Uygulamayı başlat
ENTRYPOINT ["/usr/local/bin/wait-for-it.sh", "postgres:5432", "--", "java", "-jar", "/app/app.jar"]

