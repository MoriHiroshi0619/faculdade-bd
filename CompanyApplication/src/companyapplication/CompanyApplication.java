/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication;
import companyapplication.controllers.*;
import companyapplication.data.*;
import companyapplication.views.*;


/**
 *
 * @author hiroshimori
 */

/*
O aluno deve implementar os casos de uso (funcionalidades) básicas para 
o gerenciamento de dados de um PROJETO do banco de dados,
ou seja, as funcionalidades CRUD (create, retrieve, update, delete) 
que consistem na criação, consulta, atualização e 
remoção de um registro de um projeto. 
Ele deve implementar também casos de uso que geram os relatórios de listagem dos
projetos armazenados no banco de dados, 
a lista de funcionários que trabalham em um dado projeto e a lista de projetos 
de um dado departamento.
*/
public class CompanyApplication {

    public static void main(String[] args) {
        DepartmentController dc = new DepartmentController();
        ProjectView pv = new ProjectView();
        ProjectController pc = new ProjectController();
        DepartmentView dv = new DepartmentView();
        
                //INSERÇÃO DE UM PROJETO NO BANCO
        /*
        Project projeto = null;
        projeto = pv.askInsertProject(dc.getAllDepartments());
        if(pc.insertProject(projeto)){
            System.out.println("Projeto inserido no Banco com sucesso");
        }else{
            System.out.println("Falha ao inserir projeto no banco...");
        }
        */
        
                //REMOÇÃO DE UM PROJETO NO BANCO
        /*
        ArrayList<Project> listProject = pc.getAllProjects();
        if(pc.removeProjectByID(pv.askRemoveProject(listProject))){
            System.out.println("Projeto removido do banco com sucesso");
        }else{
            System.out.println("Falha ao remover projeto do Banco");
        }
        */
        
                //ATUALIZAÇÃO DE UM PROJETO NO BANCO
        /*
        Project projeto = null;
        projeto = pv.AskUpdateProject(pc.getAllProjects(), dc.getAllDepartments());
        if(pc.updateProject(projeto)){
            System.out.println("Projeto Atualizado com sucesso");
        }else{
            System.out.println("Falha ao Atualizar projeto...");
        }
        */

                //BUSCA DE UM PROJETO NO BANCO
        
        Project projeto = pc.getProjectByID(2);
        pv.showProject(projeto);
        
        
                //BUSCA DE TODOS OS PROJETOS NO BANCO
        /*
        pv.ShowAllProject(pc.getAllProjects());
        */  
        
                //EXIBIR LISTA DE FUNCIONARIOS DE UM PROJETO
        /*
        Project projeto = pc.getProjectByID(2);
        pc.updateEmployeesOnProject(projeto);
        pv.showAllEmployesOnProject(projeto);
        */
                //EXIBIR LISTA DE FUNCIONARIOS DE UM DEPARTAMENTO
        /*
        Department department = dc.getDepartmentWithEmployees(5);
        dv.showAllEmployeesOnDepartment(department);
        */
        
                //BUSCA DE TODOS OS DEPARTAMENTOS
        /*
        ArrayList<Department> allDepartments = dc.getAllDepartments();
        dv.showAllDepartment(allDepartments);
        */
        
                //EXIBIR LISTA DE PROJETOS DE UM DEPARTAMENTO
        /*
        Department department = dc.getDepartment(5);
        dc.updateProjectsOnDepartment(department);
        dv.showAllProjectsOnDepartment(department);
        */
    }
    
}
