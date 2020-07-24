package com.disneybank.beans.customers;

import com.disneybank.services.AccountService;
import com.disneybank.services.UserService;

import java.io.Serializable;
import java.util.Set;

import static com.sun.jmx.snmp.ThreadContext.contains;

public class Account {
    String acctnumber;
    double balance;
    int user_id;
    //String name;
    String accttype;
    int acct_id;
    boolean isOpen;

    UserService userService = new UserService();
    AccountService accountService = new AccountService();

    public Account(String acctnumber, String accttype, double balance, int acct_id, boolean isOpen){
        this.acctnumber = acctnumber;
        this.accttype = accttype;
        this.balance = balance;
        //this.user_id = user_id;
        //User person = userService.getUser(user_id);
        //name = person.getFirstname() + person.getLastname();
        this.acct_id = acct_id;
        this.isOpen = isOpen;
    }

    public Account(String acctnumber, String accttype, double balance, int acct_id) {
        this.acctnumber = acctnumber;
        this.accttype = accttype;
        this.balance = balance;
        //this.user_id = user_id;
        //User person = userService.getUser(user_id);
        //name = person.getFirstname() + person.getLastname();
        this.acct_id = acct_id;
    }
    public Account(String type, double initbalance){
        acctnumber = accountNumber();
        accttype = type;
        balance = initbalance;
        user_id = user_id; //person.getId();
        //name = person.getFirstname() + " " +person.getLastname();
    }

    public boolean deposit(double input) throws NullPointerException{
        double amount = Math.round(input*100)/100;
        boolean pos = amount>0;
        if(pos){
            balance += amount;
            return true;
        }else{
            return false;
        }
    }

    public boolean withdraw(double input) throws NullPointerException{
        double amount = Math.round(input*100)/100;
        boolean pos = amount>0;
        boolean max = amount<=balance;
        if(pos && max){
            balance = balance-amount;
            return true;
        }else{
            return false;
        }
    }

    public int getUserId(){
        return user_id;
    }

    public double getBalance(){

        return Math.round(100*balance)/100;
    }

    public String getAccountNumber(){

        return acctnumber;
    }

    public String getAccountType() {
        return accttype;
    }

    public int getAccountId(){
        return acct_id;
    }

    public void setAccountId(int id){
        this.acct_id = id;
    }

    public void setAccountType(String accttype) {
        this.accttype = accttype;
    }

    public boolean isOpen(){
        return isOpen;
    }



//    public boolean closeAccount(){            //in Bank Admin class
//        if(balance==0){
//            return true;
//            //delete account from Map?
//        }else{
//            return false;
//        }
//    }

    private String accountNumber() {                //find a way to test this
        boolean available = true;
        String account = null;
        while(available) {
            account = Long.toString((long) Math.floor(Math.random() * 9000000000L) + 1000000000L);
            available = accountService.getAccounts().contains(account);
        }return account;
    }

    @Override
    public String toString() {
        return "===" + accttype.toUpperCase() + " ACCOUNT=== \n" +
                "No.: '" + acctnumber + "\'\n" +
                "Available balance: $" + Math.round(100*balance)/100 + "\n" +
                "===================================\n";
    }
}
