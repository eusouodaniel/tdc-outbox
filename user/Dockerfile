FROM maven:3.8.5-openjdk-17 AS build

COPY src /opt/app/src 
COPY pom.xml /opt/app 
RUN mvn -f /opt/app/pom.xml clean package 

FROM openjdk:17-alpine3.14

COPY --from=build /opt/app/target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8081