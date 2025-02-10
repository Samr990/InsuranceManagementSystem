package com.ims.dao;



import com.ims.model.*;


public interface CustomerDAO extends UserDAO {
     void applyForPolicy(int userId, UserDAO userDAO, Policy policy);

	

//	void requestPolicyCancellation(int policyId);

	
    
}

