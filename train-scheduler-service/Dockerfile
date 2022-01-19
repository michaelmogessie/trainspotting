FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} train-scheduler-app.jar
ENTRYPOINT ["java","-jar","/train-scheduler-app.jar"]