package com.disneybank.controller;
import com.disneybank.beans.bankemployees.BankAdmin;
import com.disneybank.beans.bankemployees.Employee;
import com.disneybank.beans.customers.Account;
import com.disneybank.beans.customers.User;
import com.disneybank.services.AccountService;
import com.disneybank.services.BankAdminService;
import com.disneybank.services.EmployeeService;
import com.disneybank.services.UserService;
import org.apache.log4j.Logger;
import com.disneybank.controller.LoggerMain;
import sun.reflect.annotation.ExceptionProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;


public class BankOfDisney {
    public static void main(String[] args) {
        UserService userService = new UserService();
        AccountService accountService = new AccountService();

        // BEGIN BANK WELCOME SCREEN
        welcome();
    }

    // MAIN MENU OPTION METHODS --------------------------------------------------------------------------
    // ALL VIEW - login menu method
    public static void welcome() {
        Scanner s = new Scanner(System.in);
        boolean a = true;
        while (a) {
            // login message
            System.out.println("\n ===Welcome to the Bank of Disney - where the money and magic happen!===");
            System.out.println("Please select an option below: ");
            System.out.println("[1] Register\n[2] Login\n[3] Employee Login\n[4] Bank Admin Login\n[5] Exit Bank");
            // scanner input - validate for the options
            String b = s.next();
            int x = getInt(b, 5);
            boolean validated = false;
            switch (x) {
                case 1: {
                    // Register for an account
                    System.out.println("\n === ACCOUNT REGISTRATION ===");
                    System.out.println("Please fill out all of the following fields.\n\n");
                    // User: username, password, first name, last name, SSN
                    User customer = makeUser(s);
                    String message = registerCustomerAccount(customer);
                    System.out.println(message);
                    break;
                }
                case 2: {
                    // Login to account
                    System.out.println("Username: ");
                    String username = s.next().toUpperCase();
                    //Check that the username exists
                    if(checkUsername(username)) {
                        System.out.println("Username does not exist.");
                        break;
                    }
                    System.out.println("Password: ");
                    String password = s.next();
                    if(checkPassword(username, password)) {
                        loginCustomerAccount(username, s);
                    }else {
                        System.out.println("Password does not match.");
                    }
                    break;
                }
                case 3: {
                    // Employee login

                    boolean EACValid = false;

                    while (!EACValid) {
                        System.out.println("Please enter your EmployeeID: ");
                        String id = s.next();

                        // JDBC check employee id if it exists in the database
                        if (checkEAC(id)){
                            // if the EAC exists in the database, login
                            loginEmployeeAccount(id,s);
                            EACValid = true;
                        }
                        else {
                            System.out.println("EAC invalid.");
                        }
                    }

                    break;
                }
                case 4: {
                    // BA login

                    boolean BAACValid = false;

                    while (!BAACValid) {
                        System.out.println("Please enter your BankAdminID: ");
                        String id = s.next();

                        // JDBC check bank admin id if it exists in the database
                        if (checkBAAC(id)){
                            // if the BAAC exists in the database, login
                            loginBAAccount(id,s);
                            BAACValid = true;
                        }
                        else {
                            System.out.println("BAAC invalid.");
                        }
                    }

                    break;
                }
                case 5: {
                    // Exit Bank
                    a = false;
                    System.out.println("Thank you for business. Have a magical day!");
                    break;
                }
                default: {
                    System.out.println("Please choose a number between 1 and 5.");
                    break;
                }
            }
        }
    }
    // ACCOUNT REGISTRATION / LOGIN METHODS --------------------------------------------------------------------------
    //Ensures customer enters a number for each choice
    public static int getInt(String str, int x){
        try{
            int m = parseInt(str);
            if(m>=1 && m<=x) {
                return m;
            }else {
                return 0;
            }
        }catch(Exception e){
            return 0;
        }
    }
    //Ensures Username is not taken:
    public static boolean checkUsername(String username) {
        UserService userService = new UserService();
        List<String> allUsernames = userService.getAllUsernames();
        boolean check = allUsernames.contains(username.toUpperCase());
        return !check;
    }
    //Check that password matches
    public static boolean checkPassword(String username, String password) {
        UserService userService = new UserService();
        User person = userService.getUserByUsername(username.toUpperCase());
        boolean matches = password.equals(person.getPassword());
        return matches;
    }
    //Ensures SSN is not taken:
    public static boolean checkSsn(String ssn) {
        UserService userService = new UserService();
        List<String> allSsns = userService.getAllSsns();
        boolean check = allSsns.contains(ssn);
        return !check;
    }
    // CUSTOMER VIEW - register for an account method
    public static String registerCustomerAccount(User person){//String username, String password, String fname, String lname, String SSN){
        //create new user
        //User person = new User(fname, lname, SSN, username, password);
        //create a new row in the users column
        UserService userService = new UserService();
        userService.saveUser(person);
        //add name to pending application list (optional)
        String message = "Thank you for registering!";
        return message;
    }

    // CUSTOMER VIEW - register for a joint account
    public static void requestJointAccount(Scanner s, User person){
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        boolean b = true;
        while(b) {
            System.out.println("Please either enter a code or apply for a new joint account: \n" +
                    "[1]Enter Code \n[2]Apply for new Joint Account \n[3]Go back ");
            String str4 = s.next();
            int x = getInt(str4, 3);
            switch (x) {
                case 1: {
                    String ssn2 = person.getSsn();

                    System.out.println("Enter other user's SSN: ");
                    String ssn1 = s.next();
                    if(!ssn1.matches("^[0-9]{9}$")) {
                        System.out.println("Not a valid SSN.");
                        break;
                    }
                    System.out.println("Enter your unique code:");
                    String code = s.next();
                    if(!code.matches("^[0-9]{10}$")) {
                        System.out.println("Code does not exist. Application rejected.");
                        break;
                    }
                    int user1=0;
                    int user2=0;
                    try{
                    user1 = userService.getUserIdBySsn(ssn1);
                    user2 = userService.getUserIdBySsn(ssn2);

                    }catch(Exception e) {
                        System.out.println("We do not recognize that SSN. Please make an account.");
                        break;
                    }
                    if (user1 == 0 || user2 == 0) {
                        System.out.println("One or both SSNs do not exist. Application rejected.");
                        break;
                    }

                    if(!userService.matchJointAccountCode(ssn1, ssn2, code)) {
                        System.out.println("Code or SSNs not found. Application rejected.");
                        break;
                    }else {
                        System.out.println("You have successfully applied for a Joint Account!" +
                                "You must wait for an administrator to approve your application.\n");
                        Account acct = new Account("joint", 0.0);

                        accountService.makeNewJointAccount(acct, user1, user2);
                    }

                    break;
                }
                case 2: {
                    String ssn1 = person.getSsn();
                    System.out.println("Enter other user's SSN: ");
                    String ssn2 = s.next();
                    if(!ssn2.matches("^[0-9]{9}$")) {
                        System.out.println("Not a valid SSN.");
                        break;
                    }

                    //GENERATE A PRIVATE CODE
                        String generatedCode = Long.toString((long) Math.floor(Math.random() * 9000000000L) + 1000000000L);
                    String message = userService.createJointAccountCode(ssn1, ssn2, generatedCode);
                    System.out.println(message);
                    System.out.println(generatedCode);
                    System.out.println("Other account applicant must sign in to verify the code.");
                    break;

                }
                case 3: {
                    b = false;
                    break;
                }
                default: {
                    System.out.println("Please enter a number between 1 and " + 3 + ".");
                    break;
                }
            }
        }

    }

