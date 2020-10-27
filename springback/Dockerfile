FROM openjdk:14-jdk-alpine

EXPOSE 3030

ARG JAR_FILE=target/abtest.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=production" ,"-jar","/app.jar"]