package com.disneybank.controller;

import com.disneybank.beans.customers.Account;

//
//import com.disneybank.beans.bankemployees.BankAdmin;
//import com.disneybank.beans.bankemployees.Employee;
//import com.disneybank.beans.bankemployees.EmployeeDataStorage;
//import customers.DataStorage;
//import customers.User;
//
//import java.io.IOException;
//
//
public class Main {





//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        DataStorage data = new DataStorage();
////        data.fillAccountMap();
////        data.fillUserMap();
//        data.generateData(data.UserMap, data.AccountMap);
//
//        EmployeeDataStorage edata = new EmployeeDataStorage();
//
//        // test subject
//        BankAdmin b1 = new BankAdmin();
//        b1.setFirstName("Peter");
//        b1.setLastName("Pan");
//        b1.setSSN("123-45-6789");
//        b1.setDriversID("12345672");
//        String BAAC1 = b1.getFirstName().substring(0,1)+ b1.getLastName().substring(0,1) + b1.getSSN().substring(7);
//        b1.setBAAC(BAAC1);
//        EmployeeDataStorage.BAMap.put(b1.getBAAC(),b1);
//
////        Employee e2 = new Employee();
////        e2.setFirstName("Wendy");
////        e2.setLastName("Dar1ling");
////        e2.setSSN("122-25-6729");
////        e2.setBirthday("02/22/1995");
////        e2.setDriversID("1223422");
////        String EAC2 = e2.getFirstName().substring(0,1) + e2.getLastName().substring(0,1) + e2.getSSN().substring(7);
////        e2.setEAC(EAC2);
////        EmployeeDataStorage.EMap.put(e2.getEAC(),e2);
//
//
//        edata.fillEMap();
//        edata.fillBAMap();
//
//        boolean running = true;
//        while(running) {
//            User loggedin = Bank.LogIn();
//            if(loggedin == null){
//                running = false;
//            }else if (edata.EMap.containsValue(loggedin)) {
//                Bank.navigateEmployeePage((Employee) loggedin);
//            } else if (edata.BAMap.containsValue(loggedin)) {
//                Bank.navigateBankAdminPage((BankAdmin) loggedin);
//            } else if (data.UserMap.containsValue(loggedin)) {
//                Bank.navigateCustomerPage(loggedin);
//            }
//        }
////        System.out.println(data.AccountMap.toString());
////        System.out.println(data.UserMap.toString());
////        System.out.println(edata.EMap.toString());
////        System.out.println(edata.BAMap.toString());
//        data.saveUserMap();
//        data.saveAccountMap();
//
//
//        edata.saveBAMap();
//        edata.saveEMap();
//
//    }
}

