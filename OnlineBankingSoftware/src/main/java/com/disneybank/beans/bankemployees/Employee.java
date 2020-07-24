package com.disneybank.beans.bankemployees;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

// import User
import com.disneybank.beans.customers.User;

// import Account
import com.disneybank.beans.customers.Account;
import com.disneybank.beans.customers.DataStorage;

public class Employee extends User {

    public int employeeID;
    protected String firstName;
    protected String lastName;
//    protected String driversID;
    protected String SSN;
//    protected String username;
//    protected String password;
    //protected ArrayList<User> pendingApproval;
    private String EAC;

    protected boolean loggedIn = false; // default logged out
    protected boolean accActivated = false; // default not activated

    public Employee(){
        // Employee Constructor
    }

    public Employee(int employeeID, String firstName, String lastName, String SSN, String EAC){
        // Employee Constructor w/ Parameters

        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.EAC = EAC;
    }

    // GETTERS & SETTERS -----------------------------
    public String getFirstName(){
        return firstName.toUpperCase();
    }

    public boolean setFirstName(String firstName) throws NullPointerException {
        firstName = firstName.trim().toUpperCase();
        // for the bank to set the Employee's first name, it has to be an abc character
        boolean temp = firstName.matches("[a-zA-Z]");

        if(temp){
            // save all first names as uppercase for consistency
            this.firstName = firstName.trim().toUpperCase();
            return true;
        }
        else {
            return false;
        }
    }

    public String getLastName() {
        return lastName.toUpperCase();
    }

    public boolean setLastName(String lastName) throws NullPointerException {
        // for the bank to set the Employee's last name, it has to be an abc character
        boolean temp = lastName.matches("[a-zA-Z]+");

        if(temp){
            // save all last names as uppercase for consistency
            this.lastName = lastName.toUpperCase().trim();
            return true;
        }
        else {
            return false;
        }
    }

//    public String getDriversID() {
//        return driversID;
//    }
//
//    public boolean setDriversID(String driversID) throws NullPointerException {
//
//        driversID.trim();
//
//        if (driversID == "" || driversID == null || driversID.length() < 1) {
//            return false;
//        }
//        else {
//            this.driversID = driversID.trim();
//            return true;
//        }
//    }

    public String getSSN() {
        return SSN;
    }

    public boolean setSSN(String SSN) throws NullPointerException {
        SSN = SSN.trim();
        boolean condition = SSN.matches("^[0-9]{9}$");

        if (condition) {
            // check that the SSN is a digit and has 9 digits
            this.SSN = SSN;
            return true;
        }

        else {
            return false;
        }

    }

//    public String getUsername() {
//        return username;
//    }
//
//    public boolean setUsername(String username) throws NullPointerException {
//
//        username = username.trim().toUpperCase();
//
//        boolean condition = username.matches("^[A-Z]{2}[0-9]{3}");
//        if (condition){
//            this.username = username;
//            return true;
//        }
//        else {
//            return false;
//        }

//    }

//    public String getPassword() {
//        return password;
//    }
//
//    public boolean setPassword(String password) throws NullPointerException {
//
//        password.trim();
//
//        if (password.matches("^[a-zA-Z0-9]{8,12}")){
//            this.password = password;
//            return true;
//        }
//        else {
//            return false;
//        }
//    }

//    public ArrayList<User> getPendingApproval() {
//        return pendingApproval;
//    }
//
//    public boolean setPendingApproval(ArrayList<User> pendingApproval) {
//
//        for (int i=0; i < pendingApproval.size();i++){
//            this.pendingApproval.set(i, pendingApproval.get(i));
//        }
//
//        return true;
//
//    }

    public String getEAC() {
        return EAC;
    }

    public boolean setEAC(String EAC) throws NullPointerException {
        // the generated EAC should match the format:
        // first name initial, last name initial, last 4 SSN
        EAC = EAC.toUpperCase().trim();
        boolean condition = EAC.matches("^[A-Z]{2}[0-9]{4}");

        if (condition) {
            this.EAC = EAC;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean setLoggedIn(boolean loggedIn) {

        if (this.loggedIn != loggedIn) {
            this.loggedIn = loggedIn;
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isAccActivated() {
        return accActivated;
    }

    public boolean setAccActivated(boolean accActivated) {

        if (this.accActivated != accActivated && accActivated != false) {
            this.accActivated = accActivated;
            return true;
        }
        else {
            return false;
        }
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public boolean setEmployeeID(int employeeID) {
        if (employeeID > 0) {
            this.employeeID = employeeID;
            return true;
        }
        else {
            return false;
        }

    }

    // TO STRING -----------------------------

    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", SSN='" + SSN + '\'' +
                ", EAC='" + EAC + '\'' +
                '}';
    }


//    // ACCOUNT REGISTRATION -----------------------------
//
//    protected void accountCreation(Employee emp) {
//        // method that writes an Employee's file to the employees.txt file
//
//        ObjectOutputStream out = null;
//
//        try {
//            out = new ObjectOutputStream(new FileOutputStream("resources/users/employees.txt"));
//            out.writeObject(emp);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected boolean accountRegistration (String key, String firstName, String lastName) throws IOException {
//        // method that matches registration key to employee on file
//        // if matched, the employee receives their account information (username and password)
//
//        ObjectInputStream in = null;
//
//        // check if the key is on file with the matching first name and last name
//        try {
//            in = new ObjectInputStream(new FileInputStream("resources/users/employees.txt"));
//            Employee emp = (Employee)in.readObject();
//            System.out.println(emp);
//
//            // read the employees' information, looking for the matching EAC, firstName, lastName
//            // read object from file
//            String eEAC = emp.getEAC();
//            String efName = emp.getFirstName();
//            String elName = emp.getLastName();
//
//            if (eEAC == key && efName == firstName && elName ==lastName) {
//
//                // return true if the matching info is found
//                return true;
//            }
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            in.close();
//        }
//
//        // else, return false
//        return false;
//    }
//
//    public int randomGen(int min, int max){
//        // method to generate random numbers
//
//        int range = (max - min) + 1;
//        int random = (int)(Math.random() * range) + min;
//
//        return random;
//    }
//
//    protected String generateAccount (boolean activated, Employee employee) {
//        // method called after accountRegistration
//        // if activated (true returned from accountRegistration)
//
//
//        if (activated) {
//            // get the first and last initials
//
//            // USERNAME LOGIC
//            String username = employee.getFirstName().substring(0,1) + employee.getLastName().substring(0,1) + employee.getSSN().substring(7);;
//
//            // generate account
//            employee.setUsername(username);
//
//            // PASSWORD LOGIC
//
//            int range = randomGen(0,9);
//            int random = (int) (Math.random() * range);
//            String randomS = String.valueOf(random);
//
//            String abcS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//            char[] abcC = abcS.toCharArray();
//            int range2 = randomGen(0,52);
//            int random2 = (int)(Math.random() * range2);
//
//            String password = String.valueOf(abcC[random2]) + String.valueOf(abcC[random2]) + String.valueOf(abcC[random2]) + String.valueOf(abcC[random2]) + randomS + randomS + randomS + randomS;
//
//            employee.setPassword(password);
//
//            String accountInfo = "Username: " + username + "\nPassword: " + password;
//
//            // rewrite to the employees file
//            // should have the username & password included now too
//            accountCreation(employee);
//            employee.setAccActivated(true);
//
//            // login automatically after activation too
//            employee.setLoggedIn(true);
//
//            return (accountInfo);
//
//        }
//
//        // else return a String that reads
//        return ("Sorry, your account hasn't been activated.");
//    }
//
//    // LOGIN/LOGOUT -----------------------------
//
//    public void login(String username, String password) throws IOException {
//        // check if the username and password exists on file, if it does then login
//
//        ObjectInputStream in = null;
//
//        // check if the key is on file with the matching first name and last name
//        try {
//            in = new ObjectInputStream(new FileInputStream("resources/users/employees.txt"));
//            Employee emp = (Employee)in.readObject();
//            System.out.println(emp);
//
//            // read the employees' information, looking for the matching EAC, firstName, lastName
//            // read object from file
//            String eUsername = emp.getUsername();
//            String ePassword = emp.getPassword();
//
//            if (username == eUsername && password == ePassword) {
//
//                // return true if the matching info is found
//                this.setLoggedIn(true);
//            }
//            else {
//                this.setLoggedIn(false);
//                System.out.println("Sorry, username/password incorrect.");
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            in.close();
//        }
//
//    }
//
//    public boolean logout() {
//
//        if (this.isLoggedIn() == true) {
//            // can't log out if you're already logged out
//            this.setLoggedIn(false);
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//    // VIEW CUSTOMER INFORMATION -----------------------------
//
//    public boolean getRetrieveCustomerInformation(HashMap<String,User> userMap, String inputUsername) throws NullPointerException {
//        if (retrieveCustomerInformation(userMap, inputUsername) instanceof User && inputUsername != null) {
//            return true;
//        }
//        return false;
//    }
//
//    private User retrieveCustomerInformation(HashMap<String, User> userMap, String inputUsername) throws NullPointerException {
//        // method to retrieve/view customer information
//
//        // look through the HashMap for matching username
//        // username that you're using to search the hashmap with
//        String username = inputUsername.toUpperCase();
//
//        if (userMap.containsKey(username)) {
//
//            // get the value for username
//            User person = userMap.get(username);
//
//            // System.out.println(person);
//            return person;
//        }
//
//        return null;
//    }
//
//    public static Account getRetrieveAccountInformation(HashMap<String,Account> accountMap, String accountNum) throws NullPointerException {
//
//        if (retrieveAccountInformation(accountMap, accountNum) instanceof Account && accountNum != null) {
//            return (retrieveAccountInformation(accountMap, accountNum));
//        }
//
//
//        return null;
//
//
//    }
//
//    private static Account retrieveAccountInformation(HashMap<String, Account> accountMap, String accountNum) throws NullPointerException {
//        // method to retrieve/view bank information
//
//        // look through the HashMap for matching account number
//        if (accountMap.containsKey(accountNum)) {
//            // get the value for accountNum
//            Account account = accountMap.get(accountNum);
//
//            return account;
//        }
//
//        return null;
//    }
//
//    // HANDLE PENDING ACCOUNTS -----------------------------
//
//    public static boolean getHandlePending(boolean handleAppStatus, User applicant) {
//
//        if (handlePending(handleAppStatus,applicant) == true) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//    private static boolean handlePending(boolean handleAppStatus, User applicant){//, HashMap<String, Account> AccountMap){
//        // method to handle pending accounts
//
//
//        if (handleAppStatus == true) {
//            // set the user applicant's status to approved
//            applicant.setStatus("Approved! Apply again.");
//            System.out.println("Approved! Apply again.");
//
//            // generate a new account
//            Account newAcc = new Account(applicant, DataStorage.getAccountSet());
//            // add the new account to the account HashMap
//            DataStorage.AccountMap.put(newAcc.getAccountNumber(),newAcc);
//
//            return true;
//        }
//        else {
//            applicant.setStatus("Rejected. Apply again.");
//            System.out.println("Rejected. Apply again.");
//
//            return false;
//        }
//    }

}
