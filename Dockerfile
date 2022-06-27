FROM openjdk:17
LABEL maintainer="bachirkeita"
ADD target/docker.jar docker.jar
ENTRYPOINT ["java","-jar", "docker.jar"]
