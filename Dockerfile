FROM openjdk:18
VOLUME /tmp
#RUN mkdir -p /app/
#RUN mkdir -p /app/logs/
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY src ./src
COPY google-fireBase-private-key ./google-fireBase-private-key
#COPY data.sql ./data.sql
#COPY target/cafe-backend-0.0.1-SNAPSHOT.jar application.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app.jar"]

