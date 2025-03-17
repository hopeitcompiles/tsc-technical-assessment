FROM maven:3.8.5-openjdk-17 AS build

LABEL authors="cristhian castro"

WORKDIR /app

#copy pom for maven dependencies
COPY pom.xml .

RUN mvn dependency:go-offline

#copy src folder to workdir
COPY src ./src

#run maven clean
RUN mvn clean package

#import java 17 slim
FROM openjdk:17-jdk-slim

#copy jar file
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

