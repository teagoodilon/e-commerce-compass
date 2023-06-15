package main;

import database.CreateTables;
import tables.costumer.Costumer;
import tables.costumer.CostumerDao;
import tables.order.Order;
import tables.order.OrderDao;
import tables.product.Product;
import tables.product.ProductDao;
import tables.shoppingcart.ShoppingCart;
import tables.shoppingcart.ShoppingCartDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        /*Costumer p1 = new Costumer();
        p1.setId(1);
        p1.setName("Teago Odilon");
        p1.setEmail("thiago-almeida05@hotmail.com");

        Product p2 = new Product();
        p2.setId(1);
        p2.setName("Manteiga");
        p2.setPrice(10.00);
        p2.setQuantity(10);

        Product p3 = new Product();
        p3.setId(2);
        p3.setName("Arroz");
        p3.setPrice(20.00);
        p3.setQuantity(25);


        List<Product> list = new ArrayList<>();
        list.add(p2);
        list.add(p3);

        ShoppingCart sc1 = new ShoppingCart();
        sc1.setId(2);
        sc1.setCostumer(p1);
        sc1.setProducts(list);

        ShoppingCartDao scDao = new ShoppingCartDao();
        ShoppingCart sc  = (ShoppingCart) scDao.select(1);
        System.out.println(sc.getNumShoppingCart());
        System.out.println(sc.getProducts());*/

    }
}

