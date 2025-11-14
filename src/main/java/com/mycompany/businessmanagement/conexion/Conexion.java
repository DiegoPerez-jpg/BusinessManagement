package com.mycompany.businessmanagement.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Conexion {
private Connection conexion;
    private static Conexion conexionObject;
    private static final String URL = "jdbc:mysql://192.168.204.109:3306/businessmanagement";
    private static final String USER = "usuario";
    private static final String PASSWORD = "";

    private Conexion(String URL, String USER, String PASSWORD){
        try {
            // 1. Cargar el driver (opcional en Java 8+)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establecer la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. Verificar la conexión
            if (conexion != null) {
                System.out.println("✅ Conexión establecida correctamente");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ No se encontró el driver JDBC: " + e.getMessage());
        }
    }



    public static Connection getConnection(){
        if(conexionObject == null){
            conexionObject = new Conexion(URL,USER,PASSWORD);
        }
        return conexionObject.conexion;
    }
}