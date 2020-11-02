# From -> 기반으로 하는 이미지 가져오는것
FROM openjdk:14-jdk-alpine
# EXPOSE -> 노출시킬 포트 번호
EXPOSE 3030
# ARG -> docker의 jar파일을 변수로 담는다
ARG JAR_FILE=target/abtest.jar
# COPY -> 왼쪽이 로컬파일이름 -> 이게 도커로가는데 app.jar로 간다.
COPY ${JAR_FILE} app.jar
# EntryPoint -> 도커가 시작될때 자동으로 실행될 명령어
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=production" ,"-jar","/app.jar"]