package com.disneybank.dao;

import com.disneybank.beans.bankemployees.BankAdmin;
import com.disneybank.beans.bankemployees.Employee;
import com.disneybank.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoJdbcPg implements EmployeeDao {

    // Connection
    private static ConnectionUtil connectionUtil = ConnectionUtil.getConnectionUtil();

    // Bank Employee JDBC - PG Queries

    // GET BANK EMPLOYEE ====================================================

    public Employee getById(int id) {
        // get employee by id

        try(Connection conn = connectionUtil.getConnection()) {
            String standardQuery = "SELECT first_name, last_name, SSN, eac FROM bank_employees WHERE employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setInt(1,id);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String SSN = results.getString("ssn");
                String EAC = results.getString("eac");

                return new Employee(id, firstName, lastName, SSN, EAC);
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

    public Employee getByEAC(String EAC) {
        // get bank employee by EAC

        try(Connection conn = connectionUtil.getConnection()) {
            String standardQuery = "SELECT employee_id, first_name, last_name, SSN FROM bank_employees WHERE eac = ?";
            PreparedStatement ps = conn.prepareStatement(standardQuery);
            ps.setString(1,EAC);
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                int id = results.getInt("employee_id");
                String firstName = results.getString("first_name");
                String lastName = results.getString("last_name");
                String SSN = results.getString("ssn");

                return new BankAdmin(id, firstName, lastName, SSN, EAC);
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

//    public List<Employee> getAllEmployees(String EAC){
//        try (Connection conn = connectionUtil.getConnection()) {
//
//            String query = "SELECT employee_id, first_name, last_name, ssn " +
//                    "FROM bank_employees WHERE eac = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1,EAC);
//
//            List<Employee> employees = new ArrayList<>();
//
//            ResultSet results = ps.executeQuery();
//            while (results.next()) {
//                int employeeID = results.getInt("employee_id");
//                String firstName = results.getString("first_name");
//                String lastName = results.getString("last_name");
//                String SSN = results.getString("ssn");
//
//                employees.add(new Employee(employeeID, firstName, lastName, SSN, EAC));
//            }
//
//            return employees;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public List<String> getAllEAC(){
        try (Connection conn = connectionUtil.getConnection()) {

            String query = "SELECT eac FROM bank_employees";
            PreparedStatement ps = conn.prepareStatement(query);

            List<String> allEAC = new ArrayList<>();

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                allEAC.add(results.getString("eac"));
            }

            return allEAC;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // CRUD BANK EMPLOYEE ====================================================

//    public void newEmployee(Employee employee) {
//        // create new bank employee
//
//        try (Connection conn = connectionUtil.getConnection()) {
//            String query = "INSERT INTO bank_employees (first_name, last_name, ssn, eac) VALUES (?, ?, ?, ?) RETURNING employee_id";
//
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1, employee.getFirstName());
//            ps.setString(2, employee.getLastName());
//            ps.setString(3, employee.getSSN());
//            ps.setString(4, employee.getEAC());
//
//            ResultSet results = ps.executeQuery();
//            if (results.next()){
//                employee.setEmployeeID(results.getInt("employee_id"));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void updateEmployee(Employee employee) {
//        // update existing employee
//
//        try (Connection conn = connectionUtil.getConnection()) {
//
//            String query = "UPDATE bank_employees SET " + "first_name = ?, last_name = ?, SSN = ?, eac = ?" +
//                    " WHERE employee_id = ? ";
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setString(1, employee.getFirstName());
//            ps.setString(2, employee.getLastName());
//            ps.setString(3, employee.getSSN());
//            ps.setString(4, employee.getEAC());
//            ps.setInt(5, employee.getEmployeeID());
//
//            ps.execute();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteEmployee(Employee employee) {
//        // delete bank employee
//
//        try(Connection conn = connectionUtil.getConnection()) {
//
//            String query = "DELETE FROM bank_employee WHERE employee_id = ?";
//            PreparedStatement ps = conn.prepareStatement(query);
//
//            ps.setInt(1,employee.getEmployeeID());
//
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
