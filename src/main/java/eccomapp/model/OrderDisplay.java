package eccomapp.model;

import java.util.UUID;

public class OrderDisplay {
    String prodName;
    float totalCost;
    UUID customerId;
    UUID orderId;

    public OrderDisplay(String prodName, float totalCost, UUID customerId, UUID orderId) {
        this.prodName = prodName;
        this.totalCost = totalCost;
        this.customerId = customerId;
        this.orderId = orderId;

    }

    public String getProdName() {
        return prodName;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public OrderDisplay()
    {
    }
}
