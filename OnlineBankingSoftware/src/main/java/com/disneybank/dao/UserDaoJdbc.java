package com.disneybank.dao;

import com.disneybank.beans.customers.User;
import com.disneybank.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    @Override
    public User getById(int id) {
        try(Connection conn = connectionUtil.getConnection()){
            String query = "SELECT first_name, last_name, ssn, username, password FROM users " +
                    "WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet results = ps.executeQuery();

            if(results.next()){
                String firstname = results.getString("first_name");
                String lastname = results.getString("last_name");
                String ssn = results.getString("ssn");
                String username = results.getString("username");
                String password = results.getString("password");
                return new User(firstname, lastname, ssn, username, password);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        try(Connection conn = connectionUtil.getConnection()){
            String query = "SELECT first_name, last_name, ssn, password, user_id FROM users " +
                    "WHERE username = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username.toUpperCase());
            ResultSet results = ps.executeQuery();

            if(results.next()){
                String firstname = results.getString("first_name");
                String lastname = results.getString("last_name");
                String ssn = results.getString("ssn");
                String password = results.getString("password");
                int id = results.getInt("user_id");
                return new User(firstname, lastname, ssn, username, password, id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getIdBySsn(String ssn){
        try(Connection conn = connectionUtil.getConnection()){
            String query = "SELECT user_id FROM users WHERE ssn = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, ssn);
            ResultSet results = ps.executeQuery();

            int user_id=0;
            if (results.next()) {
                user_id = results.getInt("user_id");
            }

            return user_id;

        }catch(SQLException e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<String> getUsernames(){
        try(Connection conn = connectionUtil.getConnection()) {
            String query = "SELECT username FROM users";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet results = ps.executeQuery();

            List<String> allUsernames = new ArrayList<String>();

            while(results.next()){
                allUsernames.add(results.getString("username"));
            }
            return allUsernames;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getSsns(){
        try(Connection conn = connectionUtil.getConnection()) {
            String query = "SELECT ssn FROM users";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet results = ps.executeQuery();

            List<String> allSsns = new ArrayList<String>();

            while(results.next()){
                allSsns.add(results.getString("ssn"));
            }
            return allSsns;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void newUser(User user) {
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare Query
            String query = "INSERT INTO users (first_name, last_name, ssn, username, password) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING user_id";
            PreparedStatement ps = conn.prepareStatement(query);

            //Assign parameters
            ps.setString(1, user.getFirstname().toUpperCase());
            ps.setString(2, user.getLastname().toUpperCase());
            ps.setString(3, user.getSsn());
            ps.setString(4, user.getUsername().toUpperCase());
            ps.setString(5, user.getPassword());

            //Get result set storing the primary key for the newly inserted object
            ResultSet results = ps.executeQuery();
            if(results.next()){
                user.setId(results.getInt("user_id"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean matchJointAccountCode(String ssn1, String ssn2, String code){
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare Query
            String query = "SELECT code FROM joint_codes WHERE (ssn1, ssn2)=(?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, ssn1);
            ps.setString(2, ssn2);
            ResultSet match = ps.executeQuery();

            List<String> codes = new ArrayList<>();
            while(match.next()){
                String c = match.getString("code");
                codes.add(c);
            }
            for(String i : codes){
                if(code.equals(i)){
                    String query2 = "DELETE FROM joint_codes WHERE code=?";
                    PreparedStatement ps2 = conn.prepareStatement(query2);
                    ps2.setString(1, code);
                    ps2.execute();
                    return true;

                }
            }
            return false;


        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String createJointAccountCode(String ssn1, String ssn2, String code){
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare Query
            String query = "INSERT INTO joint_codes (ssn1, ssn2, code) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, ssn1);
            ps.setString(2, ssn2);
            ps.setString(3, code);
            ps.execute();

            return "Success! Your generated code is: ";

        }catch(SQLException e){
            e.printStackTrace();
            return "You can't apply again until your application has been reviewed.";
        }
    }

    @Override
    public void update(User user, int user_id) {
        try (Connection conn = connectionUtil.getConnection()) {
            //Prepare Query
            String query = "UPDATE users SET first_name = ?, last_name = ?, ssn = ?, username = ?, password = ?" +
                    "WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Assign parameters
            ps.setString(1, user.getFirstname().toUpperCase());
            ps.setString(2, user.getLastname().toUpperCase());
            ps.setString(3, user.getSsn());
            ps.setString(4, user.getUsername().toUpperCase());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user_id);

            //Execute Update
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(User user) {
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare query
            String query = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, user.getId());
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
