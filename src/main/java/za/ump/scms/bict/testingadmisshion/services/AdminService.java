/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ump.scms.bict.testingadmisshion.services;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import io.jsonwebtoken.Jwts;    
import io.jsonwebtoken.SignatureAlgorithm;
import za.ump.scms.bict.testingadmisshion.mocdatabases.AdminDatabase;
import za.ump.scms.bict.testingadmisshion.models.Admin;
import java.util.Date;
/**
 *
 * @author mphep
 */
@ApplicationScoped
public class AdminService {
    
    @Inject
    private AdminDatabase adminDatabase;
    
    @Inject
    private Pbkdf2PasswordHash passwordHash;
    
    private static final String SECRET_KEY = "mySecretKey";
    
    public void register(Admin admin) {
        String hashedPassword = passwordHash.generate(admin.getPassword().toCharArray());
        admin.setPassword(hashedPassword); // Set the hashed password
        adminDatabase.createAdmin(admin);
    }

    public String login(String username, String password) {
        Admin admin = adminDatabase.getAdminByUsername(username);
        if (admin == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Verify password
        if (passwordHash.verify(password.toCharArray(), admin.getPassword())) {
            return generateJWTToken(admin);
        } else {
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
    
    // Generate JWT Token for the admin
    private String generateJWTToken(Admin admin) {
        return Jwts.builder()
                .setSubject(admin.getUsername())  // Set the username as the subject
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiry
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    } 
}
