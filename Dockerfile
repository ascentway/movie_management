#Use an official JDK runtime as base image
FROM eclipse-temurin:21-jdk

#Set Working Directory inside container
WORKDIR /app

#Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn -B dependency:go-offline -DskipTests

#Copy entire source code
COPY src ./src
RUN mvn clean package -DskipTests

# Run the application
FROM eclipse-temurin:21-jdk
WORKDIR /app

#Copy jar from bui;d stage
COPY --from=build /app/target/*.jar app.jar

#Run The Application
ENTRYPOINT ["java","-jar", "app.jar"]