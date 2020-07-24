package com.disneybank.dao;
import com.disneybank.beans.bankemployees.BankAdmin;

import java.util.List;

public interface BankAdminDao {

    public BankAdmin getById(int id);

    public BankAdmin getByBAAC(String BAAC);

//    public List<BankAdmin> getAllBankAdmin();

    public List<String> getAllBAAC();

//    public void newBankAdmin(BankAdmin bankAdmin);
//
//    public void updateBankAdmin(BankAdmin bankAdmin);
//
//    public void deleteBankAdmin(BankAdmin bankAdmin);
}
