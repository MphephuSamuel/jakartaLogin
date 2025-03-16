package za.ump.scms.bict.testingadmisshion.beans;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import java.io.IOException;

@Named
public class ProfileBean {

    private String username;

    public void loadProfile() throws IOException {
        try {
            // Get the session token from the FacesContext (session scope)
            String token = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("token");
            
            if (token == null || token.isEmpty()) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
                return;
            }

            // Create a client instance
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/TestingAdmisshion/resources/admin/profile");

            // Make the GET request to fetch profile data, passing the token in the Authorization header
            String profileInfo = target.request(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + token)
                    .get(String.class);

            this.username = profileInfo; // Assuming the backend sends the username in the response

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
    }
    
    public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "login.xhtml?faces-redirect=true";
}


    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
