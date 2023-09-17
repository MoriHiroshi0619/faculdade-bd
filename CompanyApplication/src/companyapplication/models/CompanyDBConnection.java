/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package companyapplication.models;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author valga
 */
public class CompanyDBConnection {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String dbServerURL = "127.0.0.1";
    private static final String dbServerPort = "5432";
    private static final String dbName = "";
    private static final String username = "postgres";
    private static final String password = "";
        
    public static Connection getConnection() {
        //System.out.println("-------- PostgreSQL "
        //                    + "JDBC Connection Testing ------------");

        try {
                Class.forName(DRIVER);

        } catch (ClassNotFoundException e) {

                System.out.println("Where is your PostgreSQL JDBC Driver? "
                                + "Include in your library path!");
                return null;

        }

        //System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {
                String stringConnection = "jdbc:postgresql://" +
                        dbServerURL + ":" +
                        dbServerPort + "/" +
                        dbName;
                        
                connection = DriverManager.getConnection(stringConnection, 
                        username , password);

        } catch (SQLException e) {

                System.out.println("Connection Failed! Check output console");
                System.out.println(e.getMessage());
                return null;

        }

        /*if (connection != null) {
                System.out.println("You made it, take control your database now!");
        } else {
                System.out.println("Failed to make connection!");
        }*/

        return connection;
    }


}
