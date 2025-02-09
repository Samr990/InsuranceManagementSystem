package com.ims.Details;

import java.util.List;
import java.util.Scanner;

import com.ims.dao.AdminDAO;
import com.ims.dao.CustomerDAO;
import com.ims.dao.UserDAO;
import com.ims.model.Admin;
import com.ims.model.Category;
import com.ims.model.Customer;
import com.ims.model.Policy;
import com.ims.model.SubCategory;
import com.ims.model.User;

public class UserDetails {
	

	
	public static void customerMenu(Scanner sc) {
        System.out.println("\n*** Customer Menu ***");
        System.out.println("1. View Policies");
        System.out.println("2. Apply for Policy");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        if (choice == 3) {
            System.out.println("Logging out...");
            return;
        }
        System.out.println("Feature not implemented yet.");
    }
	public static void adminMenu(Scanner sc) {
		
		System.out.println(" ------------------------------------------------");
		System.out.println("|\t1. VIEW User lists                       |");
		System.out.println("|\t2. ADD/VIEW/UPDATE/DELETE Category       |");
		System.out.println("|\t3. ADD/VIEW/UPDATE/DELETE Sub-Category   |");
		System.out.println("|\t4. ADD/VIEW/UPDATE/DELETE Policy         |");
		System.out.println("|\t5. VIEW/ACTIVATE/CANCEL Policy Request   |");
		System.out.println("|\t6. Back                                  |");
		System.out.println(" ------------------------------------------------");
		
			
	}
	
	public static void customerMenu(Scanner sc, UserDAO customer, int userId) {
		
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
                    List<Category> categories = customer.getAllCategories();
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
                            List<SubCategory> subCategories = customer.getSubCategoriesByCategoryId(selectedCategoryId);
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
                	List<Policy> policyList = customer.getPolicyList();
                	int count = 0;
                	for(Policy p : policyList) {
                		System.out.println(count++ + ". " + p);
                	}
                	System.out.println("Pick a Policy to apply using policyId: ");
                	int pickedId = sc.nextInt();
                	int policyCount =0;
                	for(Policy p : policyList) {
                		if(pickedId == p.getPolicyId()) {
                			((CustomerDAO) customer).applyForPolicy(p);
                			policyCount++;
                		
                		}
                		
                	}// for
                	if (policyCount == 0) {
                		System.out.println("No such policy found.");
                	}
                	break;
                	
                    
                case 3:
                    List<Policy> policies = customer.getPoliciesByUserId(userId);
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
