package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    static String url ="jdbc:postgresql://localhost:5432/db-e-commerce" ;
    static String user = "postgres";
    static String password = "1984";
    static Connection conn;
    public static Connection getConexao() throws SQLException{
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão com o Banco de dados realizada com sucesso");
            return conn;

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}