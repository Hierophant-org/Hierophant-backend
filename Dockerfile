FROM openjdk:8-jdk-alpine
COPY /tartget/Hierophant-0.0.1-SNAPSHOT.jar Hierophant-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar", "/Hierophant-0.0.1-SNAPSHOT.jar"]