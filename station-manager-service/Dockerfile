FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} station-manager.jar
ENTRYPOINT ["java","-jar","/station-manager.jar"]