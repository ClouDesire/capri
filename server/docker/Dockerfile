FROM cloudesire/java:8
MAINTAINER ClouDesire <dev@cloudesire.com>

ADD capri-server.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
