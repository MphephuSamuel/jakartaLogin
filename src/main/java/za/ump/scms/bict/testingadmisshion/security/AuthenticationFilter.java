package za.ump.scms.bict.testingadmisshion.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.annotation.Priority;

@Provider
@Priority(Priorities.AUTHENTICATION) // Ensures this filter runs at authentication stage
public class AuthenticationFilter implements jakarta.ws.rs.container.ContainerRequestFilter {

    private static final String SECRET_KEY = "mySecretKey";  // Your JWT secret key

    @Override
    public void filter(ContainerRequestContext requestContext) throws WebApplicationException {
        String path = requestContext.getUriInfo().getPath();
        
        // Skip the login and signup endpoints
        if (path.contains("login") || path.contains("signup")) {
            return;
        }

        // Get the Authorization header from the request
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new WebApplicationException("Authorization header must be provided", Response.Status.UNAUTHORIZED);
        }

        String token = authHeader.substring(7); // Extract token part

        try {
            // Validate the JWT token and extract the username from the claims
            String username = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject(); // The username is stored as the subject

            // Set the username in the request context for later use
            requestContext.setProperty("username", username);

        } catch (SignatureException e) {
            throw new WebApplicationException("Invalid or expired token", Response.Status.UNAUTHORIZED);
        }
    }
}
