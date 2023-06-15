package tables.shoppingcart;

import database.DbConnection;
import tables.Dao;
import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao implements Dao {
    private final CostumerDao cd = new CostumerDao();
    @Override
    public void insert(Object obj) throws SQLException {
        ShoppingCart sc = (ShoppingCart) obj;
        String query = "INSERT INTO SHOPPINGCART (costumer_id) values (?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, sc.getCostumerId().getId());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean update(Object obj, Integer i) throws SQLException {
        ShoppingCart sc = (ShoppingCart) obj;
        String query = "UPDATE SHOPPINGCART SET costumer_id=? where id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, sc.getCostumerId().getId());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM SHOPPINGCART WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Object select(Integer i) throws SQLException {
        ShoppingCart sc = new ShoppingCart();
        String query = "SELECT * FROM SHOPPINGCART WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                sc.setId(rs.getInt("id"));
                sc.setCostumerId((Costumer) cd.select(rs.getInt("costumer_id")));
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return sc;
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> list = new ArrayList<>();
        String query = "SELECT * FROM SHOPPINGCART";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                ShoppingCart sc = new ShoppingCart();
                sc.setId(rs.getInt("id"));
                sc.setCostumerId((Costumer) cd.select(rs.getInt("costumer_id")));
                list.add(sc);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return list;
    }
}