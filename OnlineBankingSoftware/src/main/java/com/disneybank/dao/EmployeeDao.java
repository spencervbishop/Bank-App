package com.disneybank.dao;
import com.disneybank.beans.bankemployees.Employee;

import java.util.List;

public interface EmployeeDao {

    public Employee getById(int id);

    public Employee getByEAC(String EAC);

//    public List<Employee> getAllEmployees(String EAC);

    public List<String> getAllEAC();

//    public void newEmployee(Employee employee);
//
//    public void updateEmployee(Employee employee);
//
//    public void deleteEmployee(Employee employee);
}
