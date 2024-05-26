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
class TransactionHistory
{
    public static Scanner sc = new Scanner(System.in);
    public static String accountFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/AccountDir.txt";
    public static String transactionsFile = "D:/Philemon/CHRISTUNIV/Octanet_ATM/ATM_Interface_Project/src/main/java/com/mycompany/atm_interface_project/Transactions.txt";
    @SuppressWarnings("empty-statement")
    public static void transactHist(String id, String name)throws IOException
    {
        System.out.print('\f');
        System.out.println("*************************************************************************************");
        System.out.println("*****************************  WELCOME TO OCTANET ATM  ******************************");
        System.out.println("*************************************************************************************");
        System.out.println("\n________________ TRANSACTION HISTORY (last 10 or fewer transactions) ________________");
        System.out.println(">>>> USER ID : "+ id + String.format("%60s", "USER NAME : "+name) + " <<<<");
        BufferedReader br = new BufferedReader(new FileReader(transactionsFile));
        int count = 0;   //counter varaible; no. of transactions placed by user
        String line;
        while((line = br.readLine()) != null)   //reads each line of text file till the end of file
        {
            String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
            String ID = parts[0];   //corresponds to id of the user
            if(ID.equals(id))   //user if found
                ++count;   //update the counter
        }
        br.close();
        System.out.println("=====================================================================================");
        System.out.println(String.format("%-25s\t%10s\t%10s\t%-20s", "DATE & TIME", "WITHDRAWAL", "DEPOSIT", "TRANSACTION TYPE"));
        System.out.println("=====================================================================================");
        if(count == 0)  //count is zero -> no transactions place
            System.out.println("No Transactions placed.");   //print msg
        else
        {
            br = new BufferedReader(new FileReader(transactionsFile));
            while((line = br.readLine()) != null)   //reads each line of text file till the end of file
            {
                String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
                String ID = parts[0];   //corresponds to id of the user
                if(ID.equals(id))   //user is found
                {
                    if(count <= 10)    //last 10 or fewer transactions
                    {
                        String DATETIME = parts[1];   //corresponds to date and time of the transaction
                        String AMT = String.format("%.2f", Double.valueOf(parts[2]));   //corresponds to amount of the transaction
                        String TYPE = parts[3];   //corresponds to type of the transaction
                        if(TYPE.equals("Deposit") || TYPE.equals("Transfer Credit"))   //deposit or transfer credit
                            System.out.println(String.format("%-25s\t%10s\t%10s\t%-20s", DATETIME, "", AMT, TYPE));
                        else    //withdrawal or transfer debit
                            System.out.println(String.format("%-25s\t%10s\t%10s\t%-20s", DATETIME, AMT, "", TYPE));
                    }
                    else
                        count--;
                }
            }
            br.close();
        }
        System.out.println("-------------------------------------------------------------------------------------");
        br = new BufferedReader(new FileReader(accountFile));
        while((line = br.readLine()) != null)   //reads each line of text file till the end of file
        {
            String[] parts = line.split("\\|"); // Split the line by the delimiter "|"
            String ID = parts[0];   //corresponds to id of the user
            if(ID.equals(id))   //user is found
            {
                String BALANCE = String.format("%.2f", Double.valueOf(parts[3]));    //corresponds to balance of the user
                System.out.println(String.format("%85s", "Current Balance Amount : Rs. "+BALANCE));    //print the balance
            }
        }
        br.close();
        System.out.println("=====================================================================================");
        System.out.println("\nPress 1 to return to Menu page");
        int transfer = sc.nextInt();
        while(transfer!=1)
        transfer = sc.nextInt();
        System.out.println("\nMenu Page --- Loading........");
        for(int j = 1; j<=2; j++)
            for(long i = -999999999;i<999999999;i++);
    }
}        
