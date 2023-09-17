/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.models;

import companyapplication.data.*;
import companyapplication.controllers.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author cleber
 */
public class DepartmentModel {
    
    /* Leitura de um departamento dado o seu numero  */
    
    public Department retrieveDepartmentWithoutLocation(Integer ID) throws SQLException {
        Department department = null;
        EmployeeController ec = new EmployeeController();
        Connection connection;
        Statement stmt = null;
        
        connection = CompanyDBConnection.getConnection();
        
        stmt = connection.createStatement();
        
        String sql = "SELECT dnumero, dnome, cpf_gerente, data_inicio_gerente"
                + " FROM departamento"
                + " WHERE dnumero = " + ID + ";";
        

        
        ResultSet rs = stmt.executeQuery(sql); 
        
        // ResultSet rs = stmt.executeQuery(sql);
        
        
        rs.next();
        // Departamento encontrado
        String dnome = rs.getString("dnome");
        int dnumero = rs.getInt("dnumero");
        String cpf = rs.getString("cpf_gerente");
        Date inicio_gerente = rs.getDate("data_inicio_gerente");
        Employee gerente = ec.getEmployee(cpf);

        department = new Department(
                            dnumero, 
                            dnome, 
                            gerente,
                            inicio_gerente,
                            "Sem localização registado..");    
        stmt.close();
        connection.close();
        
        return department;
    }
    
    public Department retrieveDepartment(Integer departmentNumber) throws SQLException {
        Department department = null;
        EmployeeController ec = new EmployeeController();
        DepartmentModel dm = new DepartmentModel();
        Connection connection;
        Statement stmt;
        
        connection = CompanyDBConnection.getConnection();
        
        stmt = connection.createStatement();
        
        String sql = "SELECT D.dnumero AS dnumero, D.dnome AS dnome, "
                + " D.cpf_gerente AS cpf_gerente, L.dlocal AS dlocal, "
                + " D.data_inicio_gerente "
                + " FROM departamento D, localizacao_dep L "
                + " WHERE D.dnumero = L.dnumero AND D.dnumero = ?";
        
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, departmentNumber);
        
        ResultSet rs = pstmt.executeQuery(); 
        
        // ResultSet rs = stmt.executeQuery(sql);
        
        
        if (rs.next()){
            // Departamento encontrado
            String dnome = rs.getString("dnome");
            int dnumero = rs.getInt("dnumero");
            String dlocal = rs.getString("dlocal");
            String cpf = rs.getString("cpf_gerente");
            Date inicio_gerente = rs.getDate("data_inicio_gerente");
            Employee gerente = ec.getEmployee(cpf);
            
            department = new Department(
                                dnumero, 
                                dnome, 
                                gerente,
                                inicio_gerente,
                                dlocal);
            
        } else {
            department = dm.retrieveDepartmentWithoutLocation(departmentNumber);
        }
        
        pstmt.close();
        connection.close();
        
        return department;
    }
   
    
    public Department retrieveDepartmentComplete(Integer departmentNumber) throws SQLException {
        Department department = null;
        Connection connection;
        Statement stmt;
        EmployeeModel em = new EmployeeModel();
        DepartmentModel dm = new DepartmentModel();
        
        connection = CompanyDBConnection.getConnection();
        
        stmt = connection.createStatement();
        
        String sql = "SELECT D.dnumero AS dnumero, D.dnome AS dnome, "
                + " D.cpf_gerente AS cpf_gerente, D.data_inicio_gerente, "
                + " L.dlocal AS dlocal "
                + " FROM departamento D, localizacao_dep L "
                + " WHERE D.dnumero = L.dnumero AND D.dnumero = " + 
                departmentNumber.toString();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        if (rs.next()){
            Integer dnumero = rs.getInt("dnumero");
            String dnome =  rs.getString("dnome");
            Employee manager = retrieveDepartmentManager(departmentNumber);
            Date manager_startdate = rs.getDate("data_inicio_gerente");
            String dlocal =  rs.getString("dlocal");
            ArrayList<Employee> employees = em.
                    retrieveAllEmployeesByDepartment(departmentNumber);
            
            
            // Departamento encontrado
            department = new Department(
                    dnumero,
                    dnome,
                    dnumero,
                    manager,
                    manager_startdate,
                    dlocal,
                    employees);
            
        } else {
            department = dm.retrieveDepartmentWithoutLocation(departmentNumber);
            ArrayList<Employee> employees = em.
                    retrieveAllEmployeesByDepartment(departmentNumber);
            department.setEmployees(employees);
        }
        
        stmt.close();
        connection.close();
        
        return department;
    }
   
    
    
    
     /* Leitura de um gerente de um departamento  */
    
    public Employee retrieveDepartmentManager(Integer departmentNumber) throws SQLException {
        Employee employee = null;
        
        Connection connection;
        Statement stmt;
        
        connection = CompanyDBConnection.getConnection();
        
        stmt = connection.createStatement();
        
        String sql = "SELECT D.cpf_gerente AS cpf_gerente "
                + " FROM departamento D "
                + " WHERE  D.dnumero = " + 
                departmentNumber.toString();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        if (rs.next()){
            // Recuperar o CPF do gerente do departamento
            String cpf_gerente =  rs.getString("cpf_gerente");
            EmployeeModel em = new EmployeeModel();
            employee = em.retrieveEmployee(cpf_gerente);
            
        } else {
            System.out.println("Nenhum departamento encontrado com o numero"
                    + " pesquisado!");
        }
        
        stmt.close();
        connection.close();
        
        return employee;
    }
   
    
    /* Leitura de um departamento dado o seu nome  */    
    
    public ArrayList<Department> retrieveDepartmentsByName(String departmentName) 
            throws SQLException {
        Department department = null;
        ArrayList<Department> departments = new ArrayList<>();
        Connection connection;
        Statement stmt;
        
        connection = CompanyDBConnection.getConnection();
        
        stmt = connection.createStatement();
        
        String sql = "SELECT D.dnumero AS dnumero, D.dnome AS dnome, "
                + " D.cpf_gerente AS cpf_gerente, L.dlocal AS dlocal "
                + " FROM departamento D, localizacao_dep L "
                + " WHERE D.dnumero = L.dnumero AND D.dnome ILIKE '" + 
                departmentName + "'";
        
        
        
        ResultSet rs = stmt.executeQuery(sql);
        
        while (rs.next()){
            // Departamento encontrado
            department = new Department(
                            rs.getString("dnome"), 
                            rs.getInt("dnumero"), 
                            rs.getString("dlocal"));
          
            departments.add(department);
        } 
        
        stmt.close();
        connection.close();
        
        return departments;
    }
    
    
    
    /* Leitura de um departamento e seus empregados */
    public Department retrieveDepartmentEmployees(Integer departmentNumber) throws SQLException {
        
        Department department = retrieveDepartment(departmentNumber);
        
        if (department != null){
        
            // Departamento encontrado
            EmployeeModel em = new EmployeeModel();
            ArrayList<Employee> employees
                    = em.retrieveAllEmployeesByDepartment(departmentNumber);

            employees.forEach(employee -> department.addEmployee(employee));
 
        } else {
            System.out.println("Nenhum departamento encontrado com o numero"
                    + " pesquisado!");
        }
        
        return department;
    }
    
    
    
    /* Leitura de todos os departamentos   */
    public ArrayList<Department> retrieveAllDepartmentComplete() throws SQLException {
        
        ArrayList<Department> departmentList = new ArrayList<Department>();
        DepartmentModel dm = new DepartmentModel();
        Department department = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            connection = CompanyDBConnection.getConnection();

            //System.out.println("Creating statement...");
            System.out.println("");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT dnumero"
                    + "  FROM departamento;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {

                int dnumero = rs.getInt("dnumero");
                department = dm.retrieveDepartmentComplete(dnumero);
                

                departmentList.add(department);
            }
            
            rs.close();
            stmt.close();
            connection.close();

        }catch(SQLException se){
              //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
                
        return departmentList;
    }
    
    /* Insercao de um departamento  */
    
    /* Atualizacao de um departamento */
    
    /* Remocao de um departamento */
    
    
    public ArrayList<Project> retrieveProjectsOnDepartment(Department department) throws SQLException{
        ArrayList<Project> projectList = new ArrayList<>();
        ProjectModel pm = new ProjectModel();
        Project project = null;
        Connection connection = null;
        Statement stmt = null;
        
        try{
            connection = CompanyDBConnection.getConnection();
            stmt = connection.createStatement();
            String sql;
            
            sql = "SELECT projnumero"
                    + " FROM departamento join projeto on dnumero = dnum"
                    + " WHERE dnumero = " + department.getDepartmentId() + ";";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                int projnumero = rs.getInt("projnumero");
                project = pm.retrieveProjectByID(projnumero);
                projectList.add(project);
            }
            
        }catch(SQLException se){
            se.printStackTrace();
        }finally{
            if(stmt != null){
                stmt.close();
            }
            try{
                if(connection != null){
                    connection.close();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        
        return projectList;
    }
}
