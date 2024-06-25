FROM gradle:7.6.0-jdk17 AS build

WORKDIR /app

COPY . .

RUN ./gradlew bootJar

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/RT-CHAT-0.0.1-SNAPSHOT.jar /app/RT-CHAT-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/RT-CHAT-0.0.1-SNAPSHOT.jar"]
