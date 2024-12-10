package com.edutecno.procesaconexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Configuración de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/horoscopo_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Metodo para obtener conexión
    public Connection getConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al cargar el driver MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al establecer conexión: " + e.getMessage());
        }
        return null;
    }

    // Metodo para cerrar conexión
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

