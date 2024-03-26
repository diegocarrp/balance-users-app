FROM openjdk:17
MAINTAINER diego.cl
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} balance-users-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT java -jar "/balance-users-app-0.0.1-SNAPSHOT.jar"