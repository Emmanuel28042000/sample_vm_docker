# Use an official Maven image as the base image for the build stage
FROM ubuntu

# Install MySQL client for interacting with MySQL databases
RUN apt-get update && \
    apt-get install -y mysql-client && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the working directory
#COPY pom.xml .

# Download and cache Maven dependencies (if any changes to pom.xml)
#UN mvn dependency:go-offline -B

# Copy the project source code to the working directory
#COPY src ./src

# Build the application using Maven
#RUN mvn package

# Use a lightweight JDK image as the base image for the runtime stage
FROM adoptopenjdk:11-jre-hotspot AS runtime

# Install MySQL client in the runtime image (if needed for runtime interactions)
#RUN apt-get update && \
#    apt-get install -y mysql-client && \
#    rm -rf /var/lib/apt/lists/*

# Set the working directory in the container
#WORKDIR /app

# Copy the built JAR file from the build stage to the runtime stage
#COPY --from=build /app/target/your-app-name.jar ./app.jar

EXPOSE 9082
COPY . ./
ADD target/Sample-0.0.1-SNAPSHOT.war /Sample-0.0.1-SNAPSHOT.war
ENTRYPOINT ["java","-jar","/Sample-0.0.1-SNAPSHOT.war"]

