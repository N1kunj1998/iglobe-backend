FROM openjdk:11
COPY ./target/Iiitb-Handshake-Backend-0.0.1-SNAPSHOT.war ./
WORKDIR ./
ENTRYPOINT ["java", "-jar", "Iiitb-Handshake-Backend-0.0.1-SNAPSHOT.war"]