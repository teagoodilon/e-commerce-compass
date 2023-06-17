package tables.order;

import database.DbConnection;
import tables.Dao;
import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import tables.product.Product;
import tables.shoppingcart.ShoppingCart;
import tables.shoppingcart.ShoppingCartDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements Dao {
    private final ShoppingCartDao sc = new ShoppingCartDao();
    @Override
    public Boolean insert(Object obj) throws SQLException {
        Order o = (Order ) obj;
        String query = "INSERT INTO ORDERS (shoppingcart_id, confirmed) values (?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, o.getShoppingCartId().getId());
            conn.setBoolean(2, o.isConfirmed());
            conn.execute();
            return true;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }
    @Override
    public Boolean update(Object obj, Integer i) throws SQLException {
        Order o = (Order ) obj;
        String query = "UPDATE ORDERS SET shoppingcart_id=?, confirmed=? where id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, o.getShoppingCartId().getId());
            conn.setBoolean(2, o.isConfirmed());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM ORDERS WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Object select(Integer i) throws SQLException {
        Order o = new Order();
        String query = "SELECT * FROM ORDERS WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                o.setShoppingCartId((ShoppingCart) sc.select(rs.getInt("shoppingcart_id")));
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return o;
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> list = new ArrayList<>();
        String query = "SELECT * FROM ORDERS";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setShoppingCartId((ShoppingCart) sc.select(rs.getInt("shoppingcart_id")));
                list.add(o);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return list;
    }
}
