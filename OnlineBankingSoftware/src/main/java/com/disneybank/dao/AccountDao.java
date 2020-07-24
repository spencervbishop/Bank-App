package com.disneybank.dao;

import com.disneybank.beans.customers.Account;

import java.util.List;

public interface AccountDao {

    public Account getById(int acct_id);

    public Account getAccountByNumber(String number);

    public List<Account> getAccountsByUser(int user_id);

    public List<String> getAccounts();

    public List<String> getPendingAccounts();

    public String newAccount(Account acct, int user_id);

    public String newJointAccount(Account acct, int user1, int user2);

    public String updateBalance(Account acct);

    public void deleteAccount(Account acct);

    public void changeAccountStatus(int acct_id, boolean status);

    public void changeAccountApproval(int acct_id, boolean approved);

}
