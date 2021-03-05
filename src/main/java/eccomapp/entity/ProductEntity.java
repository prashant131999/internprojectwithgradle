package eccomapp.entity;

import java.util.UUID;

public class ProductEntity {
    UUID prodid = UUID.randomUUID();
    private int quantity;
    private String prodName, prodType;
    private float totalCost,cost;
    public ProductEntity(int quantity,String prodName,float totalCost)
    {
        this.quantity=quantity;
        this.prodName=prodName;
        this.totalCost=totalCost;
    }
    public ProductEntity ()
     {
     }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public UUID getProdid() {
        return prodid;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProductid(UUID prodid) {
        this.prodid=prodid;
    }

    public float getCost() {
        return cost;
    }
}
