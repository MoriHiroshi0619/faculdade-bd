/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.data;

import java.sql.Date;

/**
 *
 * @author valga
 */
public class Employee {
    
    String cpf;
    String firstName;
    String middleInitial;
    String lastName;
    Date birhtDate;
    String address;
    double salary;
    char sex;
    Department department;

    public Employee(String cpf, String firstName, String middleInitial, String lastName) {
        this.cpf = cpf;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
    }

    public Employee(String cpf, String firstName, String middleInitial, String lastName, Date birhtDate, String address, double salary, char sex, Department department) {
        this.cpf = cpf;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.birhtDate = birhtDate;
        this.address = address;
        this.salary = salary;
        this.sex = sex;
        this.department = department;
    }

    public String getCpf() {
        return cpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirhtDate() {
        return birhtDate;
    }

    public String getAddress() {
        return address;
    }

    public double getSalary() {
        return salary;
    }

    public char getSex() {
        return sex;
    }

    public Department getDepartment() {
        return department;
    }
    
    
    
    
    
    public void print() {
        System.out.println("------------------------------------------");
        System.out.println("CPF = " + this.cpf);
        System.out.println("Nome = " + this.firstName +
                " " + this.middleInitial + " " + this.lastName);
        System.out.println("Data de nascimento = " + this.birhtDate);
        System.out.println("Endereço = " + this.address);
        System.out.println("Salário = " + this.salary);
        System.out.println("Sexo = " + this.sex);
        if(this.department != null){
            System.out.println("Dnr = " + this.department.getDepartmentId());
        }
    }
    
}
