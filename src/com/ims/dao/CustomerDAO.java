package com.ims.dao;

import com.ims.model.*;


public interface CustomerDAO {
     void applyForPolicy(Policy policy);

	void requestPolicyCancellation(int policyId);

	
    
}

