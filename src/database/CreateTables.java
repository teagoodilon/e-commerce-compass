package database;

import java.sql.*;

public class CreateTables {

    private static final String PRODUCTTABLE = "CREATE TABLE product " +
            "(ID SERIAL PRIMARY KEY ," +
            " NAME VARCHAR(50), " +
            " PRICE FLOAT, " +
            " QUANTITY INTEGER)";
    private static final String COSTUMERTABLE = "CREATE TABLE costumer " +
            "(ID SERIAL PRIMARY KEY ," +
            " NAME VARCHAR(50), " +
            " EMAIL VARCHAR(50))";
    private static final String ORDERTABLE = "CREATE TABLE orders " +
            "(ID SERIAL," +
            " COSTUMER INTEGER, " +
            " PRODUCTS INTEGER, " +
            " CONFIRMED BOOLEAN, " +
            " FOREIGN KEY (COSTUMER) REFERENCES costumer (id), " +
            " FOREIGN KEY (PRODUCTS) REFERENCES product (id)) ";
    private static final String SHOPPINGCARTTABLE = "CREATE TABLE shoppingCart " +
            "(ID SERIAL," +
            " COSTUMER INTEGER, " +
            " PRODUCTS INTEGER, " +
            " FOREIGN KEY (COSTUMER) REFERENCES costumer (id), " +
            " FOREIGN KEY (PRODUCTS) REFERENCES product (id)) ";

    public static void main(String[] args) throws SQLException {
        CreateTables createTable = new CreateTables();
        createTable.createTable();
    }

    private void createTable() throws SQLException {
        try (Connection connection = DbConnection.getConexao();
             Statement statement = connection.createStatement();) {
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet resultSet = metaData.getTables(null, null, "product", null);
                if(!resultSet.next()){
                    statement.execute(PRODUCTTABLE);
                    statement.execute(COSTUMERTABLE);
                    statement.execute(ORDERTABLE);
                    statement.execute(SHOPPINGCARTTABLE);
                    System.out.println("Tabelas criadas com sucesso!");
                } else {
                    System.out.println("Todas as tabelas já foram criadas!");
                }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
