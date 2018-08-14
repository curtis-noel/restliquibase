FROM openjdk:8-jdk-alpine
COPY build/libs/app.jar /
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container","-jar","/app.jar"]