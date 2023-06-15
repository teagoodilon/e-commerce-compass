package tables.order;


import tables.shoppingcart.ShoppingCart;

public class Order {
    private Integer id;
    private ShoppingCart shoppingCartId;
    private Boolean confirmed;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(ShoppingCart shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
