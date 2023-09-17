/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.controllers;


import companyapplication.CompanyApplication;
import companyapplication.data.Department;
import companyapplication.data.Employee;
import companyapplication.models.DepartmentModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleber
 */
public class DepartmentController {
    
    public Department getDepartment(Integer departmentNumber){
        Department department = null;
        
        DepartmentModel dm = new DepartmentModel();
        
        try {
            department = dm.retrieveDepartment(departmentNumber);
        } catch (SQLException ex) {
            Logger.getLogger(
                    DepartmentController.class.getName()).log(
                            Level.SEVERE, null, ex);
        }
        
        return department;
        
    }
    
    
    public Department getDepartmentWithEmployees(Integer departmentNumber){
        
        Department department = null;
        
        DepartmentModel dm = new DepartmentModel();
        
        try {
            department = dm.retrieveDepartmentEmployees(departmentNumber);
        } catch (SQLException ex) {
            Logger.getLogger(
                    DepartmentController.class.getName()).log(
                            Level.SEVERE, null, ex);
        }
        
        return department;
        
    }
    
    public Department getDepartmentComplete(Integer departmentNumber){
        Department department = null;
        
        DepartmentModel dm = new DepartmentModel();
        
        try {
            department = getDepartmentWithEmployees(departmentNumber);
            Employee manager = dm.retrieveDepartmentManager(departmentNumber);
            department.setManager(manager);
            
        } catch (SQLException ex) {
            Logger.getLogger(
                    DepartmentController.class.getName()).log(
                            Level.SEVERE, null, ex);
        }
        
        return department;
        
    }

    public Department getDepartmentComplete2(Integer departmentNumber){
        Department department = null;
        
        DepartmentModel dm = new DepartmentModel();
        
        try {
            department = dm.retrieveDepartmentComplete(departmentNumber);
            
        } catch (SQLException ex) {
            Logger.getLogger(
                    DepartmentController.class.getName()).log(
                            Level.SEVERE, null, ex);
        }
        
        return department;
        
    }

    public ArrayList<Department> getAllDepartments(){
        DepartmentModel dm = new DepartmentModel();
        ArrayList<Department> departments = null;
        try{
            departments = dm.retrieveAllDepartmentComplete();
        }catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return departments;
    }
    
    public void updateProjectsOnDepartment(Department department){
        DepartmentModel dm = new DepartmentModel();
        try{
            department.setProjects(dm.retrieveProjectsOnDepartment(department));
            System.out.println("Projetos do departamento " 
                    + department.getName()
                    + " atualizados com sucesso");
        }catch(SQLException ex){
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
