/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.controllers;

import companyapplication.CompanyApplication;
import companyapplication.data.Employee;
import companyapplication.models.EmployeeModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valga
 */
public class EmployeeController {
    
    public Employee getEmployee(String cpf) {
        EmployeeModel em = new EmployeeModel();
        Employee employee = null;
        try {
            employee = em.retrieveEmployee(cpf);
            //employee.print();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return employee;
    }
    
    public ArrayList<Employee> getEmployees() {
        EmployeeModel em = new EmployeeModel();
        ArrayList<Employee> employees = null;
        try {
            employees = em.retrieveAllEmployees();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public boolean removeEmployee(Employee employee) {
        EmployeeModel em = new EmployeeModel();
        boolean removed = false;
        try {
            removed = em.removeEmployee(employee);
        } catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }


    public void callEmployeeName(String cpf) {
        EmployeeModel em = new EmployeeModel();
        
        try {
            em.callEmployeeNameProcedure(cpf);
            
        } catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
