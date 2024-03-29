FROM openjdk:21
MAINTAINER Ivan Muratov <binakot@gmail.com>

EXPOSE 8080

ARG JAR_FILE
COPY ${JAR_FILE} /app.jar

CMD java -XX:+ExitOnOutOfMemoryError \
    -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker $JAVA_OPTIONS \
    -jar /app.jar
