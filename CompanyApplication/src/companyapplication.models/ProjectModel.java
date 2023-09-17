/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package companyapplication.models;

import companyapplication.data.*;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.Date;
import static java.sql.JDBCType.VARCHAR;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hiroshimori
 */
public class ProjectModel {
    
    public Project retrieveProjectByID(int ID) throws SQLException {
        
        Project project = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            connection = CompanyDBConnection.getConnection();

            //System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT projnome, projnumero, projlocal, dnum"
                    + "  FROM projeto"
                    + " WHERE projnumero = '" + ID + "';";

            ResultSet rs = stmt.executeQuery(sql);
            
            
            rs.next();
            
            String projnome = rs.getString("projnome");
            int projnumero = rs.getInt("projnumero");
            String projlocal = rs.getString("projlocal");
            int departmentID = rs.getInt("dnum");
            //verificar se a chave estrangeira DNUM é nula ou não
            if(!rs.wasNull()){
                project = new Project(projnome,
                        projnumero,
                        projlocal,
                        new Department(departmentID));
            }else{
                project = new Project(projnome,
                        projnumero,
                        projlocal);
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
        
        return project;
    }  
    
    public ArrayList<Project> retrieveAllProjects() throws SQLException {
        
        ArrayList<Project> projectList = new ArrayList<Project>();
        Project project = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            connection = CompanyDBConnection.getConnection();

            //System.out.println("Creating statement...");
            System.out.println("");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT projnome, projnumero, projlocal, dnum"
                    + "  FROM projeto;";

            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {

                String projnome = rs.getString("projnome");
                int projnumero = rs.getInt("projnumero");
                String projlocal = rs.getString("projlocal");
                int departmentID = rs.getInt("dnum");

                project = new Project(projnome,
                            projnumero,
                            projlocal,
                            new Department(departmentID)
                );

                projectList.add(project);
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
                
        return projectList;
    }
    
    //retornar todos funcionario trabalhando em um determinado projeto
    public ArrayList<Employee> retrieveEmployeesOnProject(Project project) throws SQLException{
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        EmployeeModel em = new EmployeeModel();
        Employee employee = null;
        Connection connection = null;
        Statement stmt = null;
        
        try{
            connection = CompanyDBConnection.getConnection();
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT fcpf"
                    + " FROM trabalha_em"
                    + " WHERE pnr = " + Integer.toString(project.getProjetoID())
                    + ";";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                String cpf = rs.getString("fcpf");
                employee = em.retrieveEmployee(cpf);
                employeeList.add(employee);
            }
            rs.close();
            stmt.close();
            connection.close();
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
        return employeeList;
    }
    
    public boolean removeProject(Project project) throws SQLException {
        
        boolean removed = false;
        
        Connection connection = null;
        Statement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();
            connection.setAutoCommit(false);

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            String sql1;
           
            String sql2;
            
            sql1 = "DELETE FROM"
                    + " trabalha_em"
                    + " WHERE"
                    + " pnr = " + project.getProjetoID() + ";";
            
            sql2 = "DELETE FROM "
                    + "projeto "
                    + " WHERE "
                    + " projnumero = '" + project.getProjetoID() + "';";

            stmt = connection.createStatement();
             
            //STEP 3: Execute the query statement
            int resutado = stmt.executeUpdate(sql1);
            int result = stmt.executeUpdate(sql2);
            //testando
            //System.out.println("Resultado do result = " + result);
            removed = (result > 0);
            
            //STEP 6: Clean-up environment
            stmt.close();
    
            // STEP7: Commit transaction
            connection.commit();

        }catch(SQLException se){
              //Handle errors for JDBC
            se.printStackTrace();
            
            System.out.println(se.getMessage());
            if (connection != null){
                connection.rollback();
            }
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
        
        return removed;
    }
    
    
    
    public boolean insertProjectWithoutDnr(Project project) throws SQLException {
        
        boolean inserted = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            
            String sql;
            sql = "INSERT INTO "
                    + "projeto (projnome, projnumero, projlocal)"
                    + " VALUES "
                    + "(?, (select max(projnumero) from projeto) + 1, ?);";
                    /*
                    + "('" + project.getNome_projeto() + "',"
                    + "select max(projnumero) from projeto) + 1,"
                    + "'" + project.getLocal_projeto() + "');";*/

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, project.getNome_projeto());
            stmt.setString(2, project.getLocal_projeto());
     
            //STEP 3: Execute the query statement
            stmt.executeUpdate();
            
            inserted = true;
            
            //STEP 6: Clean-up environment
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
        
        return inserted;
    }
    
    public boolean insertProjectWithDnr(Project project) throws SQLException {
        
        boolean inserted = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            
            String sql;
            sql = "INSERT INTO "
                    + "projeto (projnome, projnumero, projlocal, dnum)"
                    + " VALUES "
                    + "(?, (select max(projnumero) from projeto) + 1, ?, ?);";
                    /*
                    + "('" + project.getNome_projeto() + "',"
                    + "select max(projnumero) from projeto) + 1,"
                    + "'" + project.getLocal_projeto() + "');";*/

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, project.getNome_projeto());
            stmt.setString(2, project.getLocal_projeto());
            stmt.setInt(3, project.getDepartmentId());
            //STEP 3: Execute the query statement
            stmt.executeUpdate();
            
            inserted = true;
            
            //STEP 6: Clean-up environment
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
        
        return inserted;
    }
    
    /* atualização de um projeto no banco */
    
    public boolean updateProject(Project project) throws SQLException {
        
        boolean updated = false;
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            connection = CompanyDBConnection.getConnection();

            String sql;
            if(project.getDepartment() != null){
                sql = "UPDATE projeto "
                    + "SET projnome=?, projlocal=?, dnum=?"
                    + " WHERE projnumero = ?; ";
                
                stmt = connection.prepareStatement(sql);
                
                stmt.setString(1, project.getNome_projeto());
                stmt.setString(2, project.getLocal_projeto());
                stmt.setInt(3, project.getDepartmentId());
                stmt.setInt(4, project.getProjetoID());
                
                stmt.executeUpdate();
            }else{
                sql = "UPDATE projeto "
                    + "SET projnome=?, projlocal=?, dnum = null"
                    + " WHERE projnumero = ?; ";

                stmt = connection.prepareStatement(sql);
           
                stmt.setString(1, project.getNome_projeto());
                stmt.setString(2, project.getLocal_projeto());
                stmt.setInt(3, project.getProjetoID());

                stmt.executeUpdate();
            }
            
            updated = true;
            stmt.close();
            connection.close();

        }catch(SQLException se){
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
            }
        }
        
        return updated;
       
    }
}
