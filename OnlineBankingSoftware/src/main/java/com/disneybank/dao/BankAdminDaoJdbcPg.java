package com.disneybank.dao;

import com.disneybank.beans.bankemployees.BankAdmin;
import com.disneybank.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankAdminDaoJdbcPg implements BankAdminDao {

    // Connection
    private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    // Bank Admin JDBC - PG Queries

    // GET BANK ADMIN ====================================================
    public BankAdmin getById(int id) {
        // get bank admin by id

        try(Connection conn = connectionUtil.getConnection()) {
            String standardQuery = "SELECT first_name, last_name, SSN, baac FROM bank_admin WHERE admin_id = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setInt(1,id);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String SSN = results.getString("ssn");
                String BAAC = results.getString("baac");

                return new BankAdmin(id, firstName, lastName, SSN, BAAC);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());

            e.printStackTrace();
        }

        return null;
    }

    public BankAdmin getByBAAC(String BAAC) {
        // get bank admin by BAAC

        try(Connection conn = connectionUtil.getConnection()) {
            String standardQuery = "SELECT admin_id, first_name, last_name, SSN FROM bank_admin WHERE baac = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setString(1,BAAC);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                int id = results.getInt("admin_id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String SSN = results.getString("ssn");

                return new BankAdmin(id, firstName, lastName, SSN, BAAC);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            System.out.println(e.getSQLState());

            e.printStackTrace();
        }

        return null;
    }

//    public List<BankAdmin> getAllBankAdmin(){
//
//        try (Connection conn = connectionUtil.getConnection()) {
//
//            String query = "SELECT admin_id, first_name, last_name, ssn " +
//                    "FROM bank_admin";
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            List<BankAdmin> bankadmin = new ArrayList<>();
//
//            ResultSet results = ps.executeQuery();
//            while (results.next()) {
//                int adminID = results.getInt("admin_id");
//                String firstName = results.getString("first_name");
//                String lastName = results.getString("last_name");
//                String SSN = results.getString("ssn");
//                String BAAC = results.getString("baac");
//
//                bankadmin.add(new BankAdmin(adminID, firstName, lastName, SSN, BAAC));
//            }
//
//            return bankadmin;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<String> getAllBAAC(){
        try (Connection conn = connectionUtil.getConnection()) {

            String query = "SELECT baac FROM bank_admin";
            PreparedStatement ps = conn.prepareStatement(query);

            List<String> allBAAC = new ArrayList<>();

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                allBAAC.add(results.getString("baac"));
            }

            return allBAAC;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // CRUD BANK ADMIN ====================================================

//    public void newBankAdmin(BankAdmin bankAdmin) {
//        // create new bank admin
//
//        try (Connection conn = connectionUtil.getConnection()) {
//            String query = "INSERT INTO bank_admin (first_name, last_name, ssn, baac) VALUES (?, ?, ?, ?) RETURNING admin_id";
//
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1, bankAdmin.getFirstName());
//            ps.setString(2, bankAdmin.getLastName());
//            ps.setString(3, bankAdmin.getSSN());
//            ps.setString(4, bankAdmin.getBAAC());
//
//            ResultSet results = ps.executeQuery();
//            if (results.next()){
//                bankAdmin.setAdminID(results.getInt("admin_id"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void updateBankAdmin(BankAdmin bankAdmin) {
//        // update existing bank admin
//
//        try (Connection conn = connectionUtil.getConnection()) {
//
//            String query = "UPDATE bank_admin SET " + "first_name = ?, last_name = ?, SSN = ?, baac = ? " +
//                    "WHERE admin_id = ? ";
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1, bankAdmin.getFirstName());
//            ps.setString(2, bankAdmin.getLastName());
//            ps.setString(3, bankAdmin.getSSN());
//            ps.setString(4, bankAdmin.getBAAC());
//            ps.setInt(5, bankAdmin.getAdminID());
//
//            ps.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteBankAdmin(BankAdmin bankAdmin) {
//        // delete bank admin
//
//        try(Connection conn = connectionUtil.getConnection()) {
//
//            String query = "DELETE FROM bank_admin WHERE admin_id = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setInt(1,bankAdmin.getAdminID());
//
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
