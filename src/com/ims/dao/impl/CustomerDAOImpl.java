package com.ims.dao.impl;

import com.ims.dao.CustomerDAO;
import com.ims.model.Policy;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl extends UserDAOImpl implements CustomerDAO {

    // In-memory list to store applied policies (in a real-world app, this would be a database)
    private List<Policy> policyDatabase = new ArrayList<>();
    
    
//    void showPolicies(UserDAOImpl u){
//    	List<Policy> policy = u.getPolicyList();
//    	int num = 0;
//    	for(Policy p : policy) {
//    		System.out.println(num++ + ". " + p);
//    	}
//    }

    // Method for applying for a policy
    @Override
    public void applyForPolicy(Policy policy) {
        if (policy == null || policy.getUserId() == 0 || policy.getPolicyName() == null) {
            System.out.println("Invalid policy details.");
            return;
        }

        policy.setStatus("Applied"); // Set status to Applied when applying for the policy
        policyDatabase.add(policy);
        System.out.println("Customer " + policy.getUserId() + " has applied for the policy: " + policy.getPolicyName());
    }

   

    // Method to request a policy cancellation
    @Override
    public void requestPolicyCancellation(int policyId) {
        for (Policy policy : policyDatabase) {
            if (policy.getPolicyId() == policyId) {
                // Mark policy as requested for cancellation (customer action)
                if ("Applied".equals(policy.getStatus()) || "Activated".equals(policy.getStatus())) {
                    policy.setStatus("Cancellation Requested");
                    System.out.println("Policy with ID " + policyId + " has requested cancellation.");
                } else {
                    System.out.println("Policy with ID " + policyId + " cannot be canceled in its current state.");
                }
                return;
            }
        }
        System.out.println("Policy with ID " + policyId + " not found.");
    }
}
