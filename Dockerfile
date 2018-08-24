FROM openjdk:8-jre-alpine

RUN addgroup -g 1000 appGroup && adduser -h /home/appUser -u 1000 -G appGroup -s /bin/bash -D appUser

USER appUser

ADD --chown=1000:1000 build/libs/ktorService.jar /home/appUser/ktorService.jar

EXPOSE 4567

ENTRYPOINT java -jar /home/appUser/ktorService.jar


