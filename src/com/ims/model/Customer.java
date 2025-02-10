package com.ims.model;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	
	 private List<Policy> policies;
    // Constructor (Customer-specific fields and the User base constructor)
    public Customer(int userId, String username, String password, String email, String fullName) {
        super(userId, username, password, email, fullName, "Customer");
        this.policies = new ArrayList<>();
        
    }
    
    public List<Policy> getPolicies() {
        return policies;
    }

    // This method will be used to add the policy to the customer's list
    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

   
   }
