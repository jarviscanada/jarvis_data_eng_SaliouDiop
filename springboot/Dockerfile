# Build stage
#
FROM maven:3.8.5-jdk-11-slim AS build
COPY src /build/src
COPY pom.xml /build/
RUN mvn -f /build/pom.xml clean package -DskipTests

#
# Package stage
#
FROM openjdk:11
COPY --from=build /build/target/trading-app-0.0.1-SNAPSHOT.jar /usr/local/app/trading/lib/trading_app.jar
ENTRYPOINT ["java","-jar","/usr/local/app/trading/lib/trading_app.jar"]