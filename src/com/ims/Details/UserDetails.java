package com.ims.Details;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.CustomerDAO;
import com.ims.dao.UserDAO;

import com.ims.model.Category;
import com.ims.model.Customer;
import com.ims.model.Policy;
import com.ims.model.SubCategory;
import com.ims.model.User;

public class UserDetails {
	


	public static void customerMenu(Scanner sc, UserDAO user, CustomerDAO customer, int userId) {
		
		while(true) {
		
		System.out.println(" ------------------------------------------------");
		System.out.println("|\t1. VIEW All Categories                   |");
		System.out.println("|\t2. APPLY for a Policy                    |");
		System.out.println("|\t3. VIEW Policy List                      |");
		System.out.println("|\t4. Logout                                  |");
		System.out.println(" ------------------------------------------------");
		
		int choice = sc.nextInt();
		
		  switch (choice) {
                case 1:
                    List<Category> categories = user.getAllCategories();
                    if (categories.isEmpty()) {
                        System.out.println("No categories available.");
                    } else {
                        System.out.println("Available Categories:");
                        for (Category category : categories) {
                            System.out.println(category.getCategoryId() + ". " + category.getCategoryName());
                        }
                        System.out.print("Enter Category ID to view subcategories (or 0 to return): ");
                        int selectedCategoryId = sc.nextInt();
                        sc.nextLine();
                        if (selectedCategoryId != 0) {
                            List<SubCategory> subCategories = user.getSubCategoriesByCategoryId(selectedCategoryId);
                            if (subCategories.isEmpty()) {
                                System.out.println("No subcategories available for this category.");
                            } else {
                                System.out.println("Available Subcategories:");
                                for (SubCategory sub : subCategories) {
                                    System.out.println(sub.getSubCategoryId() + ". " + sub.getSubCategoryName());
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    // Retrieve the user's policies using the getPoliciesByUserId method from UserDAOImpl
                    List<Policy> policyList = user.getPolicyList(); // Ensure you cast to CustomerDAO to access method
                    int count = 0;

                    // Display the available policies
                    for (Policy p : policyList) {
                        System.out.println(count++ + ". " + p);
                    }

                    // Prompt the user to pick a policy to apply
                    System.out.println("Pick a Policy to apply using policyId: ");
                    System.out.println();
                    System.out.println("Users List: " + user.getUsers());  // Debugging line

                    int pickedId = sc.nextInt();
                    boolean policyFound = false;
                    
                    // Retrieve the current user by their userId
                    User currentUser = user.getUserById(userId);

                    // Check if user exists
                    if (currentUser == null) {
                        System.out.println("Customer not found for userId: " + userId);
                        break;
                    }

                    // Loop through the policy list to find the selected policy
                    for (Policy p : policyList) {
                        if (pickedId == p.getPolicyId()) {
                            // Apply the policy logic here
                            System.out.println("Applying for Policy: " + p.getPolicyName());

                            // Ensure we're dealing with a Customer instance
                            if (currentUser instanceof Customer) {
                                Customer customer1 = (Customer) currentUser;
                              customer.applyForPolicy(userId, user, p);
                            }
                            policyFound = true;
                            break; // Exit after applying the policy
                        }
                    }

                    // If no such policy was found
                    if (!policyFound) {
                        System.out.println("No such policy found.");
                    }
                    break;


                	
                    
                case 3:
                    List<Policy> policies = user.getPoliciesByUserId(userId);
                    if (policies.isEmpty()) {
                        System.out.println("No policies found for your account.");
                    } else {
                        System.out.println("Your Policies:");
                        for (Policy policy : policies) {
                            System.out.println("Policy ID: " + policy.getPolicyId() + ", Name: " + policy.getPolicyName() + ", Status: " + policy.getStatus());
                        }
                    }
                    break;
                
                case 4:
                    System.out.println("Logging out...");
                    return;
                
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            } //switch
		
		
		} //while
	}
	
	


}
