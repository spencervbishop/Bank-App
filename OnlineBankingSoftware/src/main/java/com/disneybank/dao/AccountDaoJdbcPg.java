package com.disneybank.dao;

import com.disneybank.beans.customers.Account;
import com.disneybank.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoJdbcPg implements AccountDao {
    ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();
    @Override
    public Account getById(int id) {
        try(Connection conn = connectionUtil.getConnection()) {

            String standardQuery = "SELECT account_number, account_type, balance, account_open FROM accounts WHERE account_id = ?";
//
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setInt(1, id);
            ResultSet results = ps.executeQuery();

            boolean isOpen;
            if(results.next()){
                String acctnum = results.getString("account_number");
                String accounttype = results.getString("account_type");
                double balance = results.getDouble("balance");
                if(results.getString("account_open")==null){
                    isOpen = false;
                }else{
                    isOpen = true;
                }
                return new Account(acctnum, accounttype, balance, id, isOpen);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Account getAccountByNumber(String number) {
        try(Connection conn = connectionUtil.getConnection()) {

            String standardQuery = "SELECT account_id, account_type, balance, account_open FROM accounts WHERE account_number = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setString(1, number);
            ResultSet results = ps.executeQuery();

            boolean isOpen;
            if(results.next()){
                int account_id = results.getInt("account_id");
                String accounttype = results.getString("account_type");
                double balance = results.getDouble("balance");
                 if(results.getString("account_open")==null){
                     isOpen = false;
                 }else{
                     isOpen = true;
                 }
                return new Account(number, accounttype, balance, account_id, isOpen);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAccountsByUser(int user_id){
        try(Connection conn = connectionUtil.getConnection()) {

            //Returns all account information for all accounts held by a user in an ArrayList
            String standardQuery = "SELECT accounts.account_id, accounts.account_type, accounts.balance, accounts.account_number, accounts.account_open " +
                    "FROM (accounts FULL OUTER JOIN users_accounts_junction " +
                    "ON accounts.account_id = users_accounts_junction.account_id) " +
                    "WHERE accounts.account_open = TRUE AND users_accounts_junction.user_id = ?";

            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setInt(1, user_id);
            ResultSet results = ps.executeQuery();

            List<Account> allUserAccounts = new ArrayList<>();
            boolean isOpen;
            while(results.next()){
                int id = results.getInt("account_id");
                String type = results.getString("account_type");
             double balance = results.getDouble("balance");
                String number = results.getString("account_number");
                if(results.getString("account_open")==null){
                    isOpen = false;
                }else{
                    isOpen = true;
                }

                Account temp = new Account(number, type, balance, id, isOpen);
                allUserAccounts.add(temp);
            }
            return allUserAccounts;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> getAccounts(){
        try(Connection conn = connectionUtil.getConnection()) {

            String standardQuery = "SELECT account_number FROM accounts";

            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ResultSet results = ps.executeQuery();

            List<String> allAccounts = new ArrayList<>();

            while(results.next()){
                String acctnum = results.getString("account_number");

                allAccounts.add(acctnum);
            }
            return allAccounts;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String newAccount(Account acct, int user_id) {
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare Query
            String query = "INSERT INTO accounts (account_number, account_type, balance, account_open) " +
                    "VALUES (?, ?, ?, FALSE) RETURNING account_id";
            PreparedStatement ps = conn.prepareStatement(query);

            //Assign parameters
            ps.setString(1, acct.getAccountNumber());
            ps.setString(2, acct.getAccountType());
            ps.setDouble(3, acct.getBalance());

            //Get result set storing the primary key for the newly inserted object
            ResultSet results = ps.executeQuery();
            int acct_id;
            if(results.next()){
                acct_id =results.getInt("account_id");
                acct.setAccountId(acct_id);

                String query2 = "INSERT INTO users_accounts_junction (user_id, account_id) VALUES (?, ?) ";
                PreparedStatement ps2 = conn.prepareStatement(query2);

                //Assign parameters
                ps2.setInt(1, user_id);
                ps2.setInt(2, acct_id);

                ps2.execute();
            }else{conn.rollback();}
            return acct.toString();

        }catch(SQLException e){
            e.printStackTrace();
            return "UNABLE TO MAKE ACCOUNT.";
        }
    }


    @Override
    public String newJointAccount(Account acct, int user_id1, int user_id2) {
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare Query
            String query = "INSERT INTO accounts (account_number, account_type, balance, account_open) " +
                    "VALUES (?, ?, ?, FALSE) RETURNING account_id";
            PreparedStatement ps = conn.prepareStatement(query);


            String query2 = "INSERT INTO users_accounts_junction (user_id, account_id) VALUES (?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            PreparedStatement ps3 = conn.prepareStatement(query2);

            //Assign parameters
            ps.setString(1, acct.getAccountNumber());
            ps.setString(2, acct.getAccountType());
            ps.setDouble(3, acct.getBalance());


            //Get result set storing the primary key for the newly inserted object
            ResultSet results = ps.executeQuery();
            int acct_id;
            if(results.next()){
                acct_id =results.getInt("account_id");
                acct.setAccountId(acct_id);
                ps2.setInt(1, user_id1);
                ps2.setInt(2, acct_id);

                ps3.setInt(1, user_id2);
                ps3.setInt(2, acct_id);

                ps2.execute();
                ps3.execute();
            }else{conn.rollback();}
            return acct.toString();

        }catch(SQLException e){
            e.printStackTrace();
            return "UNABLE TO MAKE ACCOUNT.";
        }
    }


    @Override
    public String updateBalance(Account acct) {
        try (Connection conn = connectionUtil.getConnection()) {
            //Prepare Query
            String query = "UPDATE accounts SET balance = ? " +
                    "WHERE account_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            //Assign parameters
            ps.setDouble(1, acct.getBalance());
            ps.setInt(2, acct.getAccountId());

            //Execute Update
            ps.execute();
            return acct.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Unable to update account.";
        }

    }

    @Override
    public void deleteAccount(Account acct) {
        try(Connection conn = connectionUtil.getConnection()){
            //Prepare query
            String query = "DELETE FROM accounts WHERE account_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, acct.getAccountId());
            ps.execute();

            String query2 = "DELETE FROM users_accounts_junction WHERE account_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setInt(1, acct.getAccountId());
            ps.execute();

        }catch(SQLException e){
            e.printStackTrace();
        }

   }

   @Override
    public void changeAccountStatus (int acct_id, boolean status) {
        // change an account's open/close status
        try(Connection conn = connectionUtil.getConnection()) {

            String query = "UPDATE accounts SET account_open = ? WHERE account_id = ?";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setBoolean(1,status);
            ps.setInt(2,acct_id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
   }

   @Override
    public void changeAccountApproval (int acct_id, boolean approved){
        // change a pending account's approved status
       try(Connection conn = connectionUtil.getConnection()) {
           String query = "UPDATE accounts SET account_approved = ? WHERE account_id = ?";

           PreparedStatement ps = conn.prepareStatement(query);

           ps.setBoolean(1,approved);
           ps.setInt(2,acct_id);

           ps.execute();

       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   @Override
    public List<String> getPendingAccounts() {
        // get all pending accounts

       try(Connection conn = connectionUtil.getConnection()) {
            String query = "SELECT account_number FROM accounts WHERE account_approved IS null";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet results = ps.executeQuery();

            List<String> pendingAccounts = new ArrayList<>();

            while(results.next()){
//                int accountID = results.getInt("account_id");
//                String accountNumber = results.getString("account_number");
//                String accountType = results.getString("account_type");
//                double balance = results.getDouble("balance");
//
//                Account acc = new Account(accountNumber, accountType, balance, accountID);

                String acc = results.getString("account_number");

                pendingAccounts.add(acc);
           }

           return pendingAccounts;

       } catch (SQLException e) {
           e.printStackTrace();

           return null;
       }
   }
}
