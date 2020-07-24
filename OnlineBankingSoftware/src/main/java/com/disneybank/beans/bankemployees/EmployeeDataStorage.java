package com.disneybank.beans.bankemployees;

import com.disneybank.beans.customers.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class EmployeeDataStorage implements Serializable {
    public static HashMap<String, BankAdmin> BAMap = new HashMap<String,BankAdmin>();
    public static HashMap<String, Employee> EMap = new HashMap<String, Employee>();
    public static ArrayList<User> pendingApproval = new ArrayList<User>();

    public static HashMap<String, BankAdmin> getBAMap() {
        return BAMap;
    }

    public static HashMap<String, Employee> getEMap() {
        return EMap;
    }

    public static ArrayList<User> getPendingApproval() {
        return pendingApproval;
    }

    public static void setPendingApproval(ArrayList<User> pendingApproval) {
        EmployeeDataStorage.pendingApproval = pendingApproval;
    }

    public void saveBAMap() throws IOException, ClassNotFoundException {
        FileOutputStream Writer = null;
        ObjectOutputStream stream = null;
        try {
            Writer = new FileOutputStream("resources/BAMap.ser");
            stream = new ObjectOutputStream(Writer);
            stream.writeObject(BAMap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Writer != null) {
                    Writer.close();
                }
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillBAMap(){
        FileInputStream reader = null;
        ObjectInputStream ois = null;
        try {
            reader = new FileInputStream("resources/BAMap.ser");
            ois = new ObjectInputStream(reader);
            BAMap = (HashMap) ois.readObject();
        }catch(FileNotFoundException e){
            System.out.println("File does not exist! Please locate the file.");//new File("resources/AccountMap.ser");
        }catch(IOException e) {
            System.out.println("Having trouble reading the file.");
        } catch (ClassNotFoundException e) {
            System.out.println("Saved data is not formatted properly.");
        } finally{
            try{
                if(reader != null){
                    reader.close();
                }
                if(ois != null){
                    ois.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public void saveEMap() {
            FileOutputStream Writer = null;
            ObjectOutputStream stream = null;
            try {
                Writer = new FileOutputStream("resources/EMap.ser");
                stream = new ObjectOutputStream(Writer);
                stream.writeObject(EMap);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (Writer != null) {
                        Writer.close();
                    }
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void fillEMap() {
        FileInputStream reader = null;
        ObjectInputStream ois = null;
        try {
            reader = new FileInputStream("resources/EMap.ser");
            ois = new ObjectInputStream(reader);
            EMap = (HashMap) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist! Please locate the file.");//new File("resources/AccountMap.ser");
        } catch (IOException e) {
            System.out.println("Having trouble reading the file.");
        } catch (ClassNotFoundException e) {
            System.out.println("Saved data is not formatted properly.");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean savePendingApproval() {
        FileOutputStream Writer = null;
        ObjectOutputStream stream = null;
        try {
            Writer = new FileOutputStream("resources/pendingApproval.ser");
            stream = new ObjectOutputStream(Writer);
            stream.writeObject(pendingApproval);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Writer != null) {
                    Writer.close();
                }
                if (stream != null) {
                    stream.close();
                }

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public boolean fillPendingApproval(){
        FileInputStream reader = null;
        ObjectInputStream ois = null;
        try {
            reader = new FileInputStream("resources/pendingApproval.ser");
            ois = new ObjectInputStream(reader);
            pendingApproval = (ArrayList) ois.readObject();
        }catch(FileNotFoundException e){
            System.out.println("File does not exist! Please locate the file.");//new File("resources/AccountMap.ser");
        }catch(IOException e) {
            System.out.println("Having trouble reading the file.");
        } catch (ClassNotFoundException e) {
            System.out.println("Saved data is not formatted properly.");
        } finally{
            try{
                if(reader != null){
                    reader.close();
                }
                if(ois != null){
                    ois.close();
                }
                return true;
            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }

    }


//    public String generateData(){
//
//        Employee e1 = new Employee();
//        e1.setFirstName("Peter");
//        e1.setLastName("Pan");
//        e1.setSSN("123456789");
//        e1.setDriversID("12345672");
//        e1.setUsername("flyboy");
//        e1.setPassword("hahahook22");
//        String EAC1 = e1.getFirstName().substring(0) + e1.getLastName().substring(0) + e1.getSSN().substring(5,8);
//        e1.setEAC(EAC1);
//        EMap.put(e1.getEAC(),e1);
//
//
//        Employee e2 = new Employee();
//        e2.setFirstName("Wendy");
//        e2.setLastName("Darling");
//        e2.setSSN("122256729");
//        e2.setDriversID("1223422");
//        e2.setUsername("missdarling");
//        e2.setPassword("wendydarling");
//        String EAC2 = e2.getFirstName().substring(0) + e2.getLastName().substring(0) + e2.getSSN().substring(5,8);
//        e2.setEAC(EAC2);
//        EMap.put(e2.getEAC(),e2);
//
//        return EMap.toString();
//    }

}
