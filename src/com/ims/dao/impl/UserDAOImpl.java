package com.ims.dao.impl;

import com.ims.Details.AdminDetails;
import com.ims.Details.UserDetails;
import com.ims.dao.*;
import com.ims.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



public class UserDAOImpl implements UserDAO {
    private static final int adminCode = 101;  // Hardcoded admin code
    private  List<User> users = new ArrayList<>();

    // Hardcoded Categories
    List<Category> categories = new ArrayList<>(Arrays.asList(
    	    new Category(121, "Health Insurance"),
    	    new Category(122, "Auto Insurance"),
    	    new Category(123, "Life Insurance")
    	));


    // Hardcoded Subcategories
    List<SubCategory> subCategories = new ArrayList<>(Arrays.asList(
    	    new SubCategory(12101, "Dental Insurance", categories.get(0)),
    	    new SubCategory(12102, "Mental Insurance", categories.get(0)),
    	    new SubCategory(12201, "Car Insurance", categories.get(1)),
    	    new SubCategory(12202, "Bike Insurance", categories.get(1)),
    	    new SubCategory(12301, "Term Life Insurance", categories.get(2)),
    	    new SubCategory(12302, "Whole Life Insurance", categories.get(2))
    	));


    // Hardcoded Policies
    List<Policy> policies = new ArrayList<>(Arrays.asList(
    	    new Policy(1001, "Basic Dental Plan", 500, 10000, LocalDate.now(), LocalDate.now().plusYears(1)),
    	    new Policy(1002, "Mental Health Plus", 700, 15000, LocalDate.now(), LocalDate.now().plusYears(1)),
    	    new Policy(1003, "Car Comprehensive", 1200, 20000, LocalDate.now(), LocalDate.now().plusYears(1)),
    	    new Policy(1004, "Bike Third-Party", 400, 5000, LocalDate.now(), LocalDate.now().plusYears(1)),
    	    new Policy(1005, "Term Life 20 Years", 2000, 500000, LocalDate.now(), LocalDate.now().plusYears(20)),
    	    new Policy(1006, "Whole Life Premium", 3000, 1000000, LocalDate.now(), LocalDate.now().plusYears(50))
    	));
    
    
    

    public UserDAOImpl() {
        users = new ArrayList<>();
        policies.get(1).setStatus("Applied");
        policies.get(3).setStatus("Applied");
    }

    // Get a user by username (refactor to improve role check)
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user; // Return user if found
            }
        }
        return null; // Return null if not found
    }

    // Authenticate user by username and password
    public boolean authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    // Get all categories (unchanged)
    public List<Category> getAllCategories() {
        return categories;
    }

    // Get subcategories by category ID (unchanged)
    public List<SubCategory> getSubCategoriesByCategoryId(int categoryId) {
        List<SubCategory> result = new ArrayList<>();
        for (SubCategory subCategory : subCategories) {
            if (subCategory.getParentCategory().getCategoryId() == categoryId) {
                result.add(subCategory);
            }
        }
        return result.isEmpty() ? Collections.emptyList() : result;
    }

    // Get policies by user ID (unchanged)
    public List<Policy> getPoliciesByUserId(int userId) {
        List<Policy> userPolicies = new ArrayList<>();
        for (User user : users) {
            if (user.getUserId() == userId && user instanceof Customer) {
                Customer customer = (Customer) user;
                userPolicies.addAll(customer.getPolicies());
            }
        }
        return userPolicies.isEmpty() ? Collections.emptyList() : userPolicies;
    }

    // Login user and delegate to different menus based on role
    public void logInUser(Scanner sc) {
        System.out.print("\nEnter Username: ");
        String username = sc.next();
        System.out.print("Enter Password: ");
        String password = sc.next();

        User user = getUserByUsername(username);  // Get the user once to avoid calling it multiple times

        if (user != null && authenticateUser(username, password)) {
            System.out.println("\nWelcome, " + user.getUsername() + " (" + user.getRole() + ")");
            
            // Handling user roles more efficiently
            if (user instanceof Admin) {
            	AdminDAO adminDAO = new AdminDAOImpl();
                AdminDetails.adminMenu(sc, this, adminDAO, user.getUserId());
            } else if (user instanceof Customer) {
                CustomerDAO customerDAO = new CustomerDAOImpl();
                UserDetails.customerMenu(sc, this, customerDAO, user.getUserId());
            }
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }


 // Track highest userId with a counter
    private int userIdCounter = 1000;  // Starting ID

    public int generateUserId() {
        return userIdCounter++; // Increment and return userId
    }

    public void registerUser(Scanner sc) {
        System.out.println("--------------------------");
        System.out.println("1. Register as Customer");
        System.out.println("2. Register as Admin");
        System.out.println("--------------------------");
        System.out.print("Make a choice: ");
        int r = sc.nextInt();
        sc.nextLine(); // Consume newline

        int userId = generateUserId(); // Get a unique userId

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

            users.add(new Customer(userId, uname, pwdd, email, fullName));
            System.out.println("Customer created!");

        } else if (r == 2) {
            System.out.print("Enter Admin code: ");
            int code = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (code == adminCode) {
                System.out.println("Permission granted! Follow the instructions");

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

                users.add(new Admin(userId, uname, pwdd, email, fullName));
                System.out.println("Admin created!");
            } else {
                System.out.println("Invalid admin code! Registration failed.");
            }
        } else {
            System.out.println("Invalid choice! Registration aborted.");
        }

        // Debugging: Check the total number of users and print list
        System.out.println("Total users after registration: " + users.size());
        System.out.println("Users in the system: " + users);
    }

    
    public List<User> getUsers() {
        return users;  // Provide access to the list of users
    }

	@Override
	public List<Policy> getPolicyList() {
		// TODO Auto-generated method stub
		return policies;
	}
	
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		 for (User user : users) {
	            if (user.getUserId() == userId) {
	                return user; // Return user if found
	            }
	        }
	        return null;
	}

	@Override
	public void addToCategoryList(Category category) {
		// TODO Auto-generated method stub
		categories.add(category);
	}
	
	public void setCategories(List<Category> list) {
		this.categories = list;
	}
	
	public List<SubCategory> getAllSubcategories(){
		return this.subCategories;
	}

	@Override
	public void addToSubcategoryList(SubCategory subCategory) {
		// TODO Auto-generated method stub
		this.subCategories.add(subCategory);
	}

	@Override
	public void setSubCategory(List<SubCategory> list) {
		// TODO Auto-generated method stub
		this.subCategories = list;
	}

	@Override
	public void addToPolicyList(Policy policy) {
		// TODO Auto-generated method stub
		this.policies.add(policy);
	}

	@Override
	public void setPolicies(List<Policy> list) {
		// TODO Auto-generated method stub
		this.policies=list;
	}
}
