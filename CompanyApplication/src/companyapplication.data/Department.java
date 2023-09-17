/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.data;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author valga
 */
public class Department {
    
    int departmentId;
    String name;
    Integer number;
    Employee manager;
    Date managerStartDate;
    String location;
    ArrayList<Employee> employees;
    ArrayList<Project> projects;

    public Department(int departmentId) {
        this.departmentId = departmentId;
        this.manager = null;
        this.employees = new ArrayList<>();
    }
    
    public Department(int dnumero, String dnome, Employee gerente, Date inicio_gerente, String dlocal){
        this.departmentId = dnumero;
        this.number = dnumero;
        this.name = dnome;
        this.manager = gerente;
        this.managerStartDate = inicio_gerente;
        this.location = dlocal;
        this.employees = new ArrayList<>();
    }
    
    public Department(String name, Integer number, String location) {
        this.name = name;
        this.number = number;
        this.location = location;
        this.manager = null;
        this.employees = new ArrayList<>();
    }
    

    public Department(int departmentId, String name, Integer number, 
            Employee manager, Date managerStartDate, String location, 
            ArrayList<Employee> employees) {
        this.departmentId = departmentId;
        this.name = name;
        this.number = number;
        this.manager = manager;
        this.managerStartDate = managerStartDate;
        this.location = location;
        this.employees = employees;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Employee getManager() {
        return manager;
    }

    public Date getManagerStartDate() {
        return managerStartDate;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    
    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    } 
    
    public int getNumberEmployees() {
        return employees.size();
    }
    
    public ArrayList<Project> getProjects(){
        return this.projects;
    }
    
    public void print(){
        System.out.println("--------- Departamento ---------- " );    
        System.out.println("Nome = " + this.name);
        System.out.println("Número = " + this.number);
       
        if(this.getLocation() != null){
            System.out.println("Localização = " + this.location); 
        }

        if(this.manager != null){
            System.out.println("--------Gerente do Departamento ----------- " ); 
            System.out.println("Nome: " + this.manager.getFirstName());
            System.out.println("CPF: " + this.manager.getCpf());
            System.out.println("------------------------------------------- " ); 
        }
    }
    
    public void printWihEmployees() {
        System.out.println("--------- Departamento ---------- " );    
        System.out.println("Nome = " + this.name);
        System.out.println("Número = " + this.number);
        
        if(this.getLocation() != null){
            System.out.println("Localização = " + this.location); 
        }

        if(this.manager != null){
            System.out.println("--------Gerente do Departamento ----------- " ); 
            this.manager.print();
            System.out.println("------------------------------------------- " ); 
        }
            
        
        if(this.employees != null){
            System.out.println("--------Funcionarios que trabalham para esse departamento--------");
            this.employees.forEach(e -> e.print());
            System.out.println("------------------------------------------- " );
        }
            
        
        System.out.println("------------------------------------------- " ); 
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    public void setEmployees(ArrayList<Employee> employees){
        this.employees = employees;
    }
   
    public void setProjects(ArrayList<Project> projects){
        this.projects = projects;
    }
}
