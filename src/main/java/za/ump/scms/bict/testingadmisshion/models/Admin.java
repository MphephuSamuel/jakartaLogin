package za.ump.scms.bict.testingadmisshion.models;

import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mphep
 */
@XmlRootElement
public class Admin {
    private String username;
    private String password;
    private String email;
    
    public Admin() {
        // Default constructor
    }

    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
