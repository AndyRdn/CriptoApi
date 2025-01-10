FROM openjdk:17-jdk-alpine

MAINTAINER baeldung.com
COPY target/CriptoApi-0.0.1-SNAPSHOT.jar crypto-project-1.0.0.jar
ENTRYPOINT ["java", "-jar", "crypto-project-1.0.0.jar"]