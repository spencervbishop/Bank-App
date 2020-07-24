package com.disneybank.beans.customers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class User {
    String firstname;
    String lastname;
    String ssn;                         //could make this transient for security
    String username;
    String password;
    int user_id;
    //String status = "Apply";

    public User(){};

    public User(String fname, String lname, String ssn, String username, String password, int id){
        if(setFirstname(fname)) {this.firstname = fname;}
        if(setLastname(lname)){this.lastname = lname;}
        if(setSsn(ssn)){this.ssn = ssn;}
        if(setUsername(username)){this.username = username;}
        if(setPassword(password)){this.password = password;}
        this.user_id = id;
    };

    public User(String fname, String lname, String ssn, String username, String password){
        if(setFirstname(fname)) {this.firstname = fname;}
        if(setLastname(lname)){this.lastname = lname;}
        if(setSsn(ssn)){this.ssn = ssn;}
        if(setUsername(username)){this.username = username;}
        if(setPassword(password)){this.password = password;}
    };

    public String getFirstname() {
        return firstname;
    }

    public boolean setFirstname(String firstname) {
        boolean maxlen = firstname.length()>0;
        boolean temp = firstname.matches("[a-zA-Z]+");
        if(firstname != null && temp && maxlen){
            this.firstname = firstname.toUpperCase(); return true;}
        else{ return false;}
    }

    public String getLastname() {
        return lastname;
    }

    public boolean setLastname(String lastname){
        boolean maxlen = lastname.length()>0;
        boolean temp = lastname.matches("[a-zA-Z]+");
        if(temp && maxlen){
            this.lastname = lastname.toUpperCase(); return true;}
        else{ return false;}
    }

    public String getSsn() {
        return ssn;
    }

    public boolean setSsn(String ssn) {   //MUST ENSURE IT DOESNT ALREADY EXIST IN SYSTEM
        boolean temp = ssn.matches("[0-9]+");
        boolean len = ssn.length()==9;
        if( ssn != null && temp && len){
            this.ssn = ssn;
            return true;}
        else{ return false;}
    }

    public String getUsername() {
        return username;
    }

    public boolean setUsername(String input) {
        if (input != null && input.length() >= 8 && input.length() <= 20) {
            this.username = input.toUpperCase();
            return true;
        } else {
            return false;
        }
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String input){

        if(input != null && input.length()>=8 && input.length()<=20){
            this.password = input;

            return true;
        } else{
            return false;
        }
    }

    public int getId(){
        return user_id;
    }

    public void setId(int id){
        this.user_id = id;
    }
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public boolean checkUsername(HashMap<String,User> a, String name){
//        return a.containsKey(name.toUpperCase());        //this may need to go elsewhere also
//
//    }


    @Override
    public String toString() {
        return  "==="+firstname+ " INFORMATION===\n" +
                "Name: " + firstname + " " + lastname + " \n"+
                "SSN: " + "***-**-" + ssn.substring(5) + "\n" +
                "Username: '" + username + "\' \n" +
                "Password: '" + password + "\' \n" +
        "=====================================";
                //"Application Status Approved: " + status;

    }
}
