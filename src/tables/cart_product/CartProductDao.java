package tables.cart_product;

import database.DbConnection;
import tables.Dao;
import tables.product.Product;
import tables.product.ProductDao;
import tables.shoppingcart.ShoppingCart;
import tables.shoppingcart.ShoppingCartDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartProductDao implements Dao {
    private final ShoppingCartDao sc = new ShoppingCartDao();
    private final ProductDao pd = new ProductDao();
    @Override
    public void insert(Object obj) throws SQLException {
        CartProduct cp = (CartProduct) obj;
        String query = "INSERT INTO CARTPRODUCT (qntProduct, shoppingCart_id, product_id) values (?, ?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, cp.getQntProduct());
            conn.setInt(2, cp.getShoppingCartId().getId());
            for(Product p : cp.getProductId()){
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
        CartProduct cp = (CartProduct) obj;
        String query = "UPDATE CARTPRODUCT SET qntProduct=? where product_id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setInt(1, cp.getQntProduct());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM CARTPRODUCT WHERE shoppingcart_id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Object select(Integer i) throws SQLException {
        CartProduct cp = new CartProduct();
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM CARTPRODUCT WHERE shoppingcart_id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                cp.setShoppingCartId((ShoppingCart) sc.select(rs.getInt("shoppingcart_id")));
                cp.setQntProduct(rs.getInt("qntProduct"));
                list.add((Product) pd.select(rs.getInt("product_id")));
            }
            cp.setProductId(list);
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return cp;
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> listAll = new ArrayList<>();
        CartProduct cp = null;
        String query = "SELECT * FROM CARTPRODUCT";
        try(Statement conn = DbConnection.getConexao().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = conn.executeQuery(query);
            while(rs.next()){
                cp = new CartProduct();
                List<Product> list = new ArrayList<>();
                cp.setShoppingCartId((ShoppingCart) sc.select(rs.getInt("shoppingcart_id")));
                cp.setQntProduct(rs.getInt("qntProduct"));
                list.add((Product) pd.select(rs.getInt("product_id")));
                while (rs.next() && rs.getInt("shoppingcart_id") == cp.getShoppingCartId().getId()){
                    list.add((Product) pd.select(rs.getInt("product_id")));
                }
                cp.setProductId(list);
                listAll.add(cp);
                rs.previous();
            }
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return listAll;
    }
}
