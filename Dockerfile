FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src/

RUN mvn clean package -DskipTests=true

FROM amazoncorretto:21
WORKDIR /app
COPY --from=build /app/target/*.jar /app.jar


EXPOSE 3000
ENTRYPOINT ["java","-jar","/app.jar"]