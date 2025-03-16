/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ump.scms.bict.testingadmisshion.mocdatabases;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import za.ump.scms.bict.testingadmisshion.models.Admin;

/**
 *
 * @author mphep
 */
@ApplicationScoped
public class AdminDatabase {
    private final List<Admin> admins = new ArrayList<>();
    
    
    public void createAdmin(Admin admin){
        admins.add(admin);
    }
    
    public Admin getAdminByUsername(String username){
        return admins.stream()
                .filter(admin -> admin.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
