package za.ump.scms.bict.testingadmisshion.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import za.ump.scms.bict.testingadmisshion.models.Admin;

@Named
@RequestScoped
public class SignupBean {

    private String username;
    private String password;
    private String email;

public String signup() {
    try {
        // Debugging: Log input parameters
        System.out.println("Signup initiated with Username: " + username + ", Email: " + email);

        // Check if any fields are empty before proceeding
        if (username == null || username.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new jakarta.faces.application.FacesMessage("All fields are required."));
            return "signup.xhtml?faces-redirect=true";  // Stay on the signup page if fields are empty
        }

        // Create a client instance
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/TestingAdmisshion/resources/admin/signup");

        // Prepare admin object
        Admin admin = new Admin(username, password, email);

        // Debugging: Log the Admin object being sent
        System.out.println("Sending Admin object: " + admin);

        // Sending a POST request to the signup endpoint
        String response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(admin, MediaType.APPLICATION_JSON), String.class);

        // Debugging: Log the response from the server
        System.out.println("Response from server: " + response);

                    // Check response for success or failure and return navigation string accordingly
            if ("Admin Registered Successfully".equals(response)) {
                // Use JSF-managed navigation (best practice)
                return "login.xhtml?faces-redirect=true";  // Proper redirection to login page


        } else {
            // If signup failed, stay on the signup page and display error message
            FacesContext.getCurrentInstance().addMessage(null, 
                    new jakarta.faces.application.FacesMessage("Signup failed: " + response));
            return "login.xhtml?faces-redirect=true";  // Stay on the signup page if there's an error
        }
    } catch (Exception e) {
        // Handle exceptions and display error message
        e.printStackTrace();
        // Debugging: Log the exception message
        System.err.println("Exception occurred during signup: " + e.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, 
                new jakarta.faces.application.FacesMessage("Error: " + e.getMessage()));
        return "signup.xhtml?faces-redirect=true"; // Stay on the signup page if there's an error
    }
}



    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
