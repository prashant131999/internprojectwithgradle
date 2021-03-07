package eccomapp.model;

import java.util.UUID;

public class ProductModel {
    String prodName;
    int quantity;
    float totalcost;
    UUID prodId;

    public ProductModel(String prodName, int quantity, float totalcost, UUID prodId) {
        this.prodName = prodName;
        this.quantity = quantity;
        this.totalcost = totalcost;
        this.prodId = prodId;
    }
    public ProductModel()
    {

    }

    public String getProdName() {
        return prodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalcost() {
        return totalcost;
    }

    public UUID getProdId() {
        return prodId;
    }
}
