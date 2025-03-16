# Use Payara (GlassFish) as the base image
FROM payara/server-full:5.2021.5

# Set the working directory inside the container
WORKDIR /opt/payara/deployments

# Copy your WAR file into the container
COPY ./target/TestingAdmisshion-1.0-SNAPSHOT.war /opt/payara/deployments/

# Expose the default port for Payara/GlassFish
EXPOSE 8080

# Start the Payara server
CMD ["asadmin", "start-domain", "-v"]
