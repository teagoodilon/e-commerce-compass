package main;

import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import tables.product.Product;
import tables.product.ProductDao;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //CreateTables.main();
        //Product p1 = new Product();
        //p1.setName("Arroz");
        // p1.setPrice(15.99);
        //p1.setQuantity(10);

        Costumer p1 = new Costumer();
        p1.setName("Teago Odilon");
        p1.setEmail("thiago-almeida05@hotmail.com");


        try{
            CostumerDao p1Dao = new CostumerDao();
            System.out.println(p1Dao.update(p1, 1));
        }catch(SQLException e){
            throw new SQLException(e.getMessage());
        }

    }
}

