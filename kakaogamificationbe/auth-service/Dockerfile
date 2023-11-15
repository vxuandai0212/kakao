#
# Build stage
#
FROM maven:3.8.4-openjdk-17 AS buildMaven

WORKDIR /usr/src/authentication-service
COPY . /usr/src/authentication-service
RUN mvn clean install

#
# Build authentication-service
#
FROM openjdk:11-alpine

ARG JAR_FILE=authentication-service.jar

WORKDIR /opt/mis-reporting-service

COPY --from=buildMaven /usr/src/authentication-service/target/${JAR_FILE} /opt/authentication-service/
ENTRYPOINT ["java","-jar","authentication-service.jar"]