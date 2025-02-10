package com.ims.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ims.dao.AdminDAO;
import com.ims.dao.UserDAO;
import com.ims.model.*;

public class AdminDAOImpl extends UserDAOImpl implements AdminDAO{
	
	 private final List<Category> categoryList = new ArrayList<>();
	 private final List<SubCategory> subCategoryList = new ArrayList<>();
	 private final List<Policy> policyList = new ArrayList<>();
	 
	    // Create a new category
	    public void createCategory(Category category, UserDAO user) {
	        // Check if category already exists (based on id)
	        for (Category c : user.getAllCategories()) {
	            if (c.getCategoryId() == category.getCategoryId()) {
	                System.out.println("Category with ID " + category.getCategoryId() + " already exists.");
	                return;
	            }
	        }
	        categoryList.add(category);
	        user.addToCategoryList(category);
	        
	        System.out.println("Category created: " + category);
	    }

	    // Update an existing category
	    public List<Category> updateCategory(Category category, UserDAO user) {
	    	List<Category> updatedList = user.getAllCategories();
	    	
	        for (int i = 0; i < updatedList.size(); i++) {
	            Category c = updatedList.get(i);
	            if (c.getCategoryId() == category.getCategoryId()) {
	            	updatedList.set(i, category); // Replace old category with new one
	                System.out.println("Category updated: " + category);
	                return updatedList;
	            }
	        }
	        System.out.println("Category with ID " + category.getCategoryId() + " does not exist.");
			return updatedList;
	    }

	    // Delete a category by ID
	    public List<Category> deleteCategory(int categoryId, UserDAO user) {
	        boolean found = false;
	        List<Category> updatedList = user.getAllCategories();
	        for (int i = 0; i < updatedList.size(); i++) {
	            if (updatedList.get(i).getCategoryId() == categoryId) {
	            	updatedList.remove(i);
	                System.out.println("Category with ID " + categoryId + " deleted.");
	                
	                return updatedList;
	                
	            }
	        }
	        if (!found) {
	            System.out.println("Category with ID " + categoryId + " not found.");
	        }
			return updatedList;
	    }
	    
	    /*
	     * 
	     */
	    
	    // Create a SubCategory
	    public void createSubCategory(SubCategory subCategory, UserDAO user) {
	        // Check if SubCategory ID already exists
	        for (SubCategory s : user.getAllSubcategories()) {
	            if (s.getSubCategoryId() == subCategory.getSubCategoryId()) {
	                System.out.println("SubCategory with ID " + subCategory.getSubCategoryId() + " already exists.");
	                return;
	            }
	        }
	        subCategoryList.add(subCategory);
	        user.addToSubcategoryList(subCategory);
	        System.out.println("SubCategory created: " + subCategory);
	    }

	    // Update a SubCategory
	    public List<SubCategory> updateSubCategory(SubCategory subCategory, UserDAO user) {
	    	List<SubCategory> temp = user.getAllSubcategories();
	        for (int i = 0; i < temp.size(); i++) {
	            SubCategory s = temp.get(i);
	            if (s.getSubCategoryId() == subCategory.getSubCategoryId()) {
	                temp.set(i, subCategory); // Replace old SubCategory with the updated one
	                System.out.println("SubCategory updated: " + subCategory);
	                return temp;
	            }
	        }
	        System.out.println("SubCategory with ID " + subCategory.getSubCategoryId() + " not found.");
			return temp;
	    }

	    // Delete a SubCategory by ID
	    public List<SubCategory> deleteSubCategory(int subCategoryId, UserDAO user) {
	    	List<SubCategory> temp = user.getAllSubcategories();
	        boolean found = false;
	        for (int i = 0; i < temp.size(); i++) {
	            if (temp.get(i).getSubCategoryId() == subCategoryId) {
	                temp.remove(i);
	                System.out.println("SubCategory " + temp.get(i).getSubCategoryName() + " with ID " + subCategoryId + " deleted.");
	                found = true;
	                return temp;
	            }
	        }
	        if (!found) {
	            System.out.println("SubCategory with ID " + subCategoryId + " not found.");
	        }
			return temp;
	    }
	    
