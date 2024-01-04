# Step 1: Clone the repository
FROM alpine/git as clone
ARG url
RUN git clone ${url} /app

# Step 2: Build the application using Maven
FROM maven:3.8.3-openjdk-17 AS build
COPY --from=clone /app /app
WORKDIR /app
RUN mvn clean package

# Step 3: Create the runtime image
FROM openjdk:17-jdk-slim
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]
