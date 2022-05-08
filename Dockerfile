FROM openjdk:8-jdk
ARG jar_file
COPY target/StringCalculator-1.01-SNAPSHOT.jar /StringCalculator-1.01-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "StringCalculator-1.01-SNAPSHOT.jar", "console"]