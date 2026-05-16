FROM eclipse-temurin:17-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

RUN chown -R spring:spring /app

USER spring

EXPOSE 8089

ENTRYPOINT ["java","-jar","app.jar"]