FROM amazoncorretto:17 as builder

WORKDIR /app

COPY . .

ARG JAR_FILE=./build/libs/*.jar

COPY ${JAR_FILE} app.jar

CMD ["java", "-jar", "app.jar"]