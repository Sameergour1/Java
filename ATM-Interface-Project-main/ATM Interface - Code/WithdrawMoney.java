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
class WithdrawMoney
{
    public static Scanner sc = new Scanner(System.in);
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static String accountFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/AccountDir.txt";
    public static String transactionsFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/Transactions.txt";
    @SuppressWarnings("empty-statement")
    public static void withdraw(String id, String name)throws IOException
    {
        double withdraw_amt = 0.0;
        boolean backToMenu = false;  //flag variable; status for going back to menu page
        int trials = 3;   //counter variable; no. of trials for entry of data
        while(trials >= 1)
        {    
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println("\n__________________________________ WITHDRAW MONEY ___________________________________");
            System.out.println(">>>> USER ID : "+ id + String.format("%60s", "USER NAME : "+name) + " <<<<");
            System.out.println("\nEnter the amount to be withdrawn (Limit : Rs. 75,000) : ");
            try   //get input of the choice from among the two
            {
                withdraw_amt = Double.parseDouble(input.readLine());
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
            if(withdraw_amt > 75000)   //withdraw money not within given limit of entry
            {
                System.out.println("Withdraw Amount (Rs. "+withdraw_amt+") is not within the limit.\nYou have "+(trials-1)+" more tries.");
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
                System.out.println("\nNOTE: Money Withdrawal Denied -> Invalid Withdraw Amount Entries.");   //invalid input msg
                for(int j = 1; j<=4; j++)   //time delay loop
                    for(long i = -999999999;i<999999999;i++);
            }
            else  //input meets the conditions
            {
                String content;
                boolean flag;
                try (BufferedReader br = new BufferedReader(new FileReader(accountFile))) 
                {
                    content = ""; //stores the updated content of the file
                    String line;
                    flag = false; //flag variable; status for withdrawal approval
                    while((line = br.readLine()) != null)   //reads each line of text file till the end of file
                    {
                        String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                        String ID = parts[0];   //corresponds to id of the user
                        String NAME = parts[1];   //corresponds to name of the user
                        String PIN = parts[2];   //corresponds to pin of the user
                        double BALANCE = Double.parseDouble(parts[3]);   //corresponds to current balance of the user
                        if(ID.equals(id))   //user is found
                        {
                            if(withdraw_amt <= BALANCE) //withdrawal amount within balance limit
                            {
                                BALANCE = BALANCE - withdraw_amt;    //withdraw money and update the balance
                                System.out.println("\nNOTE:\nMoney Withdrawal Successfull.");   //print msg
                                flag = true;    //update flag status
                            }
                            else
                                System.out.println("\nNOTE:\nBalance Amount not Sufficient for Withdrawal (Withdrawal Amount > Current Balance)."); //print msg
                            System.out.println("Current Balance Amount : Rs. "+BALANCE);    //print current balance
                        }
                        content += ID + "|"+ NAME + "|"+ PIN + "|" + String.valueOf(BALANCE) + System.lineSeparator();
                    }
                } 
                if(flag)   //flag is true
                {
                    try (FileWriter fw_acc = new FileWriter(accountFile)) 
                    {
                        fw_acc.write(content);   //write the updated content
                    } 
                    try (FileWriter fw_transact = new FileWriter(transactionsFile, true)) 
                    {
                        String datetime = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a").format(LocalDateTime.now());   //gets the current date and time
                        fw_transact.write(id+"|"+datetime+"|"+String.valueOf(withdraw_amt)+"|Withdrawal\n");   //write the transaction
                    } 
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