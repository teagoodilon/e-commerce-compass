package main;

import tables.product.Product;
import tables.product.ProductDao;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //CreateTables.main();
        Product p1 = new Product();
        p1.setName("Arroz");
        p1.setPrice(15.99);
        p1.setQuantity(10);

        try{
            ProductDao p1Dao = new ProductDao();
            System.out.println(p1Dao.select());
        }catch(SQLException e){
            throw new SQLException(e.getMessage());
        }


    }
}

