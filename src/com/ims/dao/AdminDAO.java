package com.ims.dao;

import com.ims.model.*;

public interface AdminDAO extends UserDAO{
	 	void createCategory(Category category);
	    void updateCategory(Category category);
	    void deleteCategory(int categoryId);
	    
	    void createSubCategory(SubCategory subCategory);
	    void updateSubCategory(SubCategory subCategory);
	    void deleteSubCategory(int subCategoryId);
	    
	    void createPolicy(Policy policy);
	    void updatePolicy(Policy policy);
	    void deletePolicy(int policyId);
	    
	    void viewPolicyRequests();
	    void activatePolicyRequest(int policyRequestId);
	    void cancelPolicyRequest(int policyRequestId);
}
