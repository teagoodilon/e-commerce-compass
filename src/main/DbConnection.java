package main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    public void connect() {
        String url = "jdbc:postgresql://localhost:5432/db-e-commerce";
        try {
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            props.setProperty("password", "1984");
            DriverManager.getConnection(url, props);
            System.out.println("Conexão com o Banco de dados realizada com sucesso");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}