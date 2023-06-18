package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    static String url ="jdbc:postgresql://localhost:5432/postgres" ;
    static String user = "postgres";
    static String password;
    static Connection conn;

    private DbConnection() {
        throw new IllegalStateException("Utility class");
    }

    public static void setPassword(String password) {
        DbConnection.password = password;
    }

    public static Connection getConexao() throws SQLException{
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    public static Connection makeConection(String p) throws SQLException{
        try {
            setPassword(p);
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão ao banco de dados feita com sucesso! ");
            return conn;

        } catch (SQLException e) {
            throw new SQLException("Senha incorreta");
        }
    }
}