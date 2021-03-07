package eccomapp.model;

import java.util.UUID;

public class OrderDisplay {
    String prodName;
    float totalCost;
    UUID customerId,OrderId;

    public OrderDisplay(String prodName, float totalCost, UUID customerId, UUID orderId) {
        this.prodName = prodName;
        this.totalCost = totalCost;
        this.customerId = customerId;
        OrderId = orderId;

    }
    public OrderDisplay()
    {

    }
}