	    /*
	     * 
	     */
	    
	 // Create a new policy
	    @Override
	    public void createPolicy(Policy policy, UserDAO user) {
	        if (policy == null) {
	            System.out.println("Invalid policy details.");
	            return;
	        }

	        // Add the policy to the database
	        policyList.add(policy);
	        user.addToPolicyList(policy);
	        System.out.println("Policy " + policy.getPolicyName() + " has been created.");
	    }

	    // Update an existing policy
	    @Override
	    public List<Policy> updatePolicy(Policy updatedPolicy, UserDAO user) {
	      List<Policy> updatedList = user.getPolicyList();
	    	
	        for (int i = 0; i < updatedList.size(); i++) {
	            Policy p = updatedList.get(i);
	            if (p.getPolicyId() == updatedPolicy.getPolicyId()) {
	            	updatedList.set(i, updatedPolicy); // Replace old category with new one
	                System.out.println("Policy updated: " + updatedPolicy);
	                return updatedList;
	            }
	        }
	        System.out.println("Policy with ID " + updatedPolicy.getPolicyId() + " does not exist.");
			return updatedList;
  
	    }

	    // Delete a policy by ID
	    @Override
	    public List<Policy> deletePolicy(int policyId, UserDAO user) {
	    	boolean found = false;
	        List<Policy> updatedList = user.getPolicyList();
	        for (int i = 0; i < updatedList.size(); i++) {
	            if (updatedList.get(i).getPolicyId() == policyId) {
	            	updatedList.remove(i);
	                System.out.println("Policy with ID " + policyId + " deleted.");
	                
	                return updatedList;
	                
	            }
	        }
	        if (!found) {
	            System.out.println("Policy with ID " + policyId + " not found.");
	        }
			return updatedList;
	    	
	    }

	    // View all policy requests (including applied and cancellation requests)
	    @Override
	    public void viewPolicyRequests(UserDAO user) {
	        if (user.getPolicyList().isEmpty()) {
	            System.out.println("No policies available.");
	            return;
	        }

	        for(Policy p : user.getPolicyList()) {
           	 if(p.getStatus().equalsIgnoreCase("applied")) {
           		 System.out.println(p);
           	 }
            }
	    }

	    // Activate a policy request
	    @Override
	    public  void activatePolicyRequest(int policyRequestId, UserDAO user) {
	    	String status = "Applied";
	        for (Policy policy : user.getPolicyList()) {
	            if (policy.getPolicyId() == policyRequestId) {
	                // Only activate if the policy is applied or cancellation requested
	                if (status.equalsIgnoreCase(policy.getStatus())) {
	                    policy.setStatus("Activated");
	                    System.out.println("Policy with ID " + policyRequestId + " has been activated.");
	                    return;
	                }  else {
	                    System.out.println("Policy with ID " + policyRequestId + " is already activated or canceled.");
	                }
	                
	            }
	        }
	        System.out.println("Policy with ID " + policyRequestId + " not found.");
	    }
	    
	    @Override
	    public void cancelPolicyRequest(int policyRequestId, UserDAO user) {
	    	String status = "Applied";
	        for (Policy policy : user.getPolicyList()) {
	            if (policy.getPolicyId() == policyRequestId) {
	                // Only allow canceling if the policy is in "Applied" status
	                if (status.equalsIgnoreCase(policy.getStatus())) {
	                    policy.setStatus("Cancelled");
	                    System.out.println("Policy with ID " + policyRequestId + " has been cancelled by admin.");
	                    return;
	                } else {
	                    System.out.println("Policy with ID " + policyRequestId + " cannot be cancelled (not in Applied state).");
	                    
	                }
	                
	            }
	        }
	        System.out.println("Policy with ID " + policyRequestId + " not found.");
	    }
		

}
