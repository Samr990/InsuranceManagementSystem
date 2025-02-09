package com.ims.dao;

import java.util.List;
import java.util.Scanner;

import com.ims.model.*;

public interface UserDAO {
	void registerUser(Scanner sc);
	void logInUser(Scanner sc);
    User getUserByUsername(String username);
    boolean authenticateUser(String username, String password);
    List<Category> getAllCategories();
    List<SubCategory> getSubCategoriesByCategoryId(int categoryId);
    List<Policy> getPoliciesByUserId(int userId);
	List<Policy> getPolicyList();

}
