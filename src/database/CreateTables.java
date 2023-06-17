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
    private static final String SHOPPINGCARTTABLE = "CREATE TABLE shoppingCart " +
            "(ID SERIAL PRIMARY KEY," +
            " COSTUMER_ID INTEGER UNIQUE, " +
            " TOTALVALUE FLOAT," +
            " FOREIGN KEY (COSTUMER_ID) REFERENCES costumer (id))";

    private static final String CARTPRODUCTTABLE = "CREATE TABLE cartproduct " +
            "(SHOPPINGCART_ID INTEGER," +
            " PRODUCT_ID INTEGER, " +
            " QNTPRODUCT INTEGER, " +
            " PRODUCTSVALUE FLOAT, " +
            " FOREIGN KEY (SHOPPINGCART_ID) REFERENCES shoppingcart (id), " +
            " FOREIGN KEY (PRODUCT_ID) REFERENCES product (id))";
    private static final String ORDERTABLE = "CREATE TABLE orders " +
            "(ID SERIAL," +
            " SHOPPINGCART_ID INTEGER, " +
            " CONFIRMED BOOLEAN, " +
            " FOREIGN KEY (SHOPPINGCART_ID) REFERENCES shoppingCart (id))";

    public static Boolean start() throws SQLException {
        CreateTables createTable = new CreateTables();
        return createTable.createDb();
    }

    private Boolean createDb() throws SQLException {
        try (Connection connection = DbConnection.getConexao();
             Statement statement = connection.createStatement();) {
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet resultSet = metaData.getTables(null, null, "product", null);
                if(!resultSet.next()){
                    statement.execute(PRODUCTTABLE);
                    statement.execute(COSTUMERTABLE);
                    statement.execute(SHOPPINGCARTTABLE);
                    statement.execute(CARTPRODUCTTABLE);
                    statement.execute(ORDERTABLE);
                    return false;
                } else {
                    return true;
                }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
