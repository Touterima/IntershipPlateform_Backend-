FROM openjdk:17
LABEL authors="imenr"
ADD target/microservices-0.0.1-SNAPSHOT.jar  application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8057