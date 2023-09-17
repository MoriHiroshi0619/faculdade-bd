/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.models;

import companyapplication.data.Employee;
import companyapplication.data.Department;
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
 * @author valga
 */
public class EmployeeModel {
 

    public Employee retrieveEmployee(String cpf) throws SQLException {
        
        Employee employee = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT pnome, minicial, unome, datanasc, sexo, salario,"
                    + " dnr, endereco"
                    + "  FROM funcionario"
                    + " WHERE cpf = '" + cpf + "';";

            //STEP 3: Execute the query statement
            ResultSet rs = stmt.executeQuery(sql);
            
            
            //STEP 4: Extract data from result set
            rs.next();
            
            //Retrieve by column name
            String firstName = rs.getString("pnome");
            String middleInicial = rs.getString("minicial");
            String lastName = rs.getString("unome");
            Date birthDate = rs.getDate("datanasc");
            char sex;
            String temp = rs.getString("sexo");
            if (rs.wasNull()){
                sex = ' ';
            } else {
                sex = temp.charAt(0);
            }
                
            double salary = rs.getDouble("salario");
            int departmentId = rs.getInt("dnr");
            String address = rs.getString("endereco");
           
            //create the employee object
            employee = new Employee(cpf, 
                    firstName, 
                    middleInicial, 
                    lastName,
                    birthDate,
                    address, 
                    salary, 
                    sex, 
                    new Department(departmentId)
            );
            
            //STEP 6: Clean-up environment
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
        
        return employee;
    }    
    
    
    public ArrayList<Employee> retrieveEmployeeByName(String firstName) throws SQLException {
        
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        Employee employee = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT cpf, pnome, minicial, unome, datanasc, sexo, salario,"
                    + " dnr, endereco"
                    + "  FROM funcionario"
                    + " WHERE pnome ILIKE '" + firstName + "%';";

            //STEP 3: Execute the query statement
            ResultSet rs = stmt.executeQuery(sql);
            
            
            //STEP 4: Extract data from result set
            while (rs.next()) {

                //Retrieve by column name
                String cpf = rs.getString("cpf");
                String retFirstName = rs.getString("pnome");
                String middleInicial = rs.getString("minicial");
                String lastName = rs.getString("unome");
                Date birthDate = rs.getDate("datanasc");
                char sex;
                String temp = rs.getString("sexo");
                if (rs.wasNull()){
                    sex = ' ';
                } else {
                    sex = temp.charAt(0);
                }
                
                double salary = rs.getDouble("salario");
                int departmentId = rs.getInt("dnr");
                String address = rs.getString("endereco");

                //create the employee object
                employee = new Employee(cpf,
                        retFirstName,
                        middleInicial,
                        lastName,
                        birthDate,
                        address,
                        salary,
                        sex,
                        new Department(departmentId)
                );

                // Add employee to list
                employeeList.add(employee);
            }
            //STEP 6: Clean-up environment
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
        
        
        return employeeList;
    }
    
    
    public ArrayList<Employee> retrieveAllEmployees() throws SQLException {
        
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        Employee employee = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT cpf, pnome, minicial, unome, "
                    + "datanasc, sexo, salario,"
                    + " dnr, endereco"
                    + "  FROM funcionario;";

            //STEP 3: Execute the query statement
            ResultSet rs = stmt.executeQuery(sql);
            
            
            //STEP 4: Extract data from result set
            while (rs.next()) {

                //Retrieve by column name
                String cpf = rs.getString("cpf");
                String retFirstName = rs.getString("pnome");
                String middleInicial = rs.getString("minicial");
                String lastName = rs.getString("unome");
                Date birthDate = rs.getDate("datanasc");
                //System.out.println(rs.getString("sexo"));
                char sex; //.toCharArray()[0];
                String temp = rs.getString("sexo");
                if (rs.wasNull()){
                    sex = ' ';
                } else {
                    sex = temp.charAt(0);
                }
                double salary = rs.getDouble("salario");
                int departmentId = rs.getInt("dnr");
                String address = rs.getString("endereco");

                //create the employee object
                employee = new Employee(cpf,
                        retFirstName,
                        middleInicial,
                        lastName,
                        birthDate,
                        address,
                        salary,
                        sex,
                        new Department(departmentId)
                );

                // Add employee to list
                employeeList.add(employee);
            }
            //STEP 6: Clean-up environment
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
                
        return employeeList;
    }


    public Employee insertEmployee(Employee employee) throws SQLException {
        
        Employee insertedEmployee = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            
            String sql;
            sql = "INSERT INTO "
                    + "funcionario (pnome, minicial, unome, datanasc, sexo, "
                    + "salario, dnr, endereco, cpf)"
                    + " VALUES "
                    + " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getMiddleInitial());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getBirhtDate().toString());
            stmt.setString(5, String.valueOf(employee.getSex()));
            stmt.setDouble(6, employee.getSalary());
            stmt.setInt(7, employee.getDepartment().getDepartmentId());
            stmt.setString(8, employee.getAddress());
            stmt.setString(9, employee.getCpf());
            
           
            
            //STEP 3: Execute the query statement
            stmt.executeUpdate(sql);
            
            
            
            insertedEmployee = retrieveEmployee(employee.getCpf());
            
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
        
        return insertedEmployee;
    }    

    
    
