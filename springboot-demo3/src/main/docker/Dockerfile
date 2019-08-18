FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG springboot-demo3-0.0.1.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]