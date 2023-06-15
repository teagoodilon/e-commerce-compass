package tables.order;


public class Order {
    private Integer id;
    private Integer numShoppingCart;
    private Boolean confirmed;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNumShoppingCart() { return numShoppingCart; }

    public void setNumShoppingCart(Integer numShoppingCart) { this.numShoppingCart = numShoppingCart; }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }
}
