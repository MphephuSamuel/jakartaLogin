package za.ump.scms.bict.testingadmisshion.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.ump.scms.bict.testingadmisshion.models.Admin;

@Named
@RequestScoped
public class LoginBean {

    private String username;
    private String password;
    private String token;
    private boolean loginError = false;  // Error flag for failed login

    // Login method
    public String login() {
        try {
            // Create a client instance
            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://localhost:8080/TestingAdmisshion/resources/admin/login");

            // Sending a POST request with the login details
            Response response = target.request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(new Admin(username, password, "kk"), MediaType.APPLICATION_JSON));

            // Check the HTTP status code
            if (response.getStatus() == 200) {
                // If the response is successful, store the token and proceed
                this.token = response.readEntity(String.class); // Assuming the token is returned as plain text or JSON

                // Save the token to session
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("token", token);

                // Reset login error flag on successful login
                loginError = false;

                // Show success message
                FacesContext.getCurrentInstance().addMessage(null,
                        new jakarta.faces.application.FacesMessage("Login successful! Redirecting to your profile..."));

                return "profile.xhtml?faces-redirect=true";  // Redirect to profile page after successful login
            } else {
                // If login failed, set the error flag and show an error message
                loginError = true;
                FacesContext.getCurrentInstance().addMessage(null,
                        new jakarta.faces.application.FacesMessage("Invalid username or password. Please try again."));
                return "login.xhtml?faces-redirect=true";  // Stay on login page
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage("Error: " + e.getMessage()));
            return "login.xhtml?faces-redirect=true";  // Stay on login page in case of error
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoginError() {
        return loginError;
    }

    public void setLoginError(boolean loginError) {
        this.loginError = loginError;
    }
}
