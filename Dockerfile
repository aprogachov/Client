FROM openjdk:8
COPY ./src /usr/src
WORKDIR /usr/src
RUN javac Client.java
ENTRYPOINT ["java", "Client"]
