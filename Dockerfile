FROM openjdk:18
VOLUME /tmp
#RUN mkdir -p /app/
#RUN mkdir -p /app/logs/
WORKDIR /app
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY src ./src
COPY google-fireBase-private-key ./google-fireBase-private-key
#COPY target/cafe-backend-0.0.1-SNAPSHOT.jar application.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENV DB_URL=jdbc:postgresql://postgres_cafe:5432/mydatabase
# ENV DB_URL=jdbc:postgresql://localhost:5432/mydatabase
#  ENV DB_URL=jdbc:postgresql://postgres_cafe:5432/mydatabase?user=postgresr&password=syspass&sslmode=require
# ENV DB_URL=jdbc:postgresql://kaffeprimo-ser.postgres.database.azure.com:5432/kaffdb?user=kaffeprimoDb@kaffeprimo-ser&password=Kaffe090&sslmode=require
ENV DB_USERNAME=postgres
ENV DB_PASSWORD=syspass

# ENV DB_USERNAME=kaffepriomDb
# ENV DB_PASSWORD=Kaffe090
ENTRYPOINT ["java", "-jar", "app.jar"]
#CMD ["java", "-jar", "application.jar"]

