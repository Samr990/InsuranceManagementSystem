package com.ims.Details;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.ims.dao.AdminDAO;
import com.ims.dao.UserDAO;
import com.ims.model.Category;
import com.ims.model.Customer;
import com.ims.model.Policy;
import com.ims.model.SubCategory;
import com.ims.model.User;

public class AdminDetails {
	
public static void adminMenu(Scanner sc, UserDAO userDAO, AdminDAO admin, int userId) {
		
	boolean t = true;  // Keep running the main menu until user selects to logout

	while (t) {
	    System.out.println(" ------------------------------------------------");
	    System.out.println("|\t1. VIEW User lists                       |");
	    System.out.println("|\t2. ADD/VIEW/UPDATE/DELETE Category       |");
	    System.out.println("|\t3. ADD/VIEW/UPDATE/DELETE Sub-Category   |");
	    System.out.println("|\t4. ADD/VIEW/UPDATE/DELETE Policy         |");
	    System.out.println("|\t5. VIEW/ACTIVATE/CANCEL Policy Request   |");
	    System.out.println("|\t6. Logout                                |");
	    System.out.println(" ------------------------------------------------");
	    System.out.print("Enter your choice: ");
	    
	    if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            sc.next(); // Consume invalid input
            continue;
        }
	    int choice = sc.nextInt();
	    sc.nextLine(); // Consume newline

	    switch (choice) {
	        case 1:
	            // Handle "VIEW User lists"
	            System.out.println("You selected: VIEW User lists");
	            // Add your logic for viewing user lists
	            int count = 0;
	            for(User u : userDAO.getUsers()) {
	            	if(u instanceof Customer) {
	            		System.out.println(++count + ". userID :" +u.getUserId() + "\tuserName: "+ u.getUsername());
	            	}
	            }
	            break;

	        case 2:
	            // Handle "ADD/VIEW/UPDATE/DELETE Category"
	            manageCategoryMenu(sc,userDAO,admin,userId);
	            break;

	        case 3:
	            // Handle "ADD/VIEW/UPDATE/DELETE Sub-Category"
	            manageSubCategoryMenu(sc,userDAO,admin,userId);
	            break;

	        case 4:
	            // Handle "ADD/VIEW/UPDATE/DELETE Policy"
	            managePolicyMenu(sc,  userDAO,  admin,  userId);
	            break;

	        case 5:
	            // Handle "VIEW/ACTIVATE/CANCEL Policy Request"
	            managePolicyRequestMenu(sc,userDAO,admin,userId);
	            break;

	        case 6:
	            // Handle "Logout"
	            System.out.println("Logging out...");
	            t = false;  // Set t to false to break the loop and exit
	            break;

	        default:
	            System.out.println("Invalid choice. Please select a valid option.");
	            break;
	    } //switch
	} // while
		
			
	}//adminMEnu

//Methods to manage Category, Sub-Category, Policy, and Policy Request
private static void manageCategoryMenu(Scanner sc, UserDAO userDAO, AdminDAO admin, int userId) {
 boolean categoryMenu = true;
 while (categoryMenu) {
     System.out.println(" ------------------------------------------------");
     System.out.println("|\t1. Add Category                          |");
     System.out.println("|\t2. View Category                         |");
     System.out.println("|\t3. Update Category                       |");
     System.out.println("|\t4. Delete Category                       |");
     System.out.println("|\t5. Back                                  |");
     System.out.println(" ------------------------------------------------");
     System.out.print("Enter your choice: ");
     
     if (!sc.hasNextInt()) {
         System.out.println("Invalid input! Please enter a number.");
         sc.next(); // Consume invalid input
         continue;
     }
	    int choice = sc.nextInt();
	    sc.nextLine(); // Consume newlineint choice = sc.nextInt();

     switch (choice) {
         case 1:
             // Handle Add Category
             System.out.println("You selected: Add Category");
             System.out.println("Enter category name: ");
             String cName = sc.next();
             int cNum = (int) (Math.random()*1000);
             Category newCat = new Category(cNum,cName);
             List<SubCategory> subList = Arrays.asList(
            		 new SubCategory(2000, "trail Insurance", newCat),
            	        new SubCategory(2001, "second trail Insurance", newCat));
            		 
             newCat.setSubCategories(subList);
             admin.createCategory(newCat, userDAO);
             System.out.println("category added: " + newCat);
             break;
         case 2:
             // Handle View Category
             System.out.println("You selected: View Category");
             List<Category> categories = userDAO.getAllCategories();
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
                     List<SubCategory> subCategories = userDAO.getSubCategoriesByCategoryId(selectedCategoryId);
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
             
             
         case 3:
             // Handle Update Category
             System.out.println("You selected: Update Category");
             for(Category c : userDAO.getAllCategories()) {
            	 System.out.println(c.getCategoryId() + " " + c.getCategoryName());
             }
             System.out.println("Which category do u want to update (pickId): ");
             int pickedId = sc.nextInt();
             sc.nextLine();
             
             System.out.println("Enter category name: ");
             String caName = sc.nextLine();
             System.out.println();
             System.out.println("Enter category desc: ");
             String desc = sc.nextLine();
             Category newC = new Category(pickedId, caName);
             newC.setDescription(desc);
             
             List<Category> newL = admin.updateCategory(newC, userDAO);
             
             userDAO.setCategories(newL);
             System.out.println("Category updated");
             
            	 
            	 
             break;
             
             
         case 4:
             // Handle Delete Category
             System.out.println("You selected: Delete Category");
             for(Category c : userDAO.getAllCategories()) {
            	 System.out.println(c.getCategoryId() + " " + c.getCategoryName());
             }
             System.out.println("Which category do u want to update (pickId): ");
             int pickedId2 = sc.nextInt();
             sc.nextLine();
             
             
             
             List<Category> newLL = admin.deleteCategory(pickedId2, userDAO);
             
             userDAO.setCategories(newLL);
             System.out.println("Category deleted");
             break;
         case 5:
             // Back to the main menu
             categoryMenu = false;
             break;
         default:
             System.out.println("Invalid choice. Please select a valid option.");
             break;
     }
 }
}

