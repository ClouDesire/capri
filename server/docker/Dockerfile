FROM cloudesire/java:8
MAINTAINER ClouDesire <dev@cloudesire.com>

ADD capra-server.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
