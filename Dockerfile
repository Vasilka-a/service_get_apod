FROM openjdk:17-jdk
EXPOSE 8080
COPY build/libs/nasa-api-0.0.1-SNAPSHOT.jar nasa-app.jar
CMD ["java","-jar","nasa-app.jar"]