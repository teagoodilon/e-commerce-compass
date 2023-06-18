package tables.shoppingcart;

import tables.costumer.Costumer;

public class ShoppingCart {
    private Integer id;
    private Costumer costumerId;
    private Float totalValue;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public ShoppingCart(){
        this.totalValue = 0.0F;
    }

    public Costumer getCostumerId() {
        return costumerId;
    }

    public void setCostumerId(Costumer costumerId) {
        this.costumerId = costumerId;
    }

    public Float getTotalValue() {
        return totalValue;
    }
    public void setTotalValue(Float fl) {
        this.totalValue = fl;
    }

    public void changeTotalValue(Float value) {
        this.totalValue = this.totalValue + value;
    }
}
