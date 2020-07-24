package com.disneybank.services;

import com.disneybank.beans.bankemployees.Employee;
import com.disneybank.dao.EmployeeDao;
import com.disneybank.dao.EmployeeDaoJdbcPg;

import java.util.List;

public class EmployeeService {
    private static EmployeeDao employeeDao = new EmployeeDaoJdbcPg();

    public Employee getById(int id){
        // get bank employee by id
        return employeeDao.getById(id);
    }

    public Employee getByEAC(String EAC){
        // get bank employee by EAC
        return employeeDao.getByEAC(EAC);
    }

//    public List<Employee> getAllEmployees(String EAC){
//        return employeeDao.getAllEmployees(EAC);
//    }

    public List<String> getAllEAC() {
        return employeeDao.getAllEAC();
    }

//    public Employee saveEmployee(Employee employee){
//        // create and save employee in db
//        employeeDao.newEmployee(employee);
//        return employee;
//    }
//
//    public void updateEmployee(Employee employee){
//        employeeDao.updateEmployee(employee);
//    }
//
//    public void deleteEmployee(Employee employee){
//        employeeDao.deleteEmployee(employee);
//    }
}
