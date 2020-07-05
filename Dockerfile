FROM maven:3.6.0-jdk-11-slim as build
MAINTAINER ayeganyan92@gmail.com
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install -Dmaven.test.skip

FROM openjdk:8-jdk-alpine
RUN mkdir /app
COPY --from=build /home/app/target/currency-tracker-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/usr/local/lib/app.jar"]
