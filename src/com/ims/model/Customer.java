package com.ims.model;

public class Customer extends User {

    // Constructor (Customer-specific fields and the User base constructor)
    public Customer(int userId, String username, String password, String email, String fullName) {
        super(userId, username, password, email, fullName, "Customer");
    }

   
   }
