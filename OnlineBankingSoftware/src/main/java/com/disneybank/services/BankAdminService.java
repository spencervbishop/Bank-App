package com.disneybank.services;

import com.disneybank.beans.bankemployees.BankAdmin;
import com.disneybank.dao.BankAdminDao;
import com.disneybank.dao.BankAdminDaoJdbcPg;

import java.util.List;

public class BankAdminService {

    private static BankAdminDao bankAdminDao = new BankAdminDaoJdbcPg();

    public BankAdmin getById (int id) {
        // get the bank admin by id
        return bankAdminDao.getById(id);
    }

    public BankAdmin getByBAAC (String BAAC) {
        // get the bank admin by BAAC
        return bankAdminDao.getByBAAC(BAAC);
    }

//    public List<BankAdmin> getAllBankAdmin(){
//        return bankAdminDao.getAllBankAdmin();
//    }

    public List<String> getAllBAAC(){
        return bankAdminDao.getAllBAAC();
    }

//    public BankAdmin saveBankAdmin (BankAdmin bankAdmin) {
//        // create new bank admin and save to db
//         bankAdminDao.newBankAdmin(bankAdmin);
//        return bankAdmin;
//    }
//
//    public void updateBankAdmin (BankAdmin bankAdmin) {
//        // update bank admin fields
//
//        bankAdminDao.updateBankAdmin(bankAdmin);
//    }
//
//    public void deleteBankAdmin (BankAdmin bankAdmin) {
//        // delete bank admin from db
//
//        bankAdminDao.deleteBankAdmin(bankAdmin);
//    }
}
