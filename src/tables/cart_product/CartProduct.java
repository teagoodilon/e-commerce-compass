package tables.cart_product;

import tables.product.Product;
import tables.shoppingcart.ShoppingCart;

import java.util.List;

public class CartProduct {
    private List<Product> productId;
    private Integer qntProduct;
    private ShoppingCart shoppingCartId;

    public List<Product> getProductId() {
        return productId;
    }

    public void setProductId(List<Product> productId) {
        this.productId = productId;
    }

    public Integer getQntProduct() {
        return qntProduct;
    }

    public void setQntProduct(Integer qntProduct) {
        this.qntProduct = qntProduct;
    }

    public ShoppingCart getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(ShoppingCart shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }
}
