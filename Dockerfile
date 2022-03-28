FROM openjdk:16.0.1-jdk
ARG jar_file
COPY target/StringCalculator-1.00-SNAPSHOT.jar /StringCalculator-1.00-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "StringCalculator-1.00-SNAPSHOT.jar"]