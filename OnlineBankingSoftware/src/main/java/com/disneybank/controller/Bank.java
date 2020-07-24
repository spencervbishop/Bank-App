//package com.disneybank.controller;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.Scanner;
//
//import static com.disneybank.beans.bankemployees.EmployeeDataStorage.pendingApproval;
//import static java.lang.Double.parseDouble;
//import static java.lang.Integer.parseInt;
//
//import com.disneybank.beans.customers.*;
//import com.disneybank.beans.bankemployees.*;
//
//public class Bank {
//
//    public static User LogIn(){
//        Scanner s = new Scanner(System.in);
//        System.out.println("====================================");
//        System.out.println("Welcome to the Bank of Disney!");
//        System.out.println("====================================");
//        boolean userworking = true;
//        while(userworking){
//            System.out.println("Please select from the following options: \n" +
//                    "1) Log In \n" +
//                    "2) Register for an Account \n" +
//                    "3) Sign in as an Employee \n" +
//                    "4) Exit");
//
//
//            String choice = s.next();
//            try {
//                int x = parseInt(choice);
//                switch (x) {
//                    case 1: {
//                        System.out.println("Please enter your username: ");
//                        String uname = s.next().toUpperCase();
//                        if (DataStorage.getUserSet().contains(uname)) {
//                            User user = DataStorage.UserMap.get(uname);
//                            System.out.println("Please enter your password: ");
//                            String pass = s.next();
//                            if (pass.equals(user.getPassword())) {
//                                //openAccount(user); //return user instead?
//                                //userworking = false;
//                                return user;
//                            } else {
//                                System.out.println("Wrong Password");
//                                //userworking = true;
//                            }
//                        } else {
//                            System.out.println("Please enter your password: ");
//                            String not = s.next();
//                            System.out.println("Wrong Username");
//                            //userworking = true;
//                        }
//
//                        break;
//
//                    }
//                    case 2: {
//                        User person = new User();
//                        boolean temp = true;
//                        while (temp) {
//                            System.out.println("Please enter your first name: ");
//                            String name1 = s.next();
//                            if (!person.setFirstname(name1)) {
//                                System.out.println("Please enter a valid name.");
//                            } else {
//                                person.setFirstname(name1);
//                                temp = false;
//                            }
//                        }
//                        temp = true;
//                        while (temp) {
//                            System.out.println("Please enter your last name: ");
//                            String name2 = s.next();
//                            if (!person.setLastname(name2)) {
//                                System.out.println("Please enter a valid last name.");
//                            } else {
//                                person.setLastname(name2);
//                                temp = false;
//                            }
//                        }
//                        temp = true;
//                        while (temp) {
//                            System.out.println("Please enter your SSN (do not use dashes): ");
//                            String ssn = s.next();
//                            if (!person.setSsn(ssn)) {
//                                System.out.println("Please enter a valid SSN.");
//                            } else {
//                                person.setSsn(ssn);
//                                temp = false;
//                            }
//                        }
//                        temp = true;
//                        while (temp) {
//                            System.out.println("Please create a username between 9 and 11 characters: ");
//                            String username = s.next();
//                            if (!person.setUsername(username, DataStorage.getUserSet())) {
//                                System.out.println("Either the username is invalid or taken. Check the format, and try again.");
//                            } else {
//                                person.setUsername(username, DataStorage.getUserSet());
//                                temp = false;
//                            }
//                        }
//                        temp = true;
//                        while (temp) {
//                            System.out.println("Please create a password (between 9 and 14 characters): ");
//                            String pword = s.next();
//                            if (!person.setPassword(pword)) {
//                                System.out.println("Invalid password. Try again.");
//                            } else {
//                                person.setPassword(pword);
//                                temp = false;
//                            }
//                        }
//                        temp = true;
//                        while (temp) {
//                            System.out.println("Would you like to apply for an account? \n" +
//                                    "1) Yes \n" +
//                                    "2) No \n");
//                            String apply = s.next();
//                            if (apply.equals("1")) {
//                                person.setStatus("Pending");
//                                System.out.println("You have applied! You must wait for an administrator to approve your account.");
//                                temp = false;
//                            } else if (apply.equals("2")) {
//                                temp = false;
//                            } else {
//                                System.out.println("Invalid response");
//                            }
//                        }
//                        DataStorage.UserMap.put(person.getUsername(), person);
//                        pendingApproval.add(person);
//                        System.out.println("Congratulations " + person.getFirstname() + "! You have applied for an account.");
//                        userworking = false;
//                        return person;
//                    }
//                    case 3: {
//                        while(true) {
//                            System.out.println("Please enter your EmployeeID:");
//                            String id = s.next();
//                            if (EmployeeDataStorage.BAMap.containsKey(id)) {
//                                User admin = EmployeeDataStorage.BAMap.get(id);
//                                return admin;
//
//                            } else if (EmployeeDataStorage.EMap.containsKey(id)) {
//                                User emp = EmployeeDataStorage.EMap.get(id);
//                                return emp;
//
//                            } else {
//                                System.out.println("ID not valid. Try again.");
//                            }
//                        }
//
//                    }
//                    case 4:{
//                        return null;
//                    }
//                    default: {
//                        System.out.println("You must choose 1 or 2.");
//                        //userworking = true;
//                    }
//                }
//            }catch(Exception e){
//                System.out.println("Please enter a valid response.");
//            }
//        }
//        return null;
//    }
//
//    public static void navigateCustomerPage(User u){
//        Scanner s = new Scanner(System.in);
//        System.out.println("Welcome " + u.getFirstname() + "!");
//
//        boolean using = true;
//        while(using){
//            System.out.println("What would you like to do? \n" +
//                    "1) Manage Accounts \n" +
//                    "2) Apply for an account \n" +
//                    "3) Log out \n");
//            String input = s.next();
//            try{
//                int x = parseInt(input);
//                switch(x){
//                    case 1:{ArrayList<Account> accounts = DataStorage.generateUserAccounts(u);
//                        System.out.println("ACCOUNTS \n" +
//                                "-------------------------------------- \n" +
//                                accounts +"\n" +
//                                "-------------------------------------- \n");
//                        if(accounts.size()==0){
//                            System.out.println("There are no accounts to manage, and your application status is:" + u.getStatus());
//                            break;
//                        }
//                        boolean b = true;
//                        while(b) {
//                            System.out.println("Enter the account number you wish to manage:");
//                            String a = s.next();
//                            if (a == null || !accounts.contains(DataStorage.AccountMap.get(a))){
//                                System.out.println("Account does not exist. Enter another number.");
//                            }else{
//                                Account acct = DataStorage.AccountMap.get(a);
//                                boolean b2 = true;
//                                while(b2) {
//                                    System.out.println(acct.toString());
//                                    System.out.println("Choose an action: \n" +
//                                            "1) Deposit \n" +
//                                            "2) Withdraw \n" +
//                                            "3) Go Back \n");
//                                    String d = s.next();
//                                    try{
//                                        int z = parseInt(d);
//                                        switch(z){
//                                            case 1:{
//                                                System.out.println("Enter positive amount to be deposited: ");
//                                                try{
//                                                    double money = parseDouble(s.next());
//                                                    if(money<=0){
//                                                        System.out.println("You must enter a positive amount.");
//                                                        break;
//                                                    }
//                                                    boolean result = acct.deposit(money);
//                                                    if(result ==true){
//                                                        b2=false;
//                                                    }
//                                                }catch(Exception h){
//                                                    System.out.println("Not a valid amount.");
//                                                }
//                                                break;
//                                            }case 2:{
//                                                System.out.println("Enter positive amount to be withdrawn: ");
//                                                try{
//                                                    double money = parseDouble(s.next());
//                                                    if(money<=0){
//                                                        System.out.println("You must enter a positive amount.");
//                                                        break;
//                                                    }else if(money>acct.getBalance()){
//                                                        System.out.println("You cannot overdraw.");
//                                                        break;
//                                                    }
//                                                    boolean result = acct.withdraw(money);
//                                                    if(result ==true){
//                                                        b2=false;
//                                                    }
//                                                }catch(Exception h){
//                                                    System.out.println("Not a valid amount.");
//                                                }
//                                                break;
//                                            }case 3:{
//                                                b2=false;
//                                                break;
//                                            }default :{
//                                                System.out.println("Enter either 1,2, or 3.");
//                                            }
//                                        }
//
//                                    }catch(Exception g){
//                                        System.out.println("Not a valid input.");
//
//                                    }
//
//
//                                }
//                                b=false;
//                            }
//                        }
//
//                        break;
//
//                    }
//                    case 2:{
//                        System.out.println("Applied. Awaiting Approval.");
////                      {String status = u.getStatus();
////                    if(status.equals("Pending")){
////                        System.out.println("Your current application is pending.");
////                        break;}
////                        else if(status.equals(""))
//
//                        break;
//                    }case 3:{
//                        System.out.println("logging out...");
//                        //s.close();
//                        using = false;
//                        break;
//
//                    }
//                    default :{
//                        System.out.println("Please select either 1, 2, or 3.");
//                    }
//                }
//            }catch(Exception f){
//                System.out.println("Not a valid input.");
//            }
//        }
//    }
//
//
//
//    public static void navigateEmployeePage(Employee e){
//        Scanner s = new Scanner(System.in);
//        System.out.println("Welcome " + e.getFirstName() + "!");
//
//        boolean employeeworking = true;
//        while(employeeworking){
//            System.out.println("What would you like to do? \n" +
//                    "1) View Pending Applications \n" +
//                    "2) View Customer Accounts \n" +
//                    "3) Log out \n");
//            String input = s.next();
//            try{
//                int x = parseInt(input);
//                switch(x){
//                    case 1:{
//                        System.out.println("The current \"Pending Approval\" list contains " + pendingApproval.size() + " applications.\n" +
//                                "Would you like to begin reviewing? \n" +
//                                "1) Yes \n" +
//                                "2) No \n");
//                        String input2 = s.next();
//                        if(input2.equals("1")){
//                            Iterator<User> itr = pendingApproval.iterator();
//                            while (itr.hasNext()) {
//                                User u = itr.next();
//                                System.out.print(u + "\n" + "1) Approve \n" + "2) Reject \n");
//                                String temp = s.next();
//                                boolean b = true;
//                                while(b) {
//                                    if (temp.equals("1")) {
//                                        Employee.getHandlePending(true, u);
//                                        System.out.println(u.getFirstname() + " has been approved.");
//                                        b = false;
//                                    } else if (temp.equals("2")) {
//                                        Employee.getHandlePending(false, u);
//                                        System.out.println(u.getFirstname() + " has been rejected.");
//                                        b = false;
//                                    } else {
//                                        System.out.println("Invalid input");
//                                    }
//                                }
//
//                            }
//                        }else if(input2.equals("2")){
//                            //employeeworking = true;
//                        }else {
//                            System.out.println("Invalid input");
//                            //employeeworking = true;
//                        } break;
//
//                    }
//                    case 2:{
//                        System.out.println("ACCOUNTS \n" +
//                                        "--------------------------------------------------------------- \n" +
//                                        DataStorage.AccountMap.toString());
//                        System.out.println("---------------------------------------------------------------");
//                        if(DataStorage.AccountMap.size()==0){
//                            System.out.println("There are no accounts to view");
//                            break;
//                        }
//                        boolean b = true;
//                        while(b) {
//                            System.out.println("Enter the account number you wish to view:");
//                            String a = s.next();
//                            if (a == null || !DataStorage.AccountMap.containsKey(a)){
//                                System.out.println("Account does not exist. Enter another number.");
//                            }else{
//                                Account acct = Employee.getRetrieveAccountInformation(DataStorage.AccountMap, a);
//                                System.out.println(acct);
//                                b=false;
//                            }
//                        }
//
////                        System.out.println("1) Deposit \n" +
////                                "2) Withdraw \n" +
////                                "3) Close");
//                        break;
//
//                    }
//                    case 3:{
//                        System.out.println("logging out...");
//                        //s.close();
//                        employeeworking = false;
//                        break;
//
//                    }
//                    default :{
//                        System.out.println("Please select either 1, 2, or 3.");
//                        //employeeworking = true;
//                    }
//                }
//            }catch(Exception f){
//                System.out.println("Not a valid input.");
//                //employeeworking = true;
//            }
//        }
//    }
//
//    public static void navigateBankAdminPage(BankAdmin e){
//        Scanner s = new Scanner(System.in);
//        System.out.println("Welcome " + e.getFirstName() + "!");
//
//        boolean employeeworking = true;
//        while(employeeworking){
//            System.out.println("What would you like to do? \n" +
//                    "1) View Pending Applications \n" +
//                    "2) Manage Customer Accounts \n" +
//                    "3) Log out \n");
//            String input = s.next();
//            try{
//                int x = parseInt(input);
//                switch(x){
//                    case 1:{
//                        System.out.println("The current \"Pending Approval\" list contains " + pendingApproval.size() + " applications.\n" +
//                                "Would you like to begin reviewing? \n" +
//                                "1) Yes \n" +
//                                "2) No \n");
//                        String input2 = s.next();
//                        if(input2.equals("1")){
//                            Iterator<User> itr = pendingApproval.iterator();
//                            while (itr.hasNext()) {
//                                User u = itr.next();
//                                System.out.print(u + "\n" + "1) Approve \n" + "2) Reject \n");
//                                String temp = s.next();
//                                boolean b = true;
//                                while(b) {
//                                    if (temp.equals("1")) {
//                                        Employee.getHandlePending(true, u);
//                                        System.out.println(u.getFirstname() + " has been approved.");
//                                        b = false;
//                                    } else if (temp.equals("2")) {
//                                        Employee.getHandlePending(false, u);
//                                        System.out.println(u.getFirstname() + " has been rejected.");
//                                        b = false;
//                                    } else {
//                                        System.out.println("Invalid input");
//                                    }
//                                }
//
//                            }
//                        }else if(input2.equals("2")){
//                            //employeeworking = true;
//                        }else {
//                            System.out.println("Invalid input");
//                            //employeeworking = true;
//                        } break;
//
//                    }
//                    case 2:{
//                        System.out.println("ACCOUNTS \n" +
//                                "--------------------------------------------------------------- \n" +
//                                DataStorage.AccountMap.toString());
//                        System.out.println("---------------------------------------------------------------");
//                        if(DataStorage.AccountMap.size()==0){
//                            System.out.println("There are no accounts to manage");
//                            break;
//                        }
//                        boolean b = true;
//                        while(b) {
//                            System.out.println("Enter the account number you wish to manage:");
//                            String a = s.next();
//                            if (a == null || !DataStorage.AccountMap.containsKey(a)){
//                                System.out.println("Account does not exist. Enter another number.");
//                            }else{
//                                Account acct = Employee.getRetrieveAccountInformation(DataStorage.AccountMap, a);
//
//                                boolean b2 = true;
//                                while(b2) {
//                                    System.out.println(acct.toString());
//                                    System.out.println("Choose an action: \n" +
//                                            "1) Deposit \n" +
//                                            "2) Withdraw \n" +
//                                            "3) Transfer \n" +
//                                            "4) Close Account\n" +
//                                            "5) Go Back \n");
//                                    String d = s.next();
//                                    try{
//                                        int z = parseInt(d);
//                                        switch(z){
//                                            case 1:{
//                                                System.out.println("Enter positive amount to be deposited: ");
//                                                try{
//                                                    double money = parseDouble(s.next());
//                                                    if(money<=0){
//                                                        System.out.println("You must enter a positive amount.");
//                                                        break;
//                                                    }
//                                                    boolean result = BankAdmin.getDepositBA(DataStorage.AccountMap, acct.getAccountNumber(), money);
//                                                        if(result ==true){
//                                                            b2=false;
//                                                        }
//                                                }catch(Exception h){
//                                                    System.out.println("Not a valid amount.");
//                                                }
//                                                break;
//                                            }case 2:{
//                                                System.out.println("Enter positive amount to be withdrawn: ");
//                                                try{
//                                                    double money = parseDouble(s.next());
//                                                    if(money<=0){
//                                                        System.out.println("You must enter a positive amount.");
//                                                        break;
//                                                    }else if(money>acct.getBalance()){
//                                                        System.out.println("You cannot overdraw.");
//                                                        break;
//                                                    }
//                                                    boolean result = BankAdmin.getWithdrawBA(DataStorage.AccountMap, acct.getAccountNumber(), money);
//                                                    if(result ==true){
//                                                        b2=false;
//                                                    }
//                                                }catch(Exception h){
//                                                    System.out.println("Not a valid amount.");
//                                                }
//                                                break;
//                                            }
//
//
//
//
//
//                                            case 3: {
//                                                System.out.println("Enter the account number you wish to transfer to:");
//                                                String a2 = s.next();
//                                                if (a2 == null || !DataStorage.AccountMap.containsKey(a2)) {
//                                                    System.out.println("Account does not exist. Enter another number.");
//                                                } else {
//                                                    Account acct2 = Employee.getRetrieveAccountInformation(DataStorage.AccountMap, a2);
//
//                                                    System.out.println("Enter positive amount to be transferred.");
//                                                    try {
//                                                        double money = parseDouble(s.next());
//                                                        if (money <= 0) {
//                                                           System.out.println("You must enter a positive amount.");
//                                                            break;
//                                                        } else if (money > acct.getBalance()) {
//                                                        System.out.println("You cannot overdraw.");
//                                                        break;
//                                                    }
//                                                    boolean result = e.getTransferBA(DataStorage.AccountMap, acct.getAccountNumber(), acct2.getAccountNumber(), money);
//                                                    if (result == true) {
//                                                        b2 = false;
//                                                    }
//                                                    } catch (Exception h) {
//                                                        System.out.println("Not a valid amount.");
//                                                    }
//                                                }
//                                                break;
//                                            }
//
//
//
//
//                                                case 4:{
//                                                boolean result = BankAdmin.getCancelBankAcc(DataStorage.AccountMap, acct.getAccountNumber());
//                                                if(result == true) {
//                                                    System.out.println("The final amount has been withdrawn and the account canceled.");
//                                                    b2=false;
//                                                }else {break;
//                                                }
//                                                break;
//                                            }case 5: {
//                                                b2=false;
//                                                break;
//                                            }default :{
//                                                System.out.println("Enter either 1,2,3, or 4.");
//                                            }
//                                        }
//
//                                    }catch(Exception g){
//                                        System.out.println("Not a valid input.");
//
//                                    }
//
//
//                                }
//                                b=false;
//                            }
//                        }
//
//                        break;
//
//                    }
//                    case 3:{
//                        System.out.println("logging out...");
//                        //s.close();
//                        employeeworking = false;
//                        break;
//
//                    }
//                    default :{
//                        System.out.println("Please select either 1, 2, or 3.");
//                        //employeeworking = true;
//                    }
//                }
//            }catch(Exception f){
//                System.out.println("Not a valid input.");
//                //employeeworking = true;
//            }
//        }
//    }
//
//}