package com.ims.dao.impl;

import com.ims.Details.UserDetails;
import com.ims.dao.*;
import com.ims.dao.UserDAO;
import com.ims.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class UserDAOImpl implements UserDAO {
	private int adminCode = 101;
	private List<User> users = new ArrayList<>();
//	private List<Category> categories = new ArrayList<>();
//	private List<SubCategory> subCategories = new ArrayList<>();
//	private List<Policy> policies = new ArrayList<>();

	 // Hardcoded Categories
    List<Category> categories = Arrays.asList(
            new Category(121, "Health Insurance"),
            new Category(122, "Auto Insurance"),
            new Category(123, "Life Insurance")
    );

    // Hardcoded Subcategories, linking correctly to Categories
    List<SubCategory> subCategories = Arrays.asList(
            new SubCategory(12101, "Dental Insurance", categories.get(0)),
            new SubCategory(12102, "Mental Insurance", categories.get(0)),
            new SubCategory(12201, "Car Insurance", categories.get(1)),
            new SubCategory(12202, "Bike Insurance", categories.get(1)),
            new SubCategory(12301, "Term Life Insurance", categories.get(2)),
            new SubCategory(12302, "Whole Life Insurance", categories.get(2))
    );

    // Hardcoded Policies, linking to SubCategories
    List<Policy> policies = Arrays.asList(
            new Policy(1001, 1, "Basic Dental Plan", 500, 10000, LocalDate.now(), LocalDate.now().plusYears(1), categories.get(0), subCategories.get(0)),
            new Policy(1002, 2, "Mental Health Plus", 700, 15000, LocalDate.now(), LocalDate.now().plusYears(1), categories.get(0), subCategories.get(1)),
            new Policy(1003, 3, "Car Comprehensive", 1200, 20000, LocalDate.now(), LocalDate.now().plusYears(1), categories.get(1), subCategories.get(2)),
            new Policy(1004, 4, "Bike Third-Party", 400, 5000, LocalDate.now(), LocalDate.now().plusYears(1), categories.get(1), subCategories.get(3)),
            new Policy(1005, 5, "Term Life 20 Years", 2000, 500000, LocalDate.now(), LocalDate.now().plusYears(20), categories.get(2), subCategories.get(4)),
            new Policy(1006, 6, "Whole Life Premium", 3000, 1000000, LocalDate.now(), LocalDate.now().plusYears(50), categories.get(2), subCategories.get(5))
    );
	
    
    public List<Policy> getPolicyList(){
		return policies;
    	
    }
	
	
	
	
	
	
	

	public User getUserByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null; // User not found
	}

	public boolean authenticateUser(String username, String password) {
		User user = getUserByUsername(username);
		return user != null && user.getPassword().equals(password);
	}

	public List<Category> getAllCategories() {
		return Collections.unmodifiableList(categories);
	}

	public List<SubCategory> getSubCategoriesByCategoryId(int categoryId) {
		List<SubCategory> result = new ArrayList<>();
		for (SubCategory subCategory : subCategories) {
			if (subCategory.getParentCategory().getCategoryId() == categoryId) {
				result.add(subCategory);
			}
		}
		return result.isEmpty() ? Collections.emptyList() : result;
	}

	public List<Policy> getPoliciesByUserId(int userId) {
		List<Policy> userPolicies = new ArrayList<>();
		for (Policy policy : policies) {
			if (policy.getUserId() == userId) {
				userPolicies.add(policy);
			}
		}
		return userPolicies.isEmpty() ? Collections.emptyList() : userPolicies;
	}

	public void logInUser(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.print("\nEnter Username: ");
		String username = sc.next();
		System.out.print("Enter Password: ");
		String password = sc.next();

		if (authenticateUser(username, password)) {
			User user = getUserByUsername(username);
			System.out.println("\nWelcome, " + user.getUsername() + " (" + user.getRole() + ")");

			if (user instanceof Admin) {
				UserDetails.adminMenu(sc);
			} else if (user instanceof Customer) {
//				UserDetails.customerMenu(sc, this);
				UserDetails.customerMenu(sc, this, ((Customer) user).getUserId());
			}
		} else {
			System.out.println("Invalid credentials. Please try again.");
		}

	} // loginUser
	
	public void registerUser(Scanner sc) {
	    System.out.println("--------------------------");
	    System.out.println("1. Register as Customer");
	    System.out.println("2. Register as Admin");
	    System.out.println("--------------------------");
	    System.out.print("Make a choice: ");
	    int r = sc.nextInt();
	    sc.nextLine(); // Consume newline

	    if (r == 1) {
	        String uname;
	        while (true) {
	            System.out.print("Enter Username: ");
	            uname = sc.nextLine();
	            if (getUserByUsername(uname) != null) {
	                System.out.println("Username already exists! Please enter a different one.");
	            } else {
	                break;
	            }
	        }

	        System.out.print("Enter Password: ");
	        String pwdd = sc.nextLine();
	        System.out.print("Enter Email: ");
	        String email = sc.nextLine();
	        System.out.print("Enter Full Name: ");
	        String fullName = sc.nextLine();
	        
	        users.add(new Customer(100, uname, pwdd, email, fullName));
	        System.out.println("Customer created!");

	    } else if (r == 2) {
	        System.out.print("Enter Admin code: ");
	        int code = sc.nextInt();
	        sc.nextLine(); // Consume newline

	        if (code == adminCode) {
	            System.out.println("Permission granted!! Follow the instructions");

	            String uname;
	            while (true) {
	                System.out.print("Enter Username: ");
	                uname = sc.nextLine();
	                if (getUserByUsername(uname) != null) {
	                    System.out.println("Username already exists! Please enter a different one.");
	                } else {
	                    break;
	                }
	            }

	            System.out.print("Enter Password: ");
	            String pwdd = sc.nextLine();
	            System.out.print("Enter Email: ");
	            String email = sc.nextLine();
	            System.out.print("Enter Full Name: ");
	            String fullName = sc.nextLine();

	            users.add(new Admin(1000, uname, pwdd, email, fullName));
	            System.out.println("Admin created!");
	        } else {
	            System.out.println("Invalid admin code! Registration failed.");
	        }
	    } else {
	        System.out.println("Invalid choice! Registration aborted.");
	    }
	} //registerUser

}
