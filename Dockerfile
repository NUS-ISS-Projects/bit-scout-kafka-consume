FROM openjdk:17-jdk
WORKDIR /app
COPY target/coin-service-0.0.1-SNAPSHOT.jar /app/coin-service.jar
ENTRYPOINT ["java", "-jar", "coin-service.jar"]