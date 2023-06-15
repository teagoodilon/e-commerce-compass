package tables.order;

import database.DbConnection;
import tables.Dao;
import tables.costumer.Costumer;
import tables.product.Product;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDao implements Dao {
    @Override
    public void insert(Object obj) throws SQLException {

    }
    @Override
    public boolean update(Object obj, Integer i) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer i) throws SQLException {
        return false;
    }

    @Override
    public Object select(Integer i) throws SQLException {
        return null;
    }

    @Override
    public List<Object> select() throws SQLException {
        return null;
    }
}
