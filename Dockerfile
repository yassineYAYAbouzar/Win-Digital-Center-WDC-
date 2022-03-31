FROM openjdk:11-jdk
COPY ./target/WDC-0.0.1-SNAPSHOT.jar WDC-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","WDC-0.0.1-SNAPSHOT.jar"]

