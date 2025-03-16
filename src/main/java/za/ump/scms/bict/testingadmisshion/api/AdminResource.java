/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.ump.scms.bict.testingadmisshion.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import za.ump.scms.bict.testingadmisshion.models.Admin;
import za.ump.scms.bict.testingadmisshion.services.AdminService;

/**
 *
 * @author mphep
 */
@Path("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {
    @Inject
    AdminService adminService;
    
    @Path("signup")
    @POST
    public Response singUp(Admin admin){
        try{
            adminService.register(admin);
            return Response.status(Response.Status.CREATED)
                    .entity("Admin Registered Succesfully")
                    .build();
        }catch (Exception e){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error registering admin : " + e.getMessage())
                    .build();
        }
    }
    
    @Path("login")
    @POST
    public Response login(Admin admin){
        try{
            String token = adminService.login(admin.getUsername(), admin.getPassword());
            return Response.ok(token).build();
        }catch(Exception e){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Invalid username or password")
                    .build();
        }
        
    }
    
    
    @GET
    @Path("profile")
    public Response getProfile(@Context ContainerRequestContext requestContext) {
        // Retrieve the username from the filter
        String username = (String) requestContext.getProperty("username");

        if (username == null) {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Unauthorized: No valid token found")
                    .build();
        }

        return Response.ok("Username: " + username).build();
    }
    
}
