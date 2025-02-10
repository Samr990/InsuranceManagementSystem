package com.ims.dao.impl;

import com.ims.dao.CustomerDAO;
import com.ims.dao.UserDAO;
import com.ims.model.Customer;
import com.ims.model.Policy;
import com.ims.model.User;

import java.util.List;

public class CustomerDAOImpl extends UserDAOImpl implements CustomerDAO {

    // Method for applying for a policy
	@Override
	public void applyForPolicy(int userId, UserDAO userDAO, Policy policy) {
	    System.out.println("Attempting to apply for policy for userId: " + userId);  // Debugging line
	    
	    // Retrieve the user from the list using getUserById
	    User user = userDAO.getUserById(userId);
	    
	    if (user != null && user instanceof Customer) {
//	        Customer customer = (Customer) user;  // Cast to Customer
	        ((Customer) user).addPolicy(policy);  // Add policy to the customer's list of policies
	        policy.setStatus("Applied");  // Set the policy status
	        System.out.println("Customer " + userId + " has applied for the policy: " + policy.getPolicyName());
	    } else {
	        System.out.println("Customer not found for userId: " + userId);  // Debugging line
	    }
	}


    private Customer getCustomerById(int customerId) {
        // Get the list of all users from UserDAOImpl
        List<User> allUsers = getUsers();  // userDAO should be the reference to UserDAOImpl
        System.out.println("All Users: " + allUsers);  // Debugging line

        for (User user : allUsers) {
            if (user instanceof Customer && user.getUserId() == customerId) {
                return (Customer) user;
            }
        }
        return null;  // If customer is not found
    }
}
