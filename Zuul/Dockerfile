FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8762
ADD target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]