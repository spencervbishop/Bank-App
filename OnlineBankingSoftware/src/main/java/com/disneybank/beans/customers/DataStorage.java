package com.disneybank.beans.customers;

import com.disneybank.beans.customers.Account;
import com.disneybank.beans.customers.User;

import java.util.*;
import java.io.*;


public class DataStorage implements Serializable {
//    public static HashMap<String, Account> AccountMap = new HashMap<String,Account>();
//    public static HashMap<String, User> UserMap = new HashMap<String, User>(); //make static? does this go here?
//
//    public static Set<String> getUserSet(){ //make static? does this even go here?
//        return UserMap.keySet();
//    }
//
//    public static Set<String> getAccountSet(){ //make static? does this even go here?
//        return AccountMap.keySet();
//    }
//
//    public void saveAccountMap() {
//        FileOutputStream Writer = null;
//        ObjectOutputStream stream = null;
//        try {
//            Writer = new FileOutputStream("resources/AccountMap.ser");
//            stream = new ObjectOutputStream(Writer);
//            stream.writeObject(AccountMap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (Writer != null) {
//                    Writer.close();
//                }
//                if (stream != null) {
//                    stream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static ArrayList<Account> generateUserAccounts(User u){
//        ArrayList<Account> userAccounts = new ArrayList<Account>();
//        String ssn = u.getSsn();
//        ArrayList<Account> temp = (ArrayList<Account>)AccountMap.values();
//        for(Account i : temp){
//            if(i.ssn.equals(ssn)){
//                userAccounts.add(i);
//            }
//        }
//        return userAccounts;
//    }
//
//    public void fillAccountMap(){
//        FileInputStream reader = null;
//        ObjectInputStream ois = null;
//        try {
//            reader = new FileInputStream("resources/AccountMap.ser");
//            ois = new ObjectInputStream(reader);
//            AccountMap = (HashMap) ois.readObject();
//        }catch(FileNotFoundException e){
//            System.out.println("File does not exist! Please locate the file.");//new File("resources/AccountMap.ser");
//        }catch(IOException e) {
//            System.out.println("Having trouble reading the file.");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Saved data is not formatted properly.");
//        } finally{
//            try{
//                if(reader != null){
//                    reader.close();
//                }
//                if(ois != null){
//                    ois.close();
//                }
//            }catch (IOException e){
//                e.printStackTrace();
//            }
//        }
//
//
//
//    }
//
//    public void saveUserMap() {
//        FileOutputStream Writer = null;
//        ObjectOutputStream stream = null;
//        try{
//            Writer = new FileOutputStream("resources/UserMap.ser");
//            stream = new ObjectOutputStream(Writer);
//            stream.writeObject(UserMap);
//        }catch(IOException e){
//            e.printStackTrace();
//        }finally{
//            try{
//                if(Writer != null){
//                    Writer.close();
//                }
//                if(stream != null){
//                    stream.close();
//                }
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void fillUserMap() {
//        FileInputStream reader = null;
//        ObjectInputStream ois = null;
//        try {
//            reader = new FileInputStream("resources/UserMap.ser");
//            ois = new ObjectInputStream(reader);
//            UserMap = (HashMap) ois.readObject();
//        } catch (FileNotFoundException e) {
//            System.out.println("File does not exist! Please locate the file.");//new File("resources/UserMap.ser");
//        } catch (IOException e) {
//            System.out.println("Having trouble reading the file.");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Saved data is not formatted properly.");
//        } finally {
//            try {
//                if (reader != null) {
//                    reader.close();
//                }
//                if (ois != null) {
//                    ois.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void generateData(HashMap<String,User> usermap, HashMap<String,Account> accountmap){
//
//        User p1 = new User();
//        p1.setFirstname("Mufasa");
//        p1.setLastname("King");
//        p1.setSsn("123456789");
//        p1.setUsername("KingOfJungl", getUserSet());
//        p1.setPassword("123456789");
//        p1.setStatus("Pending");
//        usermap.put(p1.getUsername(), p1);
//        Account a1 = new Account(p1, getAccountSet());
//        accountmap.put(a1.getAccountNumber(), a1);
//
//
//        User p2 = new User();
//        p2.setFirstname("Cusco");
//        p2.setLastname("Emporer");
//        p2.setSsn("234567891");
//        p2.setUsername("KingofWorld", getUserSet());
//        p2.setPassword("123456789");
//        p2.setStatus("Pending");
//        usermap.put(p2.getUsername(), p2);
//        Account a2 = new Account(p2, getAccountSet());
//        accountmap.put(a2.getAccountNumber(), a2);
//
//        User p3 = new User();
//        p3.setFirstname("Jim");
//        p3.setLastname("Hawkins");
//        p3.setSsn("345678912");
//        p3.setUsername("TrsrPlnt1", getUserSet());
//        p3.setPassword("123456789");
//        p3.setStatus("Pending");
//        usermap.put(p3.getUsername(), p3);
//        Account a3 = new Account(p3, getAccountSet());
//        accountmap.put(a3.getAccountNumber(), a3);
//
//        User p4 = new User();
//        p4.setFirstname("Pocahontas");
//        p4.setLastname("Princess");
//        p4.setSsn("456789123");
//        p4.setUsername("JSmith4eva", getUserSet());
//        p4.setPassword("123456789");
//        p4.setStatus("Pending");
//        usermap.put(p4.getUsername(), p4);
//        Account a4 = new Account(p4, getAccountSet());
//        accountmap.put(a4.getAccountNumber(), a4);
//
//        User p5 = new User();
//        p5.setFirstname("Woody");
//        p5.setLastname("Cowboy");
//        p5.setSsn("567891234");
//        p5.setUsername("AndysFavToy", getUserSet());
//        p5.setPassword("123456789");
//        p5.setStatus("Pending");
//        usermap.put(p5.getUsername(), p5);
//        Account a5 = new Account(p5, getAccountSet());
//        accountmap.put(a5.getAccountNumber(), a5);
//
//
//    }

}
