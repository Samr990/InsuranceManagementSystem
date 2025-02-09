package com.ims.model;

public class Admin extends User {
	
    // Constructor (Admin-specific fields and the User base constructor)
    public Admin(int userId, String username, String password, String email, String fullName) {
        super(userId, username, password, email, fullName, "Admin");
        
    }

	

	

   
}
