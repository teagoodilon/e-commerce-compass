package tables.shoppingcart;

import tables.costumer.Costumer;

public class ShoppingCart {
    private Integer id;
    private Costumer costumerId;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public Costumer getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Costumer costumerId) {
        this.costumerId = costumerId;
    }
}