    // CUSTOMER VIEW - login to account method
    public static void loginCustomerAccount(String uname, Scanner s){
        UserService userService = new UserService();
        AccountService accountService = new AccountService();
        int user_id = userService.getUserByUsername(uname.toUpperCase()).getId();
        boolean b = true;
        while(b) {      //Ensures user comes back to the main menu each time until logging out
            User person = userService.getUser(user_id);
            System.out.println("\n===Welcome, "+ person.getFirstname() +"!=== \n" +  "Please choose from the following options: ");
            System.out.println("[1] View User Information\n[2] View Account Information \n[3] Apply for an Account \n[4] Manage an Account \n[5] Log Out");
            String str = s.next();
            int x = getInt(str, 5);
            switch (x) {
                case 1: {
                    System.out.println(person);
                    boolean c = true;
                    while(c) {
                        System.out.println("[1] Change User Info \n[2] Go back");
                        String str2 = s.next();
                        int y = getInt(str2, 2);
                        switch (y) {
                            case 1: {
                                User updatedPerson = editCustomerInformation(person.getUsername(), s);
                                System.out.println("Info updated:");
                                System.out.println(updatedPerson);
                                break;
                            }
                            case 2: {
                                c = false;
                                break;
                            }
                            default: {
                                System.out.println("Please enter 1 or 2.");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    List<Account> accounts = viewUserAccounts(user_id);
                    if(!accounts.isEmpty()){
                        for(Account acct: accounts){
                            System.out.println(acct);
                        }
                    }else{
                        System.out.println("You currently have no accounts.");
                    }
                    break;
                }
                case 3: {
                    boolean d = true;
                    while(d) {
                        System.out.println("What kind of account would you like to apply for?");
                        System.out.println("[1] Personal Account \n[2] Joint Account \n[3] Go back ");
                        String str3 = s.next();
                        int z = getInt(str3, 3);
                        switch (z) {
                            case 1: {
                                boolean e = true;
                                while(e) {
                                    System.out.println("What kind of account would you like to open? \n" +
                                            "[1]Checking \n[2]Savings \n[3]Go back ");
                                    String str4 = s.next();
                                    int zz = getInt(str4, 3);
                                    switch (zz) {
                                        case 1: {
                                            System.out.println("How much would you like to make your initial deposit?");
                                            String amt = s.next();
                                            double init;
                                            try{
                                                init = Double.parseDouble(amt);
                                            }catch(Exception ex){
                                                init = 0.0;
                                                System.out.println("Amount not processed. Initial amount set to $0.00.");
                                            }
                                            Account acct = new Account("checking", init);
                                            accountService.makeNewAccountUser(acct, user_id);
                                            System.out.println("You have applied for a checking account! You must wait for an Administrator's approval.\n");
                                            e = false;
                                            d = false;
                                            break;
                                        }
                                        case 2: {
                                            System.out.println("How much would you like to make your initial deposit?");
                                            String amt = s.next();
                                            double init;
                                            try{
                                                init = (double) Double.parseDouble(amt);
                                            }catch(Exception ex){
                                                init = (double) 0.0;
                                                System.out.println("Amount not processed. Initial amount set to $0.00.");
                                            }
                                            Account acct = new Account("savings", init);
                                            accountService.makeNewAccountUser(acct, user_id);
                                            System.out.println("You have applied for a savings account! You must wait for an Administrator's approval.\n");
                                            e = false;
                                            d = false;
                                            break;
                                        }
                                        case 3: {
                                            e = false;
                                            break;
                                        }
                                        default: {
                                            System.out.println("Please enter a number between 1 and 3.");
                                            break;
                                        }
                                    }
                                }
                                break;

                            }
                            case 2: {
                                requestJointAccount(s, person);
                                break;
                            }
                            case 3: {
                                d = false;
                                break;
                            }
                            default: {
                                System.out.println("Please enter either 1, 2, or 3.");
                                break;
                            }
                        }
                    }
                    break;
                }
                case 4:{
                    List<Account> accounts = viewUserAccounts(user_id);
                    if(!accounts.isEmpty()){
                        for(Account acct: accounts){
                            System.out.println(acct);
                        }
                    }else{
                        System.out.println("You currently have no accounts.");
                    }
                    System.out.println("Enter the account number that you would like to manage" +
                            "or press 'x' to go back.");
                    String acctnum = s.next();
                    if(acctnum.equals("x")){
                        break;
                    }else if(accountService.getAccounts().contains(acctnum)){
                        Account usersAccount = accountService.getAccountByNumber(acctnum);
                        boolean cc = true;
                        while(cc){
                        System.out.println("What would you like to do? \n" +
                                "[1] Make deposit \n[2] Withdraw Funds \n[3] Go Back");
                        String input = s.next();
                        x = getInt(input, 3);

                            switch(x){
                                case 1:{
                                    System.out.println("How much would you like to deposit?");
                                    String amt = s.next();
                                    double deposit;
                                    try{
                                        deposit = (double) Double.parseDouble(amt);
                                        handleDeposit(deposit, usersAccount.getAccountId());
                                        System.out.println("Deposited!");
                                    }catch(Exception ex){
                                        deposit = (double) 0.0;
                                        System.out.println("Amount not processed. Deposited: $0.00");
                                    }
                                    break;
                                }
                                case 2:{
                                    System.out.println("How much would you like to withdraw?");
                                    String amt = s.next();
                                    double withdraw;
                                    try{
                                        withdraw = (double) Double.parseDouble(amt);
                                        if(withdraw>usersAccount.getBalance()){
                                            System.out.println("Cannot overdraw!");
                                            break;
                                        }else {
                                            handleWithdrawal(withdraw, accountService.getAccountByNumber(acctnum).getAccountId());
                                            System.out.println("Withdrawn!");
                                        }
                                    }catch(Exception ex){
                                        withdraw = (double) 0.0;
                                        System.out.println("Amount not processed. Withdrawn: $0.00");
                                    }
                                    break;
                                }
                                case 3:{
                                    cc=false;
                                    break;
                                }
                                default:{
                                    System.out.println("Please enter a number between 1 and 3.");
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                case 5: {
                    b = false;
                    break;
                }
                default: {
                    System.out.println("Please choose a number between 1 and 5.");
                    break;
                }
            }
        }
    }

    // EMPLOYEE VIEW - employee login to account method
    //Ensures EAC exists:
    public static boolean checkEAC(String EAC) {

        EmployeeService employeeService = new EmployeeService();
        List<String> allEAC = employeeService.getAllEAC();
        boolean check = allEAC.contains(EAC.toUpperCase());
        return check;

    }

    // get specific employee
    public static Employee getEmployee(String EAC) {
        EmployeeService employeeService = new EmployeeService();
        Employee e = employeeService.getByEAC(EAC.toUpperCase());

        return e;
    }

    public static void loginEmployeeAccount(String id, Scanner s){
        //JDBC obtain Emp info

        Employee e = getEmployee(id);

        boolean b = true;
        while(b) {
            System.out.println("\n===Welcome, Employee " + e.getFirstName() + " " + e.getLastName() + "!===\n" +
                    "Please choose from the following options:");
            System.out.println("[1] View Accounts \n[2] View Pending Applications \n[3] Log Out\n");
            String str = s.next();
            int x = getInt(str, 3);
            switch(x) {
                case 1:{
                    // VIEW USER ACCOUNTS AND ASSOCIATED BANK ACCOUNTS
                    // flynn
                    System.out.println("===VIEW USER ACCOUNTS AND ASSOCIATED BANK ACCOUNTS===");

                    if (getAllUsers().isEmpty() || getAllUsers() == null || getAllUsers().size() <= 0) {
                        System.out.println("No Users to view.");
                    }
                    else if (getAllUsers() != null || getAllUsers().size() > 0) {
                        List<String> usernames = getAllUsers();

                        for (int i=0; i < usernames.size(); i++) {
                            String u = usernames.get(i);

                            System.out.println("\n");
                            User user = getUserByUsername(u);
                            System.out.println("Name:" + user.getFirstname() + " " + user.getLastname());
                            System.out.println("Username: " + user.getUsername());
                            System.out.println("Password: " + user.getPassword());

                            List<Account> userBankAccounts = viewUserAccounts(user.getId());

                            if (userBankAccounts.isEmpty() || userBankAccounts == null || userBankAccounts.size() <= 0) {
                                System.out.println("No Accounts to view.");
                            }
                            else if (userBankAccounts != null || userBankAccounts.size() > 0) {

                                for (Account a : userBankAccounts) {

                                    System.out.println("\n\nAccount Number: " + a.getAccountNumber());
                                    System.out.println("Account Type: " + a.getAccountType().toUpperCase());
                                    System.out.printf("Account Balance: $%.2f", a.getBalance());
                                    System.out.println();
                                }
                            }

                            System.out.println("\n----------------------------------------------");
                        }
                    }
                    System.out.println("\n==============================\n\nReturning to main menu ...");

                    break;
                }
                case 2:{
                    // view all pending accounts to approve/deny
                    // show account number, account type, and starting balance in the view
                    List<String> pendingAccounts = viewPendingAccounts();

                    managePendingAccounts(s);

                    break;
                }
                case 3:{
                    // log out of employee account
                    System.out.println("Logging out of EAC " + e.getEAC() + "...");
                    b = false;
                    break;
                }
                default:{
                    System.out.println("Please enter a number between 1 and 3.");
                    break;
                }
            }
        }
    }

    // BA VIEW - employee login to account method
    // Ensures BAAC exists
    public static boolean checkBAAC(String BAAC) {

        BankAdminService bankAdminService = new BankAdminService();
        List<String> allBAAC = bankAdminService.getAllBAAC();
        boolean check = allBAAC.contains(BAAC.toUpperCase());
        return check;

    }

    // get specific bank admin
    public static BankAdmin getBankAdmin(String BAAC) {
        BankAdminService bankAdminService = new BankAdminService();
        BankAdmin ba = bankAdminService.getByBAAC(BAAC.toUpperCase());

        return ba;
    }

    public static void loginBAAccount(String id, Scanner s){
        //JDBC obtain Emp info
        BankAdmin ba = getBankAdmin(id);

        boolean b = true;
        while(b) {
            System.out.println("\n===Welcome, Bank Administrator " + ba.getFirstName() + " " + ba.getLastName() + "!===\n" +
                    "Please choose from the following options:");
            System.out.println("[1] Manage Accounts \n[2] View Pending Applications \n[3] Log Out \n");
            String str = s.next();
            int x = getInt(str, 3);
            switch(x) {
                case 1:{
                    // view and manage user accounts and bank accounts
                    // flynn

                    // view all user accounts first
                    System.out.println("===ALL USER ACCOUNTS===");

                    // VIEW USER ACCOUNTS AND ASSOCIATED BANK ACCOUNTS
                    System.out.println("===VIEW USER ACCOUNTS AND ASSOCIATED BANK ACCOUNTS===");

                    if (getAllUsers().isEmpty() || getAllUsers() == null || getAllUsers().size() <= 0) {
                        System.out.println("No Users to view.");
                    }
                    else if (getAllUsers() != null || getAllUsers().size() > 0) {
                        List<String> usernames = getAllUsers();

                        for (int i=0; i < usernames.size(); i++) {
                            String u = usernames.get(i);

                            System.out.println("\n\n");
                            User user = getUserByUsername(u);
                            System.out.println("Name:" + user.getFirstname() + " " + user.getLastname());
                            System.out.println("Username: " + user.getUsername());
                            System.out.println("Password: " + user.getPassword());

                            List<Account> userBankAccounts = viewUserAccounts(user.getId());

                            if (userBankAccounts.isEmpty() || userBankAccounts == null || userBankAccounts.size() <= 0) {
                                System.out.println("No Accounts to view.");
                            }
                            else if (userBankAccounts != null || userBankAccounts.size() > 0) {

                                for (Account a : userBankAccounts) {

                                    System.out.println("\n\nAccount Number: " + a.getAccountNumber());
                                    System.out.println("Account Type: " + a.getAccountType().toUpperCase());
                                    System.out.printf("Account Balance: $%.2f", a.getBalance());
                                }
                            }

                            System.out.println("\n-----------------------------------------------");
                        }

                        boolean handlingOpt = false;

                        while (!handlingOpt) {
                        System.out.println("Please enter the username of the account you would like to manage or 'x' to return to the menu: ");
                        String username = s.next();
                        

                            if (username == "x" || username.equals("x") || username.equals("X") || username == "X") {
                                handlingOpt = true;
                            }

                            // check if the user exists
                            else if (!checkUsername(username)) {

                                UserService userService = new UserService();
                                int uid = userService.getUserByUsername(username).getId();

                                System.out.println("\n===CUSTOMER INFORMATION===");
                                viewUserInfo(uid);

                                boolean optionValid = false;

                                // option to edit customer information or manage account information or cancel
                                while (!optionValid) {
                                    System.out.println("Would you like to edit customer information or proceed to accounts?");
                                    System.out.println("[1] Edit Customer Information\n[2] Proceed to View User's Accounts\n[3] Proceed to View All Accounts\n[4] Cancel");
                                    int opt = s.nextInt();

                                    if (opt == 1) {
                                        editCustomerInformation(username,s);
                                        optionValid = true;
                                    }
                                    else if(opt == 2) {
                                        System.out.println("===VIEW USER'S ACCOUNTS===\n");

                                        List<Account> userBankAccounts = viewUserAccounts(uid);

                                        if (userBankAccounts.isEmpty() || userBankAccounts == null || userBankAccounts.size() <= 0) {
                                            System.out.println("No Accounts to view.");
                                        } else if (userBankAccounts != null || userBankAccounts.size() > 0) {

                                            for (Account a : userBankAccounts) {

                                                System.out.println("\n\nAccount Number: " + a.getAccountNumber());
                                                System.out.println("Account Type: " + a.getAccountType().toUpperCase());
                                                System.out.printf("Account Balance: $%.2f", a.getBalance());
                                            }
                                        }

                                        System.out.println("\n----------------------------------------------");

                                        boolean accountManaging = true;
                                        boolean accountMatch = false;

                                        while (accountManaging) {
                                            while (!accountMatch) {
                                                System.out.println("Please enter an account number to manage the account: ");
                                                String acct = s.next();

                                                // check if the account number exists
                                                if (checkAccount(acct)) {

                                                    System.out.println("\n===ACCOUNT===");
                                                    viewAccountInfo(acct);

                                                    int accountID = getAccountByNumber(acct).getAccountId();

                                                    // option to deposit, withdraw, transfer money, or cancel account
                                                    accountTransactionOptions(accountID, s);
                                                    optionValid = true;
                                                    accountMatch = true;
                                                }
                                                else {
                                                    System.out.println("This account is unavailable.");
                                                }
                                            }
                                            accountManaging = false;

                                        }
                                    }
                                    else if (opt == 3){
                                        System.out.println("===VIEW ALL ACCOUNTS===\n");

                                        if (viewAllAccounts().isEmpty() || viewAllAccounts() == null || viewAllAccounts().size() <= 0) {
                                            System.out.println("\n\nNo Accounts to view.");
                                        }
                                        else if (viewAllAccounts() != null || viewAllAccounts().size() > 0) {
                                            List<String> accounts = viewAllAccounts();

                                            for (String a : accounts) {
                                                System.out.println("\n\nAccount Number: " + getAccountByNumber(a).getAccountNumber());
                                                System.out.println("Account Type: " + getAccountByNumber(a).getAccountType().toUpperCase());
                                                System.out.printf("Account Balance: $%.2f", getAccountByNumber(a).getBalance());
                                            }

                                            boolean accountManaging = true;
                                            boolean accountMatch = false;
                                            while (accountManaging) {
                                                while (!accountMatch) {
                                                    System.out.println("Please enter an account number to manage the account: ");
                                                    String acct = s.next();

                                                    // check if the account number exists
                                                    if (checkAccount(acct)) {

                                                        System.out.println("\n===ACCOUNT===");
                                                        viewAccountInfo(acct);

                                                        int accountID = getAccountByNumber(acct).getAccountId();

                                                        // option to deposit, withdraw, transfer money, or cancel account
                                                        accountTransactionOptions(accountID, s);
                                                        optionValid = true;
                                                        accountMatch = true;
                                                    }
                                                    else {
                                                        System.out.println("This account is unavailable.");
                                                    }
                                                }
                                                accountManaging = false;
                                            }
                                        }
                                    }

                                    else if (opt == 4) {
                                        // return to previous menu
                                        optionValid = true;
                                    }

                                    else {
                                        System.out.println("Please try again.");
                                    }

                                }
                                handlingOpt = true;
                            }
                        }

                    }
                    else {
                        System.out.println("This user is unavailable.");
                    }

                    break;
                }
                case 2:{
                    // view all pending accounts to approve/deny
                    // show account number, account type, and starting balance in the view
                    managePendingAccounts(s);

                    break;
                }
                case 3:{
                    // log out of bank admin account

                    System.out.println("Logging out of BAAC " + ba.getBAAC() + "...");
                    b = false;
                    break;
                }
                default:{
                    System.out.println("Please enter a number between 1 and 3.");
                    break;
                }
            }
        }
    }
    // HANDLE PENDING ACCOUNTS METHODS --------------------------------------------------------------------------

    // ensures that the account exists as a pending account
    public static boolean checkPendingAccount(String accNum){
        AccountService accountService = new AccountService();
        List<String> pendingAccounts = accountService.getPendingAccounts();
        boolean check = pendingAccounts.contains(accNum);

        return check;
    }

    // view all pending accounts
    public static List<String> viewPendingAccounts(){
        AccountService accountService = new AccountService();
        List<String> pendingAccounts = accountService.getPendingAccounts();

        return pendingAccounts;
    }

    public static void handlePendingAccount(String accountNum, int accountID, Scanner s){
        // list options to approve, reject, or return to menu for pending account handling

        boolean handlingValid = false;

        while (!handlingValid) {
            System.out.println("Would you like to approve Account " + accountNum + "?" );
            System.out.println("[1] Yes, approve and open account.\n[2] No, close account and remove from pending.\n[3] Return");
            int opt = s.nextInt();

            if (opt == 1) {
                // approve account
                changePendingAccountStatus(accountID, true, s);
                handlingValid = true;
            }
            else if (opt == 2) {
                // reject account
                changePendingAccountStatus(accountID, false, s);
                handlingValid = true;
            }
            else if (opt == 3) {
                // return to menu
                handlingValid = true;
            }
            else {
                System.out.println("Option invalid.");
            }
        }
    }

    public static void changePendingAccountStatus(int accountID, boolean approved, Scanner s) {
        // method to handle the account_approval boolean for the accNum passed through

        AccountService accountService = new AccountService();

        if (approved == true) {
            // if the account was approved, change its status to open

            // changing approval status to true
            accountService.changeAccountApproval(accountID,approved);

            // changing open/close status to true
            accountService.changeAccountStatus(accountID,true);
        }

        else if (approved == false) {
            // if the account was not approved

            // change the approval status to false
            accountService.changeAccountApproval(accountID,false);

            // change open/close status to false
            accountService.changeAccountStatus(accountID,false);
        }

    }

    public static void managePendingAccounts(Scanner s){
        if (viewPendingAccounts().isEmpty() || viewPendingAccounts() == null || viewPendingAccounts().size() <= 0) {
            System.out.println("No Pending Accounts to view.");
        }
        else if (viewPendingAccounts() != null || viewPendingAccounts().size() > 0)  {

            // loop through array list for pending accounts
            // use the account number to retrieve account type and initial balance
            List<String> pendingAccounts = viewPendingAccounts();

            System.out.println("==Pending Accounts==");
            for (int i = 0; i < pendingAccounts.size();i++) {
                String a = pendingAccounts.get(i);

                System.out.println("\n\nAccount Number: " + getAccountByNumber(a).getAccountNumber());
                System.out.println("Account Type: " + getAccountByNumber(a).getAccountType().toUpperCase());
                System.out.printf("Initial Balance: $%.2f", getAccountByNumber(a).getBalance());
            }

            boolean pendingValid = false;

            while (!pendingValid){
                System.out.println("\nPlease enter the account number you would like to handle for approval/rejection or 'x' to cancel: ");
                String opt = s.next();

                if (checkAccount(opt)) {
                    // check if the account exists and if the account is pending approval

                    if (checkPendingAccount(opt)) {
                        int accountID = getAccountByNumber(opt).getAccountId();
                        handlePendingAccount(opt, accountID, s);
                        pendingValid = true;
                    }
                    else {
                        System.out.println("\nAccount is not in pending.");
                    }
                }
                else if (opt.equals("x") || opt == "x" || opt.equals("X") || opt == "X") {
                    pendingValid = true;
                }
                else {
                    System.out.println("\nAccount does not exist.");
                }
            }
        }
    }

    // REVIEW / UPDATE USER INFORMATION METHODS --------------------------------------------------------------------------
    public static User makeUser(Scanner s){
        User person = new User();
        boolean validated = false;
        // User: username, password, first name, last name, SSN
        while (!validated) {
            boolean usernameValid = false;
            String checkedUsername = "";
            while (!usernameValid) {
                System.out.println("Username --- required 8-20 characters.");
                String username = s.next().toUpperCase();
                boolean usernameMatch = username.matches("^[0-9A-Z]{8,20}$");
                if (usernameMatch) {
                    if (checkUsername(username)) {
                        checkedUsername = username;
                        usernameValid = true;
                    }
                    else {
                        System.out.println("Username taken.");
                    }
                }
                else {
                    System.out.println("Username invalid.");
                }
            }
            boolean passwordValid = false;
            String checkedPassword = "";
            while (!passwordValid) {
                System.out.println("Password --- required 8-20 characters with letters + numbers.");
                String password = s.next();
                boolean passwordMatch = password.matches("^[a-zA-Z0-9]{8,20}$");
                if (!passwordMatch) {
                    System.out.println("Password invalid.");
                }
                else {
                    checkedPassword = password;
                    passwordValid = true;
                }
            }
            boolean firstnameValid = false;
            String checkedFName = "";
            while (!firstnameValid) {
                System.out.println("First name.");
                String fname = s.next().toUpperCase();
                if (fname.length() > 0) {
                    checkedFName= fname;
                    firstnameValid = true;
                }
                else {
                    System.out.println("First name invalid.");
                }
            }
            boolean lastnameValid = false;
            String checkedLName = "";
            while (!lastnameValid) {
                System.out.println("Last name.");
                String lname = s.next().toUpperCase();
                if (lname.length() > 0) {
                    checkedLName = lname;
                    lastnameValid = true;
                }
                else {
                    System.out.println("Last name invalid.");
                }
            }
            boolean SSNValid = false;
            String checkedSSN = "";
            while (!SSNValid) {
                System.out.println("SSN --- required without dashes (-) between numbers.");
                String SSN = s.next();
                boolean SSNMatch = SSN.matches("^[0-9]{9}$");
                if (SSNMatch) {
                    if (checkSsn(SSN)) {
                        SSNValid = true;
                        checkedSSN = SSN;
                    }
                    else {
                        System.out.println("SSN taken.");
                    }
                }
                else {
                    System.out.println("SSN invalid.");
                }
            }
            if (usernameValid == true && passwordValid == true && SSNValid == true && checkedFName.length() > 0 && checkedLName.length() > 0) {
                validated = true;
                person.setFirstname(checkedFName);
                person.setLastname(checkedLName);
                person.setSsn(checkedSSN);
                person.setUsername(checkedUsername);
                person.setPassword(checkedPassword);
            }
        }
        return person;
    }

    //ALL VIEW - view specific user information method -----------------------------------------------------

    public static List<String> getAllUsers(){
        // get all users
        UserService userService = new UserService();
        List<String> allUsernames =  userService.getAllUsernames();
        return allUsernames;
    }

    public static User getUserByUsername(String username){
        // get user ID
        UserService userService = new UserService();
        User user = userService.getUserByUsername(username);
        return user;
    }

    public static void viewUserInfo(int user_id){
        //JDBC retrieve user information
        UserService userService = new UserService();
        User user = userService.getUser(user_id);
        System.out.println(user);
//        System.out.println("\n===USER INFORMATION===");
//        System.out.println("Name: " + name + "\n" +
//                "Username: " + username + "\n" +
//                "Password: " + password + "\n" +
//                "SSN: " + "***-**-" + ssn.substring(5) + "\n" +
//                "");
    }

    // ALL VIEW - view specific account information method
    public static void viewAccountInfo(String acctnum){
        AccountService accountService = new AccountService();
        Account acct = accountService.getAccountByNumber(acctnum);
            System.out.println(acct.toString());

    }

    //CUSTOMER VIEW - view list of all OPEN accounts
    public static List<Account> viewUserAccounts(int user_id){
        AccountService accountService = new AccountService();
        List<Account> list = accountService.getAccountsByUser(user_id);
        List<Account> openAccounts = new ArrayList<>();
        //return list;
        for(Account i : list){
            if(i.isOpen()){
                openAccounts.add(i);
            }
        }
        return openAccounts;
    }

    // EMPLOYEE/BA VIEW - view list of all accounts
    public static List<String> viewAllAccounts(){
        AccountService accountService = new AccountService();
        List<String> accounts = accountService.getAccounts();

        return accounts;
    }

    // ALL VIEW - edit customer information
    public static User editCustomerInformation(String username, Scanner s){
        UserService userService = new UserService();
        User person = userService.getUserByUsername(username.toUpperCase());
        int id = person.getId();
        boolean validated = false;
        // User: username, password, first name, last name, SSN
        while (!validated) {
            boolean usernameValid = false;
            String checkedUsername = "";
            while (!usernameValid) {
                System.out.println("Username --- 8-20 characters. To skip, enter 'skip'.");
                String uname = s.next().toUpperCase();
                boolean usernameMatch = uname.matches("^[0-9A-Z]{8,20}$");
                if(!uname.equals("SKIP")) {
                    if (usernameMatch) {
                        if (checkUsername(uname)) {
                            checkedUsername = uname;
                            usernameValid = true;
                        } else {
                            System.out.println("Username taken.");
                        }
                    } else {
                        System.out.println("Username invalid.");
                    }
                }else{usernameValid = true; checkedUsername = username;}
            }
            boolean passwordValid = false;
            String checkedPassword = "";
            while (!passwordValid) {
                System.out.println("Password --- 8-20 characters with letters + numbers. To skip, enter 'skip'.");
                String password = s.next();
                boolean passwordMatch = password.matches("^[a-zA-Z0-9]{8,20}$");
                if(!password.toUpperCase().equals("SKIP")) {
                    if (!passwordMatch) {
                        System.out.println("Password invalid.");
                    } else {
                        checkedPassword = password;
                        passwordValid = true;
                    }
                }else{ passwordValid = true; checkedPassword = person.getPassword();}
            }
            boolean firstnameValid = false;
            String checkedFName = "";
            while (!firstnameValid) {
                System.out.println("First name. To skip, enter 'skip'.");
                String fname = s.next().toUpperCase();
                if(!fname.equals("SKIP")) {
                    if (fname.length() > 0) {
                        checkedFName = fname;
                        firstnameValid = true;
                    } else {
                        System.out.println("First name invalid.");
                    }
                }else{firstnameValid = true; checkedFName= person.getFirstname();}
            }
            boolean lastnameValid = false;
            String checkedLName = "";
            while (!lastnameValid) {
                System.out.println("Last name. To skip, enter 'skip'.");
                String lname = s.next().toUpperCase();
                if(!lname.equals("SKIP")) {
                    if (lname.length() > 0) {
                        checkedLName = lname;
                        lastnameValid = true;
                    } else {
                        System.out.println("Last name invalid.");
                    }
                }else{lastnameValid = true; checkedLName= person.getLastname();}
            }
            if (usernameValid == true && passwordValid == true && checkedFName.length() > 0 && checkedLName.length() > 0) {
                validated = true;
                person.setFirstname(checkedFName);
                person.setLastname(checkedLName);
                person.setUsername(checkedUsername);
                person.setPassword(checkedPassword);
                System.out.println("Info changed!");
            }
        }

        userService.updateUserInfo(person, id);
        return person;
    }

    // ensure the account exists
    public static boolean checkAccount(String accountNum) {
        List<String> accounts = viewAllAccounts();
        boolean check = accounts.contains(accountNum);
        return check;
    }

    public static Account getAccountByNumber(String accountNum) {
        AccountService accountService = new AccountService();
        Account acc = accountService.getAccountByNumber(accountNum);
        return acc;
    }

    // BANK TRANSACTION METHODS --------------------------------------------------------------------------

    // BA VIEW - account transaction options
    public static void accountTransactionOptions(int accountID, Scanner s) {
        final Logger logger = Logger.getLogger(LoggerMain.class);
        LoggerMain obj = new LoggerMain();

        boolean b = true;
        while(b) {
            System.out.println("Please choose from the following options:");
            System.out.println("[1] Deposit\n[2] Withdraw\n[3] Transfer\n[4] Cancel Account\n[5] Back");
            String str = s.next();
            int x = getInt(str, 5);
            switch(x) {
                case 1:{
                    // deposit money
                    boolean depositValid = false;

                    while (!depositValid) {
                        System.out.println("Enter the amount you would like to deposit: ");
                        double amount = s.nextDouble();

                        if (amount > 0) {
                            handleDeposit(amount,accountID);
                            String msg = String.format("$%.2f has been deposited.", amount);
                            obj.log(msg,logger,"info");
                            depositValid = true;
                        }
                        else{
                            String msg = "Amount needs to be greater than 0.";
                            obj.log(msg,logger,"error");
                        }

                    }
                    break;
                }
                case 2:{
                    // withdraw money
                    boolean withdrawalValid = false;

                    while (!withdrawalValid){
                        System.out.println("Enter the amount you would like to withdraw: ");
                        double amount = s.nextDouble();

                        // check the bank account balance to make sure there is no overdrawing
                        // get the account by id/acctnum
                        double balance = getAccountBalance(accountID);

                        if (balance - amount >= 0) {
                            handleWithdrawal(amount, accountID);
                            String msg = String.format("$%.2f has been withdrawn.", amount);
                            obj.log(msg,logger,"info");
                            withdrawalValid = true;
                        }
                        else {
                            String msg="Amount cannot be overdrawn.";
                            obj.log(msg,logger,"error");
                        }
                    }

                    break;
                }
                case 3:{
                    // transfer money
                    boolean transferValid = false;

                    while(!transferValid){
                        System.out.println("Enter the amount you would like to transfer: ");
                        double amount = s.nextDouble();

                        // check the bank account balance to make sure there is no overdrawing
                        // get the account by id/acctnum
                        double balance = getAccountBalance(accountID);

                        if (balance - amount >= 0){

                            System.out.println("Enter the account number you would like to transfer to.");
                            String transferAcc = s.next();

                            if (checkAccount(transferAcc)) {
                                int transferID = getAccountByNumber(transferAcc).getAccountId();
                                handleTransfer(amount, accountID, transferID);

                                String msg = String.format("$%.2f has been transferred.", amount);
                                obj.log(msg,logger,"info");

                                transferValid = true;
                            }
                            else {
                                String msg = "Account number invalid.";
                                obj.log(msg,logger,"error");
                            }
                        }
                        else {
                            String msg = "Amount cannot be overdrawn.";
                            obj.log(msg,logger,"error");
                        }
                    }
                    break;
                }
                case 4: {
                    // cancel account
                    boolean cancelValid = false;

                    while (!cancelValid) {
                        System.out.println("Would you like to cancel this account?");
                        System.out.println("[1] Yes\n[2] Return\n");
                        int opt = s.nextInt();

                        if (opt == 1) {
                            boolean status = false;
                            handleAccountCancellation(accountID, status);
                            cancelValid = true;
                            System.out.println("The account has been cancelled.");
                        }
                        else if (opt ==2) {
                            cancelValid = true;
                        }
                        else {
                            System.out.println("Option invalid.");
                        }
                    }

                    break;
                }
                case 5: {
                    // go back to account menu
                    b = false;
                    break;
                }
                default:{
                    System.out.println("Please enter a number between 1 and 5.");
                    break;
                }
            }
        }
    }

    // BA VIEW - get account balance
    public static double getAccountBalance(int accountID) {
        AccountService accountService = new AccountService();
        double balance = accountService.getById(accountID).getBalance();

        return balance;
    }

    // BA VIEW - handle deposit method
    public static void handleDeposit(double amount, int accountID){
        AccountService accountService = new AccountService();
        accountService.makeDeposit(amount, accountID);
    }

    // BA VIEW - handle withdraw method
    public static void handleWithdrawal(double amount, int accountID){
        AccountService accountService = new AccountService();
        accountService.makeWithdrawal(amount, accountID);
    }

    // BA VIEW - handle transfer method
    public static void handleTransfer(double amount, int accountIDA, int accountIDB){
        AccountService accountService = new AccountService();
        accountService.makeTransfer(amount, accountIDA, accountIDB);
    }

    // BA VIEW = handle account cancellation method
    public static void handleAccountCancellation(int accountID, boolean status){
        AccountService accountService = new AccountService();

        // set account status from OPEN to CLOSED
        accountService.changeAccountStatus(accountID, status);

    }
}