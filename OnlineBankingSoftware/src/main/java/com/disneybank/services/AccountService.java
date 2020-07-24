package com.disneybank.services;

import com.disneybank.beans.customers.Account;
import com.disneybank.dao.AccountDao;
import com.disneybank.dao.AccountDaoJdbcPg;
import com.disneybank.dao.UserDao;
import com.disneybank.dao.UserDaoJdbc;

import java.util.List;

public class AccountService {

    private static AccountDao accountDao = new AccountDaoJdbcPg();
    //private static UserDao userDao = new UserDaoJdbc();

    // GET ACCOUNTS =======================================================================================
    public List<String> getAccounts(){
        return accountDao.getAccounts();
    }

    public Account getAccountByNumber(String number){
        // get account by account number
        return accountDao.getAccountByNumber(number);
    }

    public Account getById(int id) {
        // get account by id
        return accountDao.getById(id);
    }

    public List<Account> getAccountsByUser(int user_id){
        // get accounts by user id
        return accountDao.getAccountsByUser(user_id);
    }

    public List<String> getPendingAccounts(){
        // get all pending accounts
        return accountDao.getPendingAccounts();
    }

    // MAKE ACCOUNT =======================================================================================
    public void makeNewAccountUser(Account acct, int user_id){
        // add new account to database
        accountDao.newAccount(acct, user_id);
    }

    public void makeNewJointAccount(Account acct, int user1, int user2){
        //userDao.get
        accountDao.newJointAccount(acct, user1, user2);
    }

    // ACCOUNT TRANSACTIONS =======================================================================================
    public void makeDeposit(double amount, int acct_id){
        // make a deposit
        Account acc = accountDao.getById(acct_id);

        acc.deposit(amount);
        accountDao.updateBalance(acc);
    }

    public void makeWithdrawal(double amount, int acct_id){
        // make a withdrawal
        Account acc = accountDao.getById(acct_id);

        acc.withdraw(amount);
        accountDao.updateBalance(acc);
    }

    public void makeTransfer(double amount, int acct_idA, int acct_idB){
        // transfer money from one account to another
        Account accA = accountDao.getById(acct_idA);
        Account accB = accountDao.getById(acct_idB);

        // withdraw the amount to transfer from accA
        accA.withdraw(amount);

        // deposit the same amount to accB
        accB.deposit(amount);

        accountDao.updateBalance(accA);
        accountDao.updateBalance(accB);
    }

    // CHANGING ACCOUNT STATUS =======================================================================================
    public void changeAccountStatus(int acct_id, boolean status) {
        // change account status from open/close
        accountDao.changeAccountStatus(acct_id, status);
    }

    public void deleteAccount(Account acct) {
        // permanently delete account
        // only for pending accounts that have been rejected
        accountDao.deleteAccount(acct);
    }

    public void changeAccountApproval(int acct_id, boolean approved) {
        //change pending account's approved status
        accountDao.changeAccountApproval(acct_id,approved);
    }
}
