FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} train-manager.jar
ENTRYPOINT ["java","-jar","/train-manager.jar"]