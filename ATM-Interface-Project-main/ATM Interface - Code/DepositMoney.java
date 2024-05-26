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
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;  
class DepositMoney
{
    public static Scanner sc = new Scanner(System.in);
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static String accountFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/AccountDir.txt";
    public static String transactionsFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/Transactions.txt";
    @SuppressWarnings("empty-statement")
    public void deposit(String id, String name)throws IOException
    {
        double deposit_amt = 0.0;
        boolean backToMenu = false;  //flag variable; status for going back to menu page
        int trials = 3;   //counter variable; no. of trials for entry of data
        while(trials >= 1)
        {    
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println("\n__________________________________ DEPOSIT MONEY ____________________________________");
            System.out.println(">>>> USER ID : "+ id + String.format("%60s", "USER NAME : "+name) + " <<<<");
            System.out.println("\nEnter the amount to be deposited (Limit : Rs. 1,50,000) : ");
            try   //get input of the choice from among the two
            {
                deposit_amt = Double.parseDouble(input.readLine());
            }
            catch(NumberFormatException e)   //exception if entered a non-numerical value thus handled
            {
                System.out.println("Entered non-numerical value.\nYou have "+(trials-1)+" more tries.");
                trials--;
                if(trials >= 1)
                {
                    System.out.println("\nPress 1 to Retry\nPress 2 to go Back to Menu");   //choice of retry or back to menu page
                    int pageTransfer = sc.nextInt();
                    while(pageTransfer!=1 && pageTransfer!=2)
                    pageTransfer = sc.nextInt();
                    if(pageTransfer==1)
                    {
                        for(long i = -999999999;i<999999999;i++);
                        continue;
                    }
                    else
                    {
                        backToMenu = true;
                        break;
                    }
                }
                continue;
            }
            if(deposit_amt > 150000)   //deposit money not within given limit of entry
            {
                System.out.println("Deposit Amount (Rs. "+deposit_amt+") is not within the limit.\nYou have "+(trials-1)+" more tries.");
                trials--;
                if(trials >= 1)
                {
                    System.out.println("\nPress 1 to Retry\nPress 2 to go Back to Menu");    //choice of retry or back to menu page
                    int pageTransfer = sc.nextInt();
                    while(pageTransfer!=1 && pageTransfer!=2)
                    pageTransfer = sc.nextInt();
                    if(pageTransfer==1)
                    {
                        for(long i = -999999999;i<999999999;i++);
                        continue;
                    }
                    else
                    {
                        backToMenu = true;
                        break;
                    }
                }
                for(long i = -999999999;i<999999999;i++);
            }
            else
                break;
            
        }
        if(!backToMenu)     //backToMenu is false
        {
            if(trials == 0)   //retried all times but failed to meet the conditons
            {
                System.out.println("\nNOTE : Deposit Money Denied -> Invalid Deposit Amount Entries.");   //invalid input msg
                for(int j = 1; j<=4; j++)   //time delay loop
                    for(long i = -999999999;i<999999999;i++);
            }
            else  //input meets the conditions
            {
                String content;
                try (BufferedReader br = new BufferedReader(new FileReader(accountFile))) 
                {
                    content = ""; //stores the updated content of the file
                    String line;
                    while((line = br.readLine()) != null)   //reads each line of text file till the end of file
                    {
                        String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                        String ID = parts[0];   //corresponds to id of the user
                        String NAME = parts[1];   //corresponds to name of the user
                        String PIN = parts[2];   //corresponds to pin of the user
                        double BALANCE = Double.parseDouble(parts[3]);   //corresponds to current balance of the user
                        if(ID.equals(id))   //user is found
                        {
                            BALANCE = BALANCE + deposit_amt;    //deposit money and update the balance
                            System.out.println("\nNOTE:\nMoney Deposit Successfull.\nCurrent Balance Amount : Rs. "+BALANCE);   //print msg
                        }
                        content += ID + "|"+ NAME + "|"+ PIN + "|" + String.valueOf(BALANCE) + System.lineSeparator();
                    }
                } 
                try (FileWriter fw_acc = new FileWriter(accountFile)) 
                {
                    fw_acc.write(content);   //write the updated content
                }
                try (FileWriter fw_transact = new FileWriter(transactionsFile, true)) 
                {
                    String datetime = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a").format(LocalDateTime.now());   //gets the current date and time
                    fw_transact.write(id+"|"+datetime+"|"+String.valueOf(deposit_amt)+"|Deposit\n");   //write the transaction
                }
                System.out.println("\nPress 1 to return to Menu page");
                int transfer = sc.nextInt();
                while(transfer!=1)
                transfer = sc.nextInt();
            }
        }
        System.out.println("\nMenu Page --- Loading........");
        for(int j = 1; j<=2; j++)
            for(long i = -999999999;i<999999999;i++);
    }
}
