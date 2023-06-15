package tables.shoppingcart;

import database.DbConnection;
import tables.Dao;
import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import tables.product.Product;
import tables.product.ProductDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDao implements Dao {
    private final CostumerDao cd = new CostumerDao();
    private final ProductDao pd = new ProductDao();
    private final List<Product> list = new ArrayList<>();
    @Override
    public void insert(Object obj) throws SQLException {
        ShoppingCart sc = (ShoppingCart) obj;
        String query = "INSERT INTO SHOPPINGCART (numShoppingCart, costumer, products) values (?, ?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, sc.getId());
            conn.setInt(2, sc.getCostumer().getId());
            for (Product p : sc.getProducts()){
                conn.setInt(3, p.getId());
                conn.addBatch();
            }
            conn.executeBatch();

        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public boolean update(Object obj, Integer i) throws SQLException {
        ShoppingCart sc = (ShoppingCart) obj;
        String query = "UPDATE SHOPPINGCART SET costumer=?, products=? where numShoppingCart=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, sc.getCostumer().getId());
            for (Product p : sc.getProducts()){
                conn.setInt(2, p.getId());
                conn.addBatch();
            }
            conn.executeBatch();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM SHOPPINGCART WHERE NUMSHOPPINGCART=" + i;
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
        list.clear();
        String query = "SELECT * FROM SHOPPINGCART WHERE NUMSHOPPINGCART=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                sc.setNumShoppingCart(rs.getInt("numShoppingCart"));
                sc.setCostumer((Costumer) cd.select(rs.getInt("costumer")));
                list.add((Product) pd.select(rs.getInt("products")));
            }
            sc.setProducts(list);
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return sc;
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> listAll = new ArrayList<>();
        list.clear();
        ShoppingCart sc = null;
        String query = "SELECT * FROM SHOPPINGCART";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                sc = new ShoppingCart();
                sc.setId(rs.getInt("id"));
                sc.setNumShoppingCart(rs.getInt("numShoppingCart"));
                sc.setCostumer((Costumer) cd.select(rs.getInt("costumer")));
                list.add((Product) pd.select(rs.getInt("products")));
                while (rs.next() && rs.getInt("numShoppingCart") == sc.getNumShoppingCart()){
                    list.add((Product) pd.select(rs.getInt("products")));
                }
                sc.setProducts(list);
                listAll.add(sc);
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return listAll;
    }
}
