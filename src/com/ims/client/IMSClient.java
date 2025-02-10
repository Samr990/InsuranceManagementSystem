package com.ims.client;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ims.dao.impl.*;
import com.ims.dao.*;

public class IMSClient {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

     
       
        UserDAO users = new UserDAOImpl();
        
        Scanner sc = new Scanner(System.in);
        

        while (true) {

            System.out.println(" ------------------------------------------------");
            System.out.println("|\t1. Login                              |");
            System.out.println("|\t2. Register                           |");
            System.out.println("|\t3. Exit                               |");
            System.out.println(" ------------------------------------------------");
            System.out.print("Make a choice: ");

            int num = 0;
            
            // Handle invalid input by wrapping the input reading with try-catch block
            try {
                num = sc.nextInt();
                sc.nextLine(); // Consume the newline character after the integer input
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear the invalid input
                continue; // Go back to the menu
            }

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
