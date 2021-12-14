FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} trainservice.jar
ENTRYPOINT ["java","-jar","/trainservice.jar"]