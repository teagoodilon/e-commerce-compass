package tables.cart_product;

import tables.product.Product;
import tables.shoppingcart.ShoppingCart;

import java.util.List;

public class CartProduct {
    private List<Product> productId;
    private List<Integer> qntProduct;
    private ShoppingCart shoppingCartId;
    private Float productsValue;

    public List<Product> getProductId() {
        return productId;
    }

    public void setProductId(List<Product> productId) {
        this.productId = productId;
    }

    public List<Integer> getQntProduct() {
        return qntProduct;
    }

    public void setQntProduct(List<Integer> qntProduct) {
        this.qntProduct = qntProduct;
    }

    public ShoppingCart getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(ShoppingCart shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Float getProductsValue() {
        return productsValue;
    }

    public void setProductsValue(Float productsValue) {
        this.productsValue = productsValue;
    }
}
