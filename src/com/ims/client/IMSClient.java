package com.ims.client;

import java.util.Scanner;

import com.ims.dao.impl.*;
import com.ims.model.*;
import com.ims.dao.*;

public class IMSClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Category healthInsurance = new Category(121, "Health Insurance");
		SubCategory dentalIns = new SubCategory(12101, "Dental Insurance", healthInsurance);
		SubCategory mentalIns = new SubCategory(12102, "Mental Insurance", healthInsurance);

		int adminCode = 101;
		Customer u = null;
		Admin a = null;
//		CustomerDAOImpl customers =  new CustomerDAOImpl();
//		AdminDAOImpl admins = new AdminDAOImpl();
		UserDAO users = new UserDAOImpl();
		
		Scanner sc = new Scanner(System.in);
		boolean registered = false; // flag

		while (true) {

			System.out.println(" ------------------------------------------------");
			System.out.println("|\t1. Login                              |");
			System.out.println("|\t2. Register                           |");
			System.out.println("|\t3. Exit                               |");
			System.out.println(" ------------------------------------------------");
			System.out.print("Make a choice ");

			int num = sc.nextInt();

			switch (num) {
			case 1:
				users.logInUser(sc);
				break;

			case 2:
				users.registerUser(sc);
				break;

			case 3:
				sc.close();
				System.out.println("Program is closing!!");

				System.exit(0);

			default:
				System.out.println("Enter a valid choice!");

			}

		} // while

	}

	

}
