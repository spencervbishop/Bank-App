package com.disneybank.services;

import com.disneybank.beans.customers.User;
import com.disneybank.dao.UserDao;
import com.disneybank.dao.UserDaoJdbc;

import java.util.List;

public class UserService {

    private static UserDao userDao = new UserDaoJdbc();

    public User getUser(int id){
        return userDao.getById(id);
    }

    public User getUserByUsername(String username){
        User person = userDao.getByUsername(username.toUpperCase());
        return person;
    }
    public int getUserId(String username){
        User person = userDao.getByUsername(username.toUpperCase());
        return person.getId();
    }

    public int getUserIdBySsn(String ssn){
        return userDao.getIdBySsn(ssn);
    }

    public List<String> getAllUsernames(){
        return userDao.getUsernames();
    }

    public List<String> getAllSsns(){
        return userDao.getSsns();
    }

    public void saveUser(User user){
        userDao.newUser(user);
    }

    public void updateUserInfo(User user, int id){
        userDao.update(user, id);
    }

    public boolean matchJointAccountCode(String maker, String finalizer, String code){
        return userDao.matchJointAccountCode(maker, finalizer, code);
    }

    public String createJointAccountCode(String ssn1, String ssn2, String code){
        return userDao.createJointAccountCode(ssn1, ssn2, code);
    }
//    public void deleteUser(User user){
//        userDao.delete(user);
//    }
}
