FROM eclipse-temurin:17-jdk-jammy as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as build
RUN ./mvnw package

FROM eclipse-temurin:17-jre-jammy as production
#ARG JAR_FILE=app/target/*.jar
COPY --from=build /app/target/*.jar trading.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/trading.jar"]