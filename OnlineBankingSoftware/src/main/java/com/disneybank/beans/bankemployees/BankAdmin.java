package com.disneybank.beans.bankemployees;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.disneybank.beans.customers.User;
import com.disneybank.beans.customers.Account;

public class BankAdmin extends Employee {

    public int adminID;
    private String BAAC;

    public BankAdmin (){
        // BankAdmin Constructor
    }

    public BankAdmin (int adminID, String firstName, String lastName, String SSN, String BAAC){
        // BankAdmin Constructor w/ parameters

        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.BAAC = BAAC;
    }

    // GETTERS & SETTERS -----------------------------

    public String getBAAC() {
        return BAAC;
    }

    public boolean setBAAC(String BAAC) throws NullPointerException {
        // the generated EAC should match the format:
        // first name initial, last name initial, last 4 SSN
        BAAC.toUpperCase();
        boolean condition = BAAC.matches("^[A-Z]{2}[0-9]{4}");

        if (condition) {
            this.BAAC = BAAC;
            return true;
        }
        else {
            return false;
        }
    }

    public int getAdminID() {
        return adminID;
    }

    public boolean setAdminID(int adminID) {
        if (adminID > 0) {
            this.adminID = adminID;
            return true;
        }
        else {
            return false;
        }

    }

    // TO STRING -----------------------------

    @Override
    public String toString() {
        return "BankAdmin{" +
                "BAAC='" + BAAC + '\'' +
                ", employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", SSN='" + SSN + '\'' +
                '}';
    }

    // EDIT CUSTOMER INFORMATION / BANK ACCOUNTS -----------------------------

    public boolean getSetCustomerInformation(User updateUser, int option, String value) {
        // method to access setCustomerInformation

        if (updateUser instanceof User && option > 0 && option < 4 && value.length() > 0) {
            // setCustomerInformation(updateUser, option, value);

            if (setCustomerInformation(updateUser, option, value) == true) {
                return true;
            }
        }

//        if (setCustomerInformation(updateUser, option, value) == true) {
//            return true;
//        }
        return false;
    }

    private boolean setCustomerInformation(User updateUser,  int option, String value) {
        // method for editing customer's personal information
        // first name, last name, password

        boolean success = false;
        switch (option) {
            case 1:{
                if (updateUser.getFirstname().equals(value)) {
                    System.out.println("Sorry, this is the same name.");
                    success = false;
                    //break;
                    return false;
                }
                else {
                    updateUser.setFirstname(value);
                    System.out.println("First name updated.");
                    success = true;
                    //break;
                    return true;
                }
            }
            case 2:{
                if (updateUser.getLastname().equals(value)) {
                    System.out.println("Sorry, this is the same name.");
                    success = false;
                    //break;
                    return false;
                }
                else {
                    updateUser.setLastname(value);
                    System.out.println("Last name updated.");
                    success = true;
                    //break;
                    return true;
                }
            }
            case 3:{
                if (updateUser.getPassword().equals(value)) {
                    System.out.println("Sorry, this is the same password.");
                    success = false;
                    //break;
                    return false;
                }
                else {
                    updateUser.setPassword(value);
                    System.out.println("Password updated.");
                    success = true;
                    //break;
                    return true;
                }
            }
            default:{
                System.out.println("That was not a valid option. Please try again.");
                success = false;
                //break;
                return false;
            }
        }

       //

    }

//    // CANCEL BANK ACCOUNT -----------------------------
//
//    public static boolean getCancelBankAcc(HashMap<String,Account> AccountMap, String accNum) throws NullPointerException {
//        // method to access cancelBankAcc
//
//        if (AccountMap instanceof HashMap && accNum != null) {
//            cancelBankAcc(AccountMap, accNum);
//        }
//
//        if (getCancelBankAcc(AccountMap, accNum) == true) {
//            return true;
//        }
//        else {
//            return false;
//        }
//
//    }
//
//    private static boolean cancelBankAcc(HashMap<String, Account> AccountMap, String accNum){
//        // retrieve bank account
//        Account acc = getRetrieveAccountInformation(AccountMap, accNum);
//
//        // check if account's balance is 0 before cancelling
//        if (acc.getBalance() > 0) {
//            // you can't cancel until balance is 0
//
//            // force withdrawal everything left in balance
//            acc.withdraw(acc.getBalance());
//
//        }
//
//        // delete account from Map
//        AccountMap.remove(accNum);
//        return true;
//
//    }
//
//    // HANDLE BANK ACCOUNT TRANSACTIONS -----------------------------
//    // apply methods from account
//
//    public static boolean getWithdrawBA(HashMap<String,Account> AccountMap, String accNum, float amount) {
//
//        if (withdrawBA(AccountMap, accNum,amount) == true) {
//            return true;
//        }
//        else {
//            return false;
//        }
//    }
//
//    private static boolean withdrawBA(HashMap<String, Account> AccountMap, String accNum, float amount){
//        // method to withdraw from bank account
//        Account acc = getRetrieveAccountInformation(AccountMap, accNum);
//
//        double afterWithdrawal = (acc.getBalance()) - amount;
//        if (afterWithdrawal > 0) {
//            acc.withdraw(amount);
//            System.out.println("You have successfully withdrawn $" + amount + ".");
//            return true;
//        }
//        else {
//            System.out.println("Sorry, your withdrawal amount is invalid. Please try again.");
//            return false;
//        }
//    }
//
//    public static boolean getDepositBA (HashMap<String,Account> AccountMap, String bankID, float amount) {
//
//        if (AccountMap instanceof HashMap && bankID != null && amount > 0 ) {
//            if (depositBA(AccountMap, bankID, amount) == true) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean depositBA(HashMap<String,Account> AccountMap, String bankID, float amount){
//        // method to deposit to bank account
//        Account acc = getRetrieveAccountInformation(AccountMap, bankID);
//
//        boolean b = acc.deposit(amount);
//        if(!b) {
//            System.out.println("You have tried to deposit an invalid amount. Deposit not transacted.");
//            return false;
//        }else {
//            System.out.println("You have successfully deposited $" + amount + ".");
//            return true;
//        }
//    }
//
//    public boolean getTransferBA(HashMap<String,Account> AccountMap, String bankIDA, String bankIDB, float amount) {
//
//        if (AccountMap instanceof  HashMap && bankIDA != null && bankIDB != null && amount > 0) {
//
//            if (transferBA(AccountMap, bankIDA, bankIDA, amount) == true) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean transferBA(HashMap<String,Account> AccountMap, String bankIDA, String bankIDB, float amount){
//        // method to transfer between bank accounts
//
//        // accA is the bank account you're taking money from
//        // accB is the bank account you're giving money to
//        // if you successfully withdraw that amount from accA
//        if (withdrawBA(AccountMap, bankIDA,amount)){
//
//            // deposit the money into accB
//            depositBA(AccountMap, bankIDB, amount);
//            System.out.println("You have successfully transferred $" + amount + " from account #" + bankIDA + " to account #" + bankIDB);
//            return true;
//        }
//        return false;
//
//    }
}
