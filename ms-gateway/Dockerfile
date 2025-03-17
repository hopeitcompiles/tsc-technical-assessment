FROM maven:3.8.5-openjdk-17 AS build

LABEL authors="cristhian castro"

WORKDIR /app

#copy pom for maven dependencies
COPY pom.xml .

RUN mvn dependency:go-offline

#copy src folder to workdir
COPY src ./src

ENV SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL}
ENV SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME}
ENV SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD}

#run maven clean
RUN mvn clean package

#import java 17 slim
FROM openjdk:17-jdk-slim

#copy jar file
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