private static void manageSubCategoryMenu(Scanner sc, UserDAO userDAO, AdminDAO admin, int userId) {
 boolean subCategoryMenu = true;
 while (subCategoryMenu) {
     System.out.println(" ------------------------------------------------");
     System.out.println("|\t1. Add Sub-Category                     |");
     System.out.println("|\t2. View Sub-Category                    |");
     System.out.println("|\t3. Update Sub-Category                  |");
     System.out.println("|\t4. Delete Sub-Category                  |");
     System.out.println("|\t5. Back                                  |");
     System.out.println(" ------------------------------------------------");
     System.out.print("Enter your choice: ");
     if (!sc.hasNextInt()) {
         System.out.println("Invalid input! Please enter a number.");
         sc.next(); // Consume invalid input
         continue;
     }
	    int choice = sc.nextInt();
	    sc.nextLine(); // Consume newline

     switch (choice) {
         case 1:
             // Handle Add Sub-Category
             System.out.println("You selected: Add Sub-Category");
            
             System.out.println("Enter sub category name: ");
             String scName = sc.next();
             int cNum = (int) (Math.random()*1000);
             System.out.println("Enter category name: ");
             String cName = sc.next();
             boolean categoryExists = false;
             SubCategory newSc = new SubCategory(cNum, cName);
             for(Category c : userDAO.getAllCategories()) {
            	 if(c.getCategoryName().equals(cName)) {
            		 newSc.setParentCategory(c);
            		 System.out.println("New subcategory created " + newSc.getSubCategoryName() + "with category "  + c.getCategoryName());
            	 }
             }
             if(!categoryExists) {
            	 System.out.println("sub category "+ newSc.getSubCategoryName() +" added without a category");
             }
             
             
             admin.createSubCategory(newSc, userDAO);
           
      
             break;
         case 2:
             // Handle View Sub-Category
             System.out.println("You selected: View Sub-Category");
             for(SubCategory s : userDAO.getAllSubcategories() ) {
            	 System.out.println(s.getSubCategoryId() + " " + s.getSubCategoryName());
             }
             break;
         case 3:
             // Handle Update Sub-Category
             System.out.println("You selected: Update Sub-Category");
             for(SubCategory s : userDAO.getAllSubcategories() ) {
            	 System.out.println(s.getSubCategoryId() + " " + s.getSubCategoryName());
             }
             
             System.out.println("Which subcategory do u want to update (pickId): ");
             int pickedId = sc.nextInt();
             sc.nextLine();
             
             System.out.println("Enter subcategory name: ");
             String scaName = sc.nextLine();
             System.out.println();
             System.out.println("Enter sub category desc: ");
             String desc = sc.nextLine();
             System.out.println("Enter category name: ");
             String catgName = sc.nextLine();
             Category newCategory = null;
             boolean flag = false;
             for(Category c : userDAO.getAllCategories()) {
            	 if(c.getCategoryName().equals(catgName)) {
            		 newCategory = c;
            		 flag = true;
            	 }
             }
             if(!flag) {
            	 newCategory = new Category((int)Math.random()*1000, catgName);
             }
             SubCategory newSubcat = new SubCategory(pickedId, scaName, newCategory);
             newSubcat.setDescription(desc);
             
             
             List<SubCategory> newL = admin.updateSubCategory(newSubcat, userDAO);
             
             userDAO.setSubCategory(newL);
             System.out.println("SubCategory updated");
             
             break;
         case 4:
             // Handle Delete Sub-Category
             System.out.println("You selected: Delete Sub-Category");
        
             for(SubCategory s : userDAO.getAllSubcategories() ) {
            	 System.out.println(s.getSubCategoryId() + " " + s.getSubCategoryName());
             }
             
             System.out.println("Which subcategory do u want to delete (pickId): ");
             int deleteId = sc.nextInt();
             sc.nextLine();
             
           
             
             List<SubCategory> newLL = admin.deleteSubCategory(deleteId, userDAO);
             
             userDAO.setSubCategory(newLL);
             System.out.println("SubCategory deleted");
             break;
         case 5:
             // Back to the main menu
             subCategoryMenu = false;
             break;
         default:
             System.out.println("Invalid choice. Please select a valid option.");
             break;
     }
 }
}

private static void managePolicyMenu(Scanner sc, UserDAO userDAO, AdminDAO admin, int userId) {
 boolean policyMenu = true;
 while (policyMenu) {
     System.out.println(" ------------------------------------------------");
     System.out.println("|\t1. Add Policy                           |");
     System.out.println("|\t2. View Policy                          |");
     System.out.println("|\t3. Update Policy                        |");
     System.out.println("|\t4. Delete Policy                        |");
     System.out.println("|\t5. Back                                  |");
     System.out.println(" ------------------------------------------------");
     System.out.print("Enter your choice: ");
     if (!sc.hasNextInt()) {
         System.out.println("Invalid input! Please enter a number.");
         sc.next(); // Consume invalid input
         continue;
     }
	    int choice = sc.nextInt();
	    sc.nextLine(); // Consume newline

     switch (choice) {
         case 1:
             // Handle Add Policy
             System.out.println("You selected: Add Policy");
             System.out.println("Enter Policy name: ");
             String pName = sc.next();
             int pNum = (int) (Math.random()*1000);
//             public Policy(int policyId, String policyName, double premiumAmount, double coverageAmount,
//                     LocalDate startDate, LocalDate endDate) {
             System.out.println("Enter premium Amount ");
             double premAmt = sc.nextDouble();
             System.out.println("Enter coverage Amount ");
             double coverAmt = sc.nextDouble();
             System.out.println("Enter how many years the policy last in years: ");
             int time = sc.nextInt();
             
             Policy newP = new Policy(pNum, pName, premAmt, coverAmt, LocalDate.now(), LocalDate.now().plusYears(time));
             admin.createPolicy(newP, userDAO);
             System.out.println("New polict added: " + newP);
             break;
         case 2:
             // Handle View Policy
             System.out.println("You selected: View Policy");
             for(Policy p : userDAO.getPolicyList()) {
            	 System.out.println(p);
             }
             break;
         case 3:
             // Handle Update Policy
             System.out.println("You selected: Update Policy");
             for(Policy p : userDAO.getPolicyList()) {
            	 System.out.println(p.getPolicyId() + " " + p.getPolicyName());
             }
             System.out.println("Which category do u want to update (pickId): ");
             int pickedId = sc.nextInt();
             sc.nextLine();
             
             System.out.println("Enter policy name: ");
             String newName = sc.nextLine();
             System.out.println("Enter premium Amount ");
             double premiumAmt = sc.nextDouble();
             System.out.println("Enter coverage Amount ");
             double coverageAmt = sc.nextDouble();
             System.out.println("Enter how many years the policy lasts in years: ");
             int time2 = sc.nextInt();
             Policy newPolicy = new Policy(pickedId, newName, premiumAmt, coverageAmt, LocalDate.now(), LocalDate.now().plusYears(time2));
             List<Policy> newL = admin.updatePolicy(newPolicy, userDAO);
             
             userDAO.setPolicies(newL);
             System.out.println("Policy updated");
             break;
         case 4:
             // Handle Delete Policy
             System.out.println("You selected: Delete Policy");
             for(Policy p : userDAO.getPolicyList()) {
            	 System.out.println(p.getPolicyId() + " " + p.getPolicyName());
             }
             System.out.println("Which category do u want to update (pickId): ");
             int pickedId2 = sc.nextInt();
             sc.nextLine();
             
             
             
             List<Policy> newLL = admin.deletePolicy(pickedId2, userDAO);
             
             userDAO.setPolicies(newLL);
             System.out.println("Policy deleted");
             break;
         case 5:
             // Back to the main menu
             policyMenu = false;
             break;
         default:
             System.out.println("Invalid choice. Please select a valid option.");
             break;
     }
 }
}

