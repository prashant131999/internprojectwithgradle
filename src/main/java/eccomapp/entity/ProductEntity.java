package eccomapp.entity;

import java.util.UUID;

public class ProductEntity {
    UUID prodid = UUID.randomUUID();
    private int quantity;
    private String prodName, prodType, prodDescription;
    private float totalCost,cost;
    public ProductEntity(int quantity,String prodName,float cost)
    {
        this.quantity=quantity;
        this.prodName=prodName;
        this.cost=cost;
    }
    public ProductEntity ()
     {
     }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProdDescription(String prodDedcription) {
        this.prodDescription = prodDescription;
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

    public String getProdDescription() {
        return prodDescription;
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
