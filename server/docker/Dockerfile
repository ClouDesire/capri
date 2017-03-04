FROM cloudesire/java:8
MAINTAINER ClouDesire <dev@cloudesire.com>

ADD capri-server.jar app.jar

EXPOSE 8080
ENV SERVER_PORT 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
