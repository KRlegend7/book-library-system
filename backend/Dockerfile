# Book Library Backend Dockerfile
# Multi-stage build for efficient image size

# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml first for dependency caching
COPY pom.xml .

# Download dependencies (this layer will be cached)
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Runtime stage
FROM quay.io/wildfly/wildfly:29.0.1.Final-jdk17

# Set environment variables
ENV WILDFLY_USER=admin
ENV WILDFLY_PASS=admin123

# Copy the built WAR file to WildFly deployments directory
COPY --from=build /app/target/book-library-backend.war /opt/jboss/wildfly/standalone/deployments/

# Copy PostgreSQL driver
ADD https://jdbc.postgresql.org/download/postgresql-42.7.1.jar /opt/jboss/wildfly/standalone/deployments/

# Copy configuration files
COPY docker/standalone.xml /opt/jboss/wildfly/standalone/configuration/

# Create admin user for WildFly management
RUN /opt/jboss/wildfly/bin/add-user.sh ${WILDFLY_USER} ${WILDFLY_PASS} --silent

# Expose ports
EXPOSE 8080 9990

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/book-library-backend/api/books || exit 1

# Start WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
