/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.views;

import companyapplication.data.Department;
import java.util.ArrayList;

/**
 *
 * @author cleber
 */
public class DepartmentView {
 
    public void showDepartment(Department department) {
        try{
        System.out.println("");
        department.print();
        System.out.println("");
        } catch(NullPointerException npe){
            System.out.println("Tentativa de impressão de um departamento nulo");
            System.out.println(npe.getMessage());
        }
    }
    
    public void showAllDepartment(ArrayList<Department> department){
        System.out.println("Exibindo todos os departamentos do Banco.");
        department.forEach((d) -> {
            if(d != null){
                d.print();
            }
        });
    }
    
    public void showAllEmployeesOnDepartment(Department department){
        System.out.println("--------------------------------------------");
        System.out.println("Exibindo todos os funcionarios trabalhando no departamento -> "
                           + department.getName());
        try{
            for(int i = 0; i < department.getNumberEmployees(); i++){
                department.getEmployees().get(i).print();
            }
        }catch(NullPointerException npe){
            
        }
    }

    public void showAllProjectsOnDepartment(Department department){
        System.out.println("-------------------------------------------");
        System.out.println("Exibindo todos os projetos do departamento -> "
                            + department.getName());
        for(int i = 0; i < department.getProjects().size(); i++){
            department.getProjects().get(i).print();
        }
    }
    
    public void showAllDepartmentIds(ArrayList<Department> departments){
        System.out.println("Exibindo todos os ID's de departamentos no Banco.");
        System.out.print("ID(s): ");
        departments.forEach((d)-> {
           System.out.print("| " + d.getDepartmentId() + " | ");
        });
        System.out.println("");
    }
}
