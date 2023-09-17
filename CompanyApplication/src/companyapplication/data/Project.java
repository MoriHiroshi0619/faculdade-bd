/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package companyapplication.data;

import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author hiroshimori
 */
public class Project {
    private String nome_projeto;
    private int projetoID; //chave primaria
    private String local_projeto;
    private ArrayList<Employee> funcionarios = new ArrayList<Employee>();
    Department departamento;
    
    public Project (String np, String lp){
        this.nome_projeto = np;
        this.local_projeto = lp;
    }
    
    public Project (String np, int pID, String lp){
        this.nome_projeto = np;
        this.projetoID = pID;
        this.local_projeto = lp;
        this.departamento = null;
    }
    
    public Project (String np, int pID, String lp, Department d){
        this.nome_projeto = np;
        this.projetoID = pID;
        this.local_projeto = lp;
        this.departamento = d;
    }
    
    public String getNome_projeto(){
        return this.nome_projeto;
    }
    
    public int getProjetoID(){
        return this.projetoID;
    }
    
    public String getLocal_projeto(){
        return this.local_projeto;
    }
    
    public Department getDepartment(){
        return this.departamento;
    }
    
    public int getDepartmentId(){
        return this.departamento.getDepartmentId();
    }
    
    public ArrayList<Employee> getEmployeesOnProject(){
        return this.funcionarios;
    }
    
    public void setEmployeesOnProject(ArrayList<Employee> employee){
        this.funcionarios = employee;
    }
    
    public void setNome_projeto(String n){
        this.nome_projeto = n;
    }
    
    public void setLocal_projeto(String n){
        this.local_projeto = n;
    }
    
    public void setDepartment(Department department){
        this.departamento = department;
    }
    
    public void print(){
        System.out.println("-------------------------------------------------");
        System.out.println("Nome do projeto = " + this.getNome_projeto());
        System.out.println("ID do projeto = " + Integer.toString(getProjetoID()));
        System.out.println("Local do projeto = " + getLocal_projeto());
        if(getDepartment() != null){
            System.out.println("Numero de departamento = " + 
                    this.departamento.getDepartmentId());
        }else{
            System.out.println("*Projeto sem departamento associado...");
        }
    }
}
