FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} discovery-app.jar
ENTRYPOINT ["java","-jar","/discovery-app.jar"]