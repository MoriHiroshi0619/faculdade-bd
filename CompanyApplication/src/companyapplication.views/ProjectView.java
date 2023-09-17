/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package companyapplication.views;

import companyapplication.data.*;
import companyapplication.controllers.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author hiroshimori
 */
public class ProjectView {
    
    public void showProject(Project project){
        System.out.println("Exibindo informçãoes adicionais do projeto " + project.getNome_projeto());
        project.print();
    }
    
    public void ShowAllProject(ArrayList<Project> projects){
        System.out.println("Exibindo todos os projetos no Banco");
        projects.forEach((p)->{
            p.print();
        });
    }
    
    public void showAllProjectIds(ArrayList<Project> projects){
        System.out.println("Exibindo todos os ID's de projetos no Banco.");
        System.out.print("ID(s): ");
        projects.forEach((p)-> {
           System.out.print("| " + p.getProjetoID() + " | ");
        });
        System.out.println("");
    }
    
    public void showAllEmployesOnProject(Project project){
        System.out.println("-----------------------------------------");
        System.out.println("Exebindo todos os funcionarios trabalhando no projeto -> "
        + project.getNome_projeto());
        for(int i = 0; i < project.getEmployeesOnProject().size(); i++){
            project.getEmployeesOnProject().get(i).print();
        }
    }
    
    public Project askInsertProject(ArrayList<Department> departmentList){
        Project projeto = null;
        DepartmentView dm = new DepartmentView();
        ProjectController pc = new ProjectController();
        
        System.out.println("Começando processo de inserção de um projeto no Banco");
        Scanner teclado = new Scanner(System.in);
        String projnome = null;
        Integer projnumero = 0;
        String projlocal = null;
        String dnr = null;
        do{
            System.out.println("Insira o nome do projeto:");
            projnome = teclado.nextLine();
        }while(projnome.length() < 2); 
        do{
            System.out.println("Insira o local do projeto: ");
            projlocal = teclado.nextLine();
        }while(projlocal.length() < 2);
        do{
            System.out.println("Informe o ID do departamento a ser inserido no projeto.");
            System.out.println("Digite 'n' ou 'N' para não inserir um numero de departamento");
            dm.showAllDepartmentIds(departmentList);
            dnr = teclado.nextLine();
        }while(pc.verifyDnumExists(dnr, departmentList));
        if("n".equals(dnr) || "N".equals(dnr)){
            projeto = new Project(
                    projnome,
                    projnumero,
                    projlocal
            );
        }else{
            projeto = new Project(
                    projnome,
                    projnumero,
                    projlocal,
                    new Department(Integer.parseInt(dnr))
            );
        }
        return projeto;
    } 
    
    public int askRemoveProject (ArrayList<Project> listProject){
        System.out.println("Começando processo de remoção de projeto no banco");
        ProjectController pc = new ProjectController();
        Scanner teclado = new Scanner(System.in);
        Integer id_escolhido;
        do{
            System.out.println("Escolha o ID de projeto que deseja remover");
            this.showAllProjectIds(listProject);
            id_escolhido = teclado.nextInt();
        }while(pc.verifyIdExists(listProject, id_escolhido));
        return id_escolhido;
    }
    
    public Project AskUpdateProject (ArrayList<Project> listProject, ArrayList<Department> departmentList){
        System.out.println("Começando processo de atualização de um projeto no banco");
        Scanner teclado = new Scanner(System.in);
        ProjectController pc = new ProjectController(); 
        DepartmentView dm = new DepartmentView();
        DepartmentController dc = new DepartmentController();
        Project projeto = null;
        String n = null;
        Integer id_escolhido;
        do{
            System.out.println("Escolha o ID de projeto que deseja atualizar");
            this.showAllProjectIds(listProject);
            id_escolhido = teclado.nextInt();
        }while(pc.verifyIdExists(listProject, id_escolhido));
        teclado.nextLine(); //limpar cache
        projeto = pc.getProjectByID(id_escolhido);
        System.out.println("Dados atuais do Projeto selecionado...");
        projeto.print();
        System.out.println("");
        //NÃO ESQUECER DA ATUALIZAÇÃO DO DNR TAMBEM
        
        System.out.println("Digite o novo NOME do projeto");
        n = teclado.nextLine();
        projeto.setNome_projeto(n);
        
        System.out.println("Digite o novo LOCAL do projeto");
        n = teclado.nextLine();
        projeto.setLocal_projeto(n);
  
        do{
            System.out.println("Deseja alterar o departamento do projeto? (s/n)");
            n = teclado.nextLine();
        }while(!"n".equals(n) && !"s".equals(n));
        if("s".equals(n)){
            do{
                System.out.println("Informe o ID do departamento a ser atualizado no projeto.");
                System.out.println("Digite 'n' ou 'N' para não inserir um numero de departamento");
                dm.showAllDepartmentIds(departmentList);
                n = teclado.nextLine();
            }while(pc.verifyDnumExists(n, departmentList));
            if("n".equals(n) || "n".equals(n)){
                projeto.setDepartment(null);
            }else{
                projeto.setDepartment(dc.getDepartment(Integer.parseInt(n)));
            }
        }
        return projeto;
    } 
}
