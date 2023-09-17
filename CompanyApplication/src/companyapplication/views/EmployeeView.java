/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.views;

import companyapplication.data.Employee;
import java.util.ArrayList;

/**
 *
 * @author valga
 */
public class EmployeeView {
    
    public void showEmployee(Employee employee) {
      
        employee.print();
        
    }

    public void showEmployees(ArrayList<Employee> employees) {
        employees.forEach((employee) -> {
            System.out.println("");
            employee.print();
            System.out.println("");
        });       
    }
 
}
