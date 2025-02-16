FROM maven:3-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -Dmaven.test.skip=true

FROM eclipse-temurin:17-alpine
COPY --from=build /app/target/booker-0.0.1-SNAPSHOT.jar /app/booker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/booker.jar"]
