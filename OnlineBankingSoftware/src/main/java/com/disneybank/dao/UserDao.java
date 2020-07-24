package com.disneybank.dao;

import com.disneybank.beans.customers.User;

import java.util.List;

public interface UserDao {

    public User getById(int id);

    public User getByUsername(String username);

    public int getIdBySsn(String ssn);

    public boolean matchJointAccountCode(String ssn1, String ssn2, String code);

    public String createJointAccountCode(String ssn1, String ssn2, String code);

    public List<String> getUsernames();

    public List<String> getSsns();

    public void newUser(User user);

    public void update(User user, int id);

    public void delete(User user);

}