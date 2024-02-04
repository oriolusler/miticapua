FROM openjdk:17-alpine
MAINTAINER oriolsoler.com
COPY build/libs/pua-0.0.1-SNAPSHOT-plain.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