    public Employee updateEmployee(Employee employee) throws SQLException {
        
        Employee updatedEmployee = null;
        Connection connection = null;
        PreparedStatement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            
            String sql;
            sql = "UPDATE funcionario "
                    + "SET pnome=?, minicial=?, unome=?, datanasc=?, sexo=?, "
                    + "salario=?, dnr=?, endereco=?"
                    + " WHERE cpf = ?; ";

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getMiddleInitial());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getBirhtDate().toString());
            stmt.setString(5, String.valueOf(employee.getSex()));
            stmt.setDouble(6, employee.getSalary());
            stmt.setInt(7, employee.getDepartment().getDepartmentId());
            stmt.setString(8, employee.getAddress());
            stmt.setString(9, employee.getCpf());
           
            
            //STEP 3: Execute the query statement
            stmt.executeUpdate(sql);
            
            updatedEmployee = retrieveEmployee(employee.getCpf());
            
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
        
        return updatedEmployee;
    }    
    
    
    public boolean removeEmployee(Employee employee) throws SQLException {
        
        boolean removed = false;
        
        Connection connection = null;
        Statement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();
            connection.setAutoCommit(false);

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            
            String sql;
            sql = "DELETE FROM "
                    + "funcionario "
                    + " WHERE "
                    + " cpf = '" + employee.getCpf() + "';";

            stmt = connection.createStatement();
             
            //STEP 3: Execute the query statement
            int result = stmt.executeUpdate(sql);
            
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

    ArrayList<Employee> retrieveAllEmployeesByDepartment(int departmentId) {
        ArrayList<Employee> employeeList = new ArrayList<>();
        Employee employee = null;
        Connection connection = null;
        Statement stmt = null;
        
        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "SELECT cpf, pnome, minicial, unome, datanasc, sexo, salario,"
                    + " dnr, endereco"
                    + "  FROM funcionario"
                    + " WHERE dnr = " + Integer.toString(departmentId) + ";";

            //STEP 3: Execute the query statement
            ResultSet rs = stmt.executeQuery(sql);
            
            
            //STEP 4: Extract data from result set
            while (rs.next()) {

                //Retrieve by column name
                String cpf = rs.getString("cpf");
                String retFirstName = rs.getString("pnome");
                String middleInicial = rs.getString("minicial");
                String lastName = rs.getString("unome");
                Date birthDate = rs.getDate("datanasc");
                char sex;
                String temp = rs.getString("sexo");
                if (rs.wasNull()){
                    sex = ' ';
                } else {
                    sex = temp.charAt(0);
                }
                
                double salary = rs.getDouble("salario");
                String address = rs.getString("endereco");

                //create the employee object
                employee = new Employee(cpf,
                        retFirstName,
                        middleInicial,
                        lastName,
                        birthDate,
                        address,
                        salary,
                        sex,
                        new Department(departmentId)
                );

                // Add employee to list
                employeeList.add(employee);
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            connection.close();

        }catch(SQLException se){
              //Handle errors for JDBC
            se.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(
                            EmployeeModel.class.getName()).log(
                                    Level.SEVERE, null, ex);
                }
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        
        
        return employeeList;
    }
    
    
/* Create a Stored Procedure */
    
    public boolean createEmployeeNameProcedure() throws SQLException {

        Connection connection = null;
        Statement stmt = null;
        boolean rs = false;

        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            //System.out.println("Creating statement...");
            stmt = connection.createStatement();
            String sql;
            sql = "CREATE OR REPLACE PROCEDURE getEmployeeName \n"
                    + "   (IN in_cpf VARCHAR(11), OUT emp_nome text)\n"
                    + "BEGIN  ATOMIC\n"
                    + "   SELECT CAST(pnome || ' ' || unome  AS text) "
                    + "      AS emp_nome\n"
                    + "   FROM funcionario\n"
                    + "   WHERE cpf = in_cpf;\n"
                    + "END;";

            //STEP 3: Execute the Stored Procedure query statement
            rs = stmt.execute(sql);

            stmt.close();
            connection.close();

        } catch (SQLException se) {
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

        return rs;
    }
     
     
     
   
    
/* Call a Stored Procedure */    
    
    public boolean callEmployeeNameProcedure(String cpf) throws SQLException {

        Connection connection = null;
        CallableStatement stmt = null;
        boolean rs = false;

        try {
            // STEP 1:  Connect to the database
            connection = CompanyDBConnection.getConnection();

            // STEP 2: Prepare a query statement
            System.out.println("Creating statement...");
            
            String sql;
            sql = "{call getEmployeeName(?, ?) }";

            stmt = connection.prepareCall(sql);
            
            stmt.setString(1, cpf);
            stmt.registerOutParameter(2, VARCHAR);
            
            
            //STEP 3: Execute the Stored Procedure query statement
            System.out.println("Executing stored procedure..." );
            rs  = stmt.execute();
            
            //Retrieve employee name with getXXX method
            String empName = stmt.getString(2);
            System.out.println("Emp Name with ID: " + cpf + " is " + empName);

            stmt.close();
            connection.close();

        } catch (SQLException se) {
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

        return rs;
    }
    
    
}
