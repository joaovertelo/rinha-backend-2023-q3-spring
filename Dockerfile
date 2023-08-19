FROM amazoncorretto:17.0.7-alpine

WORKDIR /app

COPY target/rinha-backend-0.1.jar /app/service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "service.jar"]