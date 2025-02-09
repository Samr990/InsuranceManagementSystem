package com.ims.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ims.dao.AdminDAO;
import com.ims.model.*;

public class AdminDAOImpl extends UserDAOImpl implements AdminDAO{
	
	 private final List<Category> categoryList = new ArrayList<>();
	 private final List<SubCategory> subCategoryList = new ArrayList<>();
	 private final List<Policy> policyList = new ArrayList<>();
	 
	    // Create a new category
	    public void createCategory(Category category) {
	        // Check if category already exists (based on id)
	        for (Category c : categoryList) {
	            if (c.getCategoryId() == category.getCategoryId()) {
	                System.out.println("Category with ID " + category.getCategoryId() + " already exists.");
	                return;
	            }
	        }
	        categoryList.add(category);
	        System.out.println("Category created: " + category);
	    }

	    // Update an existing category
	    public void updateCategory(Category category) {
	        for (int i = 0; i < categoryList.size(); i++) {
	            Category c = categoryList.get(i);
	            if (c.getCategoryId() == category.getCategoryId()) {
	                categoryList.set(i, category); // Replace old category with new one
	                System.out.println("Category updated: " + category);
	                return;
	            }
	        }
	        System.out.println("Category with ID " + category.getCategoryId() + " does not exist.");
	    }

	    // Delete a category by ID
	    public void deleteCategory(int categoryId) {
	        boolean found = false;
	        for (int i = 0; i < categoryList.size(); i++) {
	            if (categoryList.get(i).getCategoryId() == categoryId) {
	                categoryList.remove(i);
	                System.out.println("Category with ID " + categoryId + " deleted.");
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            System.out.println("Category with ID " + categoryId + " not found.");
	        }
	    }
	    
	    /*
	     * 
	     */
	    
	    // Create a SubCategory
	    public void createSubCategory(SubCategory subCategory) {
	        // Check if SubCategory ID already exists
	        for (SubCategory s : subCategoryList) {
	            if (s.getSubCategoryId() == subCategory.getSubCategoryId()) {
	                System.out.println("SubCategory with ID " + subCategory.getSubCategoryId() + " already exists.");
	                return;
	            }
	        }
	        subCategoryList.add(subCategory);
	        System.out.println("SubCategory created: " + subCategory);
	    }

	    // Update a SubCategory
	    public void updateSubCategory(SubCategory subCategory) {
	        for (int i = 0; i < subCategoryList.size(); i++) {
	            SubCategory s = subCategoryList.get(i);
	            if (s.getSubCategoryId() == subCategory.getSubCategoryId()) {
	                subCategoryList.set(i, subCategory); // Replace old SubCategory with the updated one
	                System.out.println("SubCategory updated: " + subCategory);
	                return;
	            }
	        }
	        System.out.println("SubCategory with ID " + subCategory.getSubCategoryId() + " not found.");
	    }

	    // Delete a SubCategory by ID
	    public void deleteSubCategory(int subCategoryId) {
	        boolean found = false;
	        for (int i = 0; i < subCategoryList.size(); i++) {
	            if (subCategoryList.get(i).getSubCategoryId() == subCategoryId) {
	                subCategoryList.remove(i);
	                System.out.println("SubCategory with ID " + subCategoryId + " deleted.");
	                found = true;
	                break;
	            }
	        }
	        if (!found) {
	            System.out.println("SubCategory with ID " + subCategoryId + " not found.");
	        }
	    }
	    
	    /*
	     * 
	     */
	    
	 // Create a new policy
	    @Override
	    public void createPolicy(Policy policy) {
	        if (policy == null) {
	            System.out.println("Invalid policy details.");
	            return;
	        }

	        // Add the policy to the database
	        policyList.add(policy);
	        System.out.println("Policy " + policy.getPolicyName() + " has been created.");
	    }

	    // Update an existing policy
	    @Override
	    public void updatePolicy(Policy updatedPolicy) {
	        if (updatedPolicy == null) {
	            System.out.println("Invalid policy details.");
	            return;
	        }

	        // Find and update the policy
	        for (int i = 0; i < policyList.size(); i++) {
	            Policy policy = policyList.get(i);
	            if (policy.getPolicyId() == updatedPolicy.getPolicyId()) {
	                policy.setPolicyName(updatedPolicy.getPolicyName());
	                policy.setPremiumAmount(updatedPolicy.getPremiumAmount());
	                policy.setCoverageAmount(updatedPolicy.getCoverageAmount());
	                policy.setStartDate(updatedPolicy.getStartDate());
	                policy.setEndDate(updatedPolicy.getEndDate());
	                policy.setCategory(updatedPolicy.getCategory());
	                policy.setSubCategory(updatedPolicy.getSubCategory());
	                policy.setStatus(updatedPolicy.getStatus());  // Update status as well
	                System.out.println("Policy with ID " + updatedPolicy.getPolicyId() + " has been updated.");
	                return;
	            }
	        }

	        System.out.println("Policy with ID " + updatedPolicy.getPolicyId() + " not found.");
	    }

	    // Delete a policy by ID
	    @Override
	    public void deletePolicy(int policyId) {
	        for (int i = 0; i < policyList.size(); i++) {
	            if (policyList.get(i).getPolicyId() == policyId) {
	            	policyList.remove(i);
	                System.out.println("Policy with ID " + policyId + " has been deleted.");
	                return;
	            }
	        }
	        System.out.println("Policy with ID " + policyId + " not found.");
	    }

	    // View all policy requests (including applied and cancellation requests)
	    @Override
	    public void viewPolicyRequests() {
	        if (policyList.isEmpty()) {
	            System.out.println("No policies available.");
	            return;
	        }

	        System.out.println("Viewing all policy requests:");
	        for (Policy policy : policyList) {
	            System.out.println(policy);
	        }
	    }

	    // Activate a policy request
	    @Override
	    public void activatePolicyRequest(int policyRequestId) {
	        for (Policy policy : policyList) {
	            if (policy.getPolicyId() == policyRequestId) {
	                // Only activate if the policy is applied or cancellation requested
	                if ("Applied".equals(policy.getStatus())) {
	                    policy.setStatus("Activated");
	                    System.out.println("Policy with ID " + policyRequestId + " has been activated.");
	                } else if ("Cancellation Requested".equals(policy.getStatus())) {
	                    System.out.println("Policy with ID " + policyRequestId + " has cancellation requested. Can't activate.");
	                } else {
	                    System.out.println("Policy with ID " + policyRequestId + " is already activated or canceled.");
	                }
	                return;
	            }
	        }
	        System.out.println("Policy with ID " + policyRequestId + " not found.");
	    }
	    
	    @Override
	    public void cancelPolicyRequest(int policyRequestId) {
	        for (Policy policy : policyList) {
	            if (policy.getPolicyId() == policyRequestId) {
	                // Only allow canceling if the policy is in "Applied" status
	                if ("Applied".equals(policy.getStatus())) {
	                    policy.setStatus("Cancelled");
	                    System.out.println("Policy with ID " + policyRequestId + " has been cancelled by admin.");
	                } else {
	                    System.out.println("Policy with ID " + policyRequestId + " cannot be cancelled (not in Applied state).");
	                }
	                return;
	            }
	        }
	        System.out.println("Policy with ID " + policyRequestId + " not found.");
	    }
		

}
