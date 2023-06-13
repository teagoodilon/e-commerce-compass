package main;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DbConnection conn = new DbConnection();
        System.out.println(conn.getConexao());
    }
}

