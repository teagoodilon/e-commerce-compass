package main;


import tables.cart_product.CartProduct;

import tables.cart_product.CartProductDao;
import tables.product.Product;
import tables.product.ProductDao;
import tables.shoppingcart.ShoppingCart;
import tables.shoppingcart.ShoppingCartDao;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws SQLException {



        /*ProductDao p = new ProductDao();
        ShoppingCartDao scDao = new ShoppingCartDao();
        CartProduct cp = new CartProduct();
        List<Product> cover = new ArrayList<>();
        cover.add((Product)p.select(2));
        cp.setProductId(cover);
        cp.setQntProduct(35);
        cp.setShoppingCartId((ShoppingCart) scDao.select(3));
        CartProductDao c = new CartProductDao();
        for(Object a : c.select()){
            if(a instanceof CartProduct cpa){
                System.out.println(cpa.getProductId());
                System.out.println(cpa.getShoppingCartId().getId());
            }
        }*/






    }
}

