/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package companyapplication.controllers;

import companyapplication.CompanyApplication;
import companyapplication.data.*;
import companyapplication.models.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author hiroshimori
 */
public class ProjectController {
    
    public Project getProjectByID(int ID){
        ProjectModel pm = new ProjectModel();
        Project project = null;
        try {
            project = pm.retrieveProjectByID(ID);
        } catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return project;
    }
    
    public ArrayList<Project> getAllProjects(){
        ProjectModel pm = new ProjectModel();
        ArrayList<Project> projects = null;
        try {
            projects = pm.retrieveAllProjects();
        } catch (SQLException ex) {
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projects;
    }
    
    //tem que arrumar esse metodo de remoção ainda....
    public boolean removeProjectByID(int ID){
        ProjectModel pm = new ProjectModel();
        Project project = null;
        boolean removed = false;
        try{
            project = pm.retrieveProjectByID(ID);
            removed = pm.removeProject(project);
        }catch(SQLException ex){
            Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }
    
    public boolean insertProject(Project project){
        ProjectModel pm = new ProjectModel();
        boolean inserted = false;
        if(project.getDepartment() == null){
            try{
                inserted = pm.insertProjectWithoutDnr(project);
            }catch(SQLException ex){
                Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try{
                inserted = pm.insertProjectWithDnr(project);
            }catch(SQLException ex){
                Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return inserted;
    }
    
    public boolean updateProject(Project project){
        ProjectModel pm = new ProjectModel();
        boolean updated = false;
        try{
                updated = pm.updateProject(project);
            }catch(SQLException ex){
                Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return updated;
    }
    
    
    //busca todos os funcionarios que estão trabalhando em um determinado projeto
    public void updateEmployeesOnProject(Project project){
        ProjectModel pm = new ProjectModel();
        try{
            project.setEmployeesOnProject(pm.retrieveEmployeesOnProject(project));
            System.out.println("Funcionarios trabalhando no projeto" 
                    + project.getNome_projeto()
                    + " atualizados com sucesso");
        }catch(SQLException ex){
                Logger.getLogger(CompanyApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean verifyDnumExists(String dnum, ArrayList<Department> departmentList){
        boolean approved = true;
        if("n".equals(dnum) || "N".equals(dnum)){
            approved = false;
        }else{
            try{
                for(int i = 0; i < departmentList.size(); i++){
                    if(departmentList.get(i).getDepartmentId() == Integer.parseInt(dnum)){
                        approved = false;
                    }
                }
            }catch(NumberFormatException e){
                approved = true;
            }
        }
        return approved;
    }
    
    public boolean verifyIdExists(ArrayList<Project> listProject, int id){
        boolean approved = true;
        for(int i = 0; i < listProject.size(); i++){
            if(listProject.get(i).getProjetoID() == id){
                approved = false;
            }
        }
        return approved;
    }
}
