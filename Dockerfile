FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -DskipTests
FROM openjdk:8-jre-alpine3.9
COPY --from=MAVEN_BUILD /target/user-library-0.0.1-SNAPSHOT.jar /application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]