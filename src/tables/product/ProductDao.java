package tables.product;

import database.DbConnection;
import tables.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao {

    static final String PCSTRING = "price";
    static final String QNSTRING = "quantity";
    @Override
    public Boolean insert(Object obj) throws SQLException {
        Product p = (Product) obj;
        String query = "INSERT INTO PRODUCT (name, price, quantity) values (?, ?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setString(1, p.getName());
            conn.setDouble(2, p.getPrice());
            conn.setInt(3, p.getQuantity());
            conn.execute();
            return true;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Boolean update(Object obj, Integer i) throws SQLException {
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
    public Boolean delete(Integer i) throws SQLException {
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
            if(rs.next()){
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble(PCSTRING));
                p.setQuantity(rs.getInt(QNSTRING));
            } else {
                System.out.println("Não existe produto com esse id");
                return null;
            }
        } catch (SQLException e){
            System.out.println("Não existe produto com esse id");
        }
        if(p.getId() == i){
            return p;
        } else {
            return null;
        }
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
                p.setPrice(rs.getDouble(PCSTRING));
                p.setQuantity(rs.getInt(QNSTRING));
                list.add(p);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return list;
    }
    public List<Product> selectAvaible() throws SQLException {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT WHERE QUANTITY > 0";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble(PCSTRING));
                p.setQuantity(rs.getInt(QNSTRING));
                list.add(p);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return list;
    }

}
