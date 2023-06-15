package tables.product;

import database.DbConnection;
import tables.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao {
    @Override
    public void insert(Object obj) throws SQLException {
        Product p = (Product) obj;
        String query = "INSERT INTO PRODUCT (name, price, quantity) values (?, ?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setString(1, p.getName());
            conn.setDouble(2, p.getPrice());
            conn.setInt(3, p.getQuantity());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean update(Object obj, Integer i) throws SQLException {
        Product p = (Product) obj;
        String query = "UPDATE PRODUCT SET name=?, price=?, quantity=? where id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setString(1, p.getName());
            conn.setDouble(2, p.getPrice());
            conn.setInt(3, p.getQuantity());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM PRODUCT WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Object select(Integer i) throws SQLException {
        Product p = new Product();
        String query = "SELECT * FROM PRODUCT WHERE id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return p;
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> list = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                list.add(p);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return list;
    }
}
