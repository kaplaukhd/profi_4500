# Используйте базовый образ Java
FROM openjdk:latest
# Установите переменные среды для Java
# Установите переменные среды для PostgreSQL
ENV PSQL_SERVER=194.67.68.95
ENV PSQL_USER=postgres
ENV PSQL_PASSWORD=root
ENV PSQL_DATABASE=news

# Копирование JAR-файла в контейнер
COPY news.jar /news.jar

# Запуск JAR-файла
ENTRYPOINT ["java", "-jar", "/news.jar"]
