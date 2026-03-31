package com.example.cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/dbcrm";
    private static final String USER = "postgres";
    private static final String PASS = "17011998";

    public static Connection getConnection(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        }catch(ClassNotFoundException | SQLException e){
            throw new RuntimeException("Erro ao conectar" + e.getMessage());
        }
    }
}
