# Use Payara (GlassFish) as the base image
FROM payara/server-full:5.2021.5

# Set the working directory inside the container
WORKDIR /opt/payara/deployments

# Copy your WAR file into the container
COPY ./target/TestingAdmisshion-1.0-SNAPSHOT.war /opt/payara/deployments/

# Expose the default port for Payara/GlassFish
EXPOSE 8080

# Set the HTTP port for the Payara server (if not already set)
ENV PAYARA_HTTP_PORT 8080

# Start the Payara server
CMD ["asadmin", "start-domain", "-v"]
