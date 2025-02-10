package com.ims.dao;

import java.util.List;

import com.ims.model.*;

public interface AdminDAO extends UserDAO{
	 	void createCategory(Category category, UserDAO user);
	    List<Category> updateCategory(Category category, UserDAO user);
	    List<Category> deleteCategory(int categoryId, UserDAO user);
	    
	    void createSubCategory(SubCategory subCategory, UserDAO user);
	    List<SubCategory> updateSubCategory(SubCategory subCategory, UserDAO user);
	    List<SubCategory> deleteSubCategory(int subCategoryId, UserDAO user);
	    
	    void createPolicy(Policy policy, UserDAO user);
	    List<Policy> updatePolicy(Policy policy, UserDAO user);
	    List<Policy> deletePolicy(int policyId, UserDAO user);
	    
	    void viewPolicyRequests(UserDAO user);
	   void activatePolicyRequest(int policyRequestId, UserDAO user);
	    void cancelPolicyRequest(int policyRequestId, UserDAO user);
}
