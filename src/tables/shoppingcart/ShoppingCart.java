package tables.shoppingcart;

import tables.costumer.Costumer;
import tables.product.Product;

import java.util.List;

public class ShoppingCart {
    private int id;
    private Costumer costumer;
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