private static void managePolicyRequestMenu(Scanner sc, UserDAO userDAO, AdminDAO admin, int userId) {
 boolean policyRequestMenu = true;
 while (policyRequestMenu) {
     System.out.println(" ------------------------------------------------");
     System.out.println("|\t1. View Policy Requests                 |");
     System.out.println("|\t2. Activate Policy Request              |");
     System.out.println("|\t3. Cancel Policy Request                |");
     System.out.println("|\t4. Back                                  |");
     System.out.println(" ------------------------------------------------");
     System.out.print("Enter your choice: ");
     if (!sc.hasNextInt()) {
         System.out.println("Invalid input! Please enter a number.");
         sc.next(); // Consume invalid input
         continue;
     }
	    int choice = sc.nextInt();
	    sc.nextLine(); // Consume newline

     switch (choice) {
         case 1:
             // Handle View Policy Request
             System.out.println("You selected: View Policy Request");
             admin.viewPolicyRequests(userDAO);
             break;
         case 2:
             // Handle Activate Policy Request
             System.out.println("You selected: Activate Policy Request");
             for(Policy p : userDAO.getPolicyList()) {
               	 if(p.getStatus().equalsIgnoreCase("applied")) {
               		 System.out.println(p.getPolicyId() + " " + p.getPolicyName());
               	 }
                }
             System.out.println("pick a policy to activate (pick policyId: ");
             int pickedId = sc.nextInt();
             sc.nextLine();
             admin.activatePolicyRequest(pickedId, userDAO);
             break;
         case 3:
             // Handle Cancel Policy Request
             System.out.println("You selected: Cancel Policy Request");
             for(Policy p : userDAO.getPolicyList()) {
               	 if(p.getStatus().equalsIgnoreCase("applied")) {
               		 System.out.println(p.getPolicyId() + " " + p.getPolicyName());
               	 }
                }
             System.out.println("pick a policy to cancel (pick policyId: ");
             int pickedId2 = sc.nextInt();
             sc.nextLine();
             admin.cancelPolicyRequest(pickedId2, userDAO);
             break;
         case 4:
             // Back to the main menu
             policyRequestMenu = false;
             break;
         default:
             System.out.println("Invalid choice. Please select a valid option.");
             break;
     }
 }
}
	

}
