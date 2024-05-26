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
class TransferMoney
{
    public static Scanner sc = new Scanner(System.in);
    public static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    public static String accountFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/AccountDir.txt";
    public static String transactionsFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/Transactions.txt";
    @SuppressWarnings("empty-statement")
    public static void transfer(String id, String name)throws IOException
    {
        String transfer_id = "";
        boolean backToMenu = false;  //flag variable; status for going back to menu page
        int trials = 3;   //counter variable; no. of trials for entry of data
        while(trials >= 1)
        {    
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println("\n__________________________________ TRANSFER MONEY ___________________________________");
            System.out.println(">>>> USER ID : "+ id + String.format("%60s", "USER NAME : "+name) + " <<<<");
            System.out.println("\nEnter USER_ID for amount to be tranferred to : ");
            transfer_id = input.readLine();   //input the transfer user id
            if(!checkUserID(transfer_id))   //transfer user id doesn't exist
            {
                System.out.println("Entered User_ID doesn't exist.\nYou have "+(trials-1)+" more tries.");
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
                for(long i = -999999999;i<999999999;i++);
            }                       
            else
                break;
        }
        if(!backToMenu)   //backToMenu is false
        {
            if(trials > 0)     //input the meets the conditions
                transferPart2(id,name,transfer_id);
            else   //retried all times but failed to meet the conditons
            {
                System.out.println("\nNOTE: Transfer Money Denied -> Invalid User_ID Entries.");    //invalid input msg
                for(int j = 1; j<=4; j++)   //time delay loop
                    for(long i = -999999999;i<999999999;i++);
            }
        }
        System.out.println("\nMenu Page --- Loading........");
        for(int j = 1; j<=2; j++)
            for(long i = -999999999;i<999999999;i++);
    }
    @SuppressWarnings("empty-statement")
    public static void transferPart2(String id, String name, String transfer_id)throws IOException
    {
        double transfer_amt = 0.0; 
        boolean backToMenu = false;  //flag variable; status for going back to menu page
        int trials = 3;   //counter variable; no. of trials for entry of data
        while(trials >= 1)
        {    
            System.out.print('\f');
            System.out.println("*************************************************************************************");
            System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
            System.out.println("*************************************************************************************");
            System.out.println("\n__________________________________ TRANSFER MONEY ___________________________________");
            System.out.println(">>>> USER ID : "+ id + String.format("%60s", "USER NAME : "+name) + " <<<<");
            System.out.println("\n------ Transfer User_ID : "+transfer_id);
            System.out.println("\nEnter the amount to be tranferred (Limit : Rs. 75,000) : ");
            try   //get input of the choice from among the two
            {
                transfer_amt = Double.parseDouble(input.readLine());
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
            if(transfer_amt > 75000)   //transfer money not within given limit of entry
            {
                System.out.println("Transfer Amount (Rs. "+transfer_amt+") is not within the limit.\nYou have "+(trials-1)+" more tries.");
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
                    }
                    else
                    {
                        backToMenu = true;
                        break;
                    }
                }
            }
            else
                break;
        }
        if(!backToMenu)   //backToMenu is false
        {
            if(trials <= 0)   //retried all times but failed to meet the conditons
            {
                System.out.println("\nNOTE: Transfer Money Denied -> Invalid Transfer Amount Entries.");   //invalid input msg
                for(int j = 1; j<=4; j++)    //time delay loop
                    for(long i = -999999999;i<999999999;i++);
            }
            else    //input meets the conditions
            {
                BufferedReader br = new BufferedReader(new FileReader(accountFile));
                String content = "";    //stores the updated content of the file
                String line;
                boolean flag = false;   //flag variable; status for withdrawal approval
                while((line = br.readLine()) != null)   //reads each line of text file till the end of file
                {
                    String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                    String ID = parts[0];   //corresponds to id of the user
                    String NAME = parts[1];   //corresponds to name of the user
                    String PIN = parts[2];   //corresponds to pin of the user
                    double BALANCE = Double.parseDouble(parts[3]);   //corresponds to current balance of the user  
                    if(ID.equals(id))   //user is found
                    {
                        System.out.println("\nNOTE for User_ID ["+id+"]:");
                        if(transfer_amt <= BALANCE) //transfer amount within balance limit
                        {
                            BALANCE = BALANCE - transfer_amt;    //transfer money and update the balance   
                            System.out.println("Money Transfer Debit Successfull.");   //print msg
                            flag = true;   //update flag status
                        }
                        else
                            System.out.println("Balance Amount not Sufficient for Transfer (Transfer Amount > Current Balance).");   //print msg
                        System.out.println("Current Balance Amount : Rs. "+BALANCE);   //print current balance
                    }
                    content += ID + "|"+ NAME + "|"+ PIN + "|" + String.valueOf(BALANCE) + System.lineSeparator();
                }
                br.close();
                if(flag)   //flag is true
                {
                    FileWriter fw_acc = new FileWriter(accountFile);
                    fw_acc.write(content);    //write the updated content
                    fw_acc.close();
                    br = new BufferedReader(new FileReader(accountFile));
                    content = "";
                    while((line = br.readLine()) != null)   //reads each line of text file till the end of file
                    {
                        String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                        String ID = parts[0];   //corresponds to id of the user
                        String NAME = parts[1];   //corresponds to name of the user
                        String PIN = parts[2];   //corresponds to pin of the user
                        double BALANCE = Double.parseDouble(parts[3]);   //corresponds to current balance of the user  
                        if(ID.equals(transfer_id))   //transfer user is found 
                        {
                            BALANCE = BALANCE + transfer_amt;    //transfer money and update the balance
                            System.out.println("\nNOTE for User_ID ["+transfer_id+":]\nMoney Transfer Credit Successfull.");   //print msg
                            //System.out.println("Current Balance Amount : Rs. "+BALANCE);    //print current balance
                        }
                        content += ID + "|"+ NAME + "|"+ PIN + "|" + String.valueOf(BALANCE) + System.lineSeparator();
                    }
                    br.close();
                    fw_acc = new FileWriter(accountFile);
                    fw_acc.write(content);    //write the updated content
                    fw_acc.close();
                    FileWriter fw_transact = new FileWriter(transactionsFile, true);
                    String datetime = DateTimeFormatter.ofPattern("MMM dd, yyyy hh:mm:ss a").format(LocalDateTime.now());   //gets the current date and time
                    fw_transact.write(id+"|"+datetime+"|"+String.valueOf(transfer_amt)+"|Transfer Debit\n");   //write the transaction   
                    fw_transact.write(transfer_id+"|"+datetime+"|"+String.valueOf(transfer_amt)+"|Transfer Credit\n");   //write the transaction
                    fw_transact.close();
                }
                System.out.println("\nPress 1 to return to Menu page");
                int transfer = sc.nextInt();
                while(transfer!=1)
                transfer = sc.nextInt();
            }
        }
    }
    public static boolean checkUserID(String id)throws IOException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(accountFile)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                String accountNumber = parts[0];
                if(accountNumber.equalsIgnoreCase(id))
                    return true;
            }
        }
        return false;
    }
}