FROM openjdk:8-jre-alpine

ADD build/libs/sparkService-1.0-SNAPSHOT.jar /opt/sparkService.jar

EXPOSE 4567

ENTRYPOINT java -jar /opt/sparkService.jar


