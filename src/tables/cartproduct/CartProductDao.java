package tables.cartproduct;

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
    static final String SCSTRING = "shoppingcart_id";
    static final String QNTSTRING = "qntProduct";
    static final String PVSTRING = "productsValue";
    static final String PSTRING = "product_id";

    @Override
    public Boolean insert(Object obj) throws SQLException {
        CartProduct cp = (CartProduct) obj;
        List<Integer> qntProduct = cp.getQntProduct();
        List<Product> products = cp.getProductId();
        String query = "INSERT INTO CARTPRODUCT (productsValue, shoppingCart_id, qntProduct, product_id) values (?, ?, ?, ?)";
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            conn.setFloat(1, cp.getProductsValue());
            conn.setInt(2, cp.getShoppingCartId().getId());
            for (int i = 0; i < products.size(); i++) {
                conn.setInt(3, qntProduct.get(i));
                conn.setInt(4, products.get(i).getId());
                conn.addBatch();
            }
            conn.executeBatch();
            qntProduct.clear();
            products.clear();
            return true;
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Boolean update(Object obj, Integer i) throws SQLException {
        CartProduct cp = (CartProduct) obj;
        String query = "UPDATE CARTPRODUCT SET qntProduct=?, productsValue=? where product_id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            for(Integer integer : cp.getQntProduct()){
                conn.setInt(1, integer);
            }
            conn.setFloat(2, cp.getProductsValue());
            conn.execute();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return false;
    }

    @Override
    public Boolean delete(Integer i) throws SQLException {
        String query = "DELETE FROM CARTPRODUCT WHERE product_id=" + i;
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
        List<Integer> qntList = new ArrayList<>();
        String query = "SELECT * FROM CARTPRODUCT WHERE shoppingcart_id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            while(rs.next()){
                cp.setShoppingCartId((ShoppingCart) sc.select(rs.getInt(SCSTRING)));
                qntList.add(rs.getInt(QNTSTRING));
                cp.setProductsValue(rs.getFloat(PVSTRING));
                list.add((Product) pd.select(rs.getInt(PSTRING)));
            }
            cp.setQntProduct(qntList);
            cp.setProductId(list);
        }catch (SQLException e){
            System.out.println("Não há nenhum produto nesse carinho");
            return null;
        }
        return cp;
    }

    public Boolean selectProduct(Integer i) throws SQLException {
        String query = "SELECT * FROM CARTPRODUCT WHERE product_id=" + i;
        try(PreparedStatement conn = DbConnection.getConexao().prepareStatement(query)){
            ResultSet rs = conn.executeQuery();
            boolean set = rs.next();
            if(Boolean.TRUE.equals(set)){
               return true;
            } else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Não há nenhum produto nesse carinho");

        }
        return false;
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> listAll = new ArrayList<>();
        List<Integer> qntList = new ArrayList<>();
        CartProduct cp;
        String query = "SELECT * FROM CARTPRODUCT";
        try(Statement conn = DbConnection.getConexao().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = conn.executeQuery(query);
            while(rs.next()){
                float value = 0.0F;
                cp = new CartProduct();
                List<Product> list = new ArrayList<>();
                cp.setShoppingCartId((ShoppingCart) sc.selectSc(rs.getInt(SCSTRING)));
                qntList.add(rs.getInt(QNTSTRING));
                list.add((Product) pd.select(rs.getInt(PSTRING)));
                value += rs.getFloat(PVSTRING);
                while (rs.next() && rs.getInt(SCSTRING) == cp.getShoppingCartId().getId()){
                    value += rs.getFloat(PVSTRING);
                    list.add((Product) pd.select(rs.getInt(PSTRING)));
                    qntList.add(rs.getInt(QNTSTRING));
                }
                cp.setProductsValue(value);
                cp.setQntProduct(qntList);
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
