FROM bellsoft/liberica-openjdk-alpine:11.0.16.1-1

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем исходный код проекта
COPY ./src /app/src

# Создаем папку для хранения выходных файлов
RUN mkdir -p /app/out /app/doc

# Указываем команды для генерации Javadoc
CMD ["javadoc", "-sourcepath", "/app/src", "-d", "/app/doc", "-subpackages", "ru"]
