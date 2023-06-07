FROM openjdk:18
VOLUME /tmp
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY src ./src
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
ADD src/main/resources/static/images application/images
#COPY images .src/main/resources/static/images
ADD target/cafe-backend-0.0.1-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/app.jar"]
EXPOSE 8080

#FROM openjdk:18
#ENV APP_HOME=/usr/app/
#WORKDIR $APP_HOME
##RUN mkdir -p /app/
##RUN mkdir -p /app/logs/
#ADD src/main/resources/static/images application/images
#ADD target/cafe-backend-0.0.1-SNAPSHOT.jar /app/app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "target/app.jar"]