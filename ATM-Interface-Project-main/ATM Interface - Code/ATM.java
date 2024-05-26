/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.atm_interface_project;

/**
 *
 * @author Philemon
 */
import java.util.*;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
public class ATM
{
    public static Scanner sc = new Scanner(System.in);
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static String accountFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/AccountDir.txt";
    @SuppressWarnings("empty-statement")
    public static void main(String args[])throws IOException
    {
        boolean flag = false;
        int choice = 0;
        while(!flag)
        {
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println("1. Log In\n2. Exit Interface");
            System.out.println("Enter your purpose (1 or 2) :");
            try   //get input of the choice from among the two
            {
                choice = Integer.parseInt(input.readLine());
                flag = true;
            }
            catch(NumberFormatException e)   //exception if entered a non-numerical value thus handled
            {
                System.out.println("\nEntered non-numerical value. Try again");
                for(int j = 1; j<=2; j++)
                    for(long i = -999999999;i<999999999;i++);  
            }
        }
        switch(choice)
        {
            case 1 -> {
                login();   //invokes login() of ATM class
                main(args);   //invokes main() of ATM class
            }
            case 2 -> {
                System.out.println("\nExited ATM Interface.");  //exit msg
                System.exit(0);  //exits the program
            }
            default -> {
                System.out.println("\nInvalid Choice. Try again.");    //invalid input msg
                for(int j = 1; j<=2; j++)    //time delay loop
                    for(long i = -999999999;i<999999999;i++);  
                main(args);   //invokes main() of ATM class
            }
        }
    }
    @SuppressWarnings({"SuspiciousIndentAfterControlStatement", "empty-statement", "UnusedAssignment"})
    public static void login()throws IOException
    {
        boolean backToHome = false;
        while(!backToHome)
        {
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println("Enter USER_ID : ");
            String id = input.readLine();    //input for user id
            System.out.println("Enter PIN : ");
            String pin = input.readLine();   //input for pin
            String name = checkUser(id, pin);   //validation of id and pin; if true gets name else null
            if(name.equals("NULL"))     //if null access is denied
            {
                System.out.println("\nAccess Denied...Try again!!!");
                System.out.println("\nPress 1 to Retry\nPress 2 to go Back to Home page");    //choice of retry or back to home page
                int pageTransfer = sc.nextInt();
                while(pageTransfer!=1 && pageTransfer!=2)
                pageTransfer = sc.nextInt();
                if(pageTransfer==1)
                {
                    for(int j = 1; j<=2; j++)
                        for(long i = -999999999;i<999999999;i++);
                }
                else
                {
                    backToHome = true;
                    break;
                }
            }
            else   //user validated
            {
                System.out.println("\nUser Validated!!!");
                for(int j = 1; j<=2; j++)  //time delay loop
                    for(long i = -999999999;i<999999999;i++);
                menu(id, name);    //invokes menu() of ATM class 
                break;
            }
        }
    }
    @SuppressWarnings("empty-statement")
    public static void menu(String id, String name)throws IOException
    {
        boolean flag = false;
        int choice = 0;
        while(!flag)
        {
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println(">>>> USER ID : "+ id + String.format("%60s", "USER NAME : "+name) + " <<<<");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  M E N U  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Transaction History\n5. Quit Session");
            System.out.println("Enter your purpose (1-5) :");
            try    //get input of the choice from among the five
            {
                choice = Integer.parseInt(input.readLine());
                flag = true;
            }
            catch(NumberFormatException e)   //exception if entered a non-numerical value thus handled
            {
                System.out.println("\nEntered non-numerical value. Try again");
                for(int j = 1; j<=2; j++)
                    for(long i = -999999999;i<999999999;i++);  
            }
        }
        switch(choice)
        {
            case 1 -> {
                DepositMoney depObj = new DepositMoney();   //DepositMoney Class object creation
                depObj.deposit(id, name); //invokes deposit() of DepositMoney class
                System.gc();   //garbage collector
                menu(id, name);   //invokes menu() of ATM class
            }
            case 2 -> {
                WithdrawMoney withObj = new WithdrawMoney();   //WithdrawMoney Class object creation
                withObj.withdraw(id, name);   //invokes withdraw() of WithdrawMoney class
                System.gc();   //garbage collector
                menu(id, name);   //invokes menu() of ATM class
            }
            case 3 -> {
                TransferMoney transferObj = new TransferMoney();   //TransferMoney Class object creation
                transferObj.transfer(id, name);   //invokes transfer() of TransferMoney class
                System.gc();   //garbage collector
                menu(id, name);   //invokes menu() of ATM class
            }
            case 4 -> {
                TransactionHistory transactObj = new TransactionHistory();   //TransferMoney Class object creation
                transactObj.transactHist(id, name);   //invokes transact() of TransactionHistory class
                System.gc();   //garbage collector
                menu(id, name);   //invokes menu() of ATM class
            }
            case 5 -> {
                System.out.println("\nThank You for trusting Octanet.\nYour session has ended.\n\n");   //Exit msg
                for(int j = 1; j<=2; j++)   //time delay loop
                    for(long i = -999999999;i<999999999;i++);
            }
            default -> {
                System.out.println("\nInvalid Choice. Try Again.");    //invalid input msg
                for(int j = 1; j<=2; j++)    //time delay loop
                    for(long i = -999999999;i<999999999;i++); 
                menu(id, name);   //invokes menu() of ATM class
            }
        }
    }
    public static String checkUser(String id, String pin)throws IOException    //function to validate name and pin
    {
        try (BufferedReader br = new BufferedReader(new FileReader(accountFile))) 
        {
            String line;
            while((line = br.readLine()) != null)    //read each line in the text file till the end of file
            {
                String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                String ID = parts[0];   //corresponds to id of the user
                String NAME = parts[1];   //corresponds to name of the user
                String PIN = parts[2];   //corresponds to pin of the user
                if(ID.equalsIgnoreCase(id) && PIN.equalsIgnoreCase(pin))   //if name and pin is valid
                    return NAME;   //return the name of the user
            }
        }
        return "NULL";   //returns null as the name of the user ; reaching here means its not validated
    }
}
