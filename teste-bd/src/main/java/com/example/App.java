package com.example;

import java.sql.*;
/* import java.sql.DriverManager;
import java.sql.SQLException; */

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ){
        try {
            Connection conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/empresa", "postgres", "");
            if(conexao != null){
                System.out.println("Banco conectado com sucesso!");
            }else{
                System.out.println("Conexao falhou...");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

