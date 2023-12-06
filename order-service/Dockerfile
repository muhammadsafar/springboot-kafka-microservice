FROM eclipse-temurin:17

#eclipse-temurin is jdk

LABEL mentainer="muhammad.ict1487@gmail.com"

WORKDIR /app/

COPY target/order-service.jar /app/order-service.jar

ENTRYPOINT ["java", "-jar", "order-service.jar"]