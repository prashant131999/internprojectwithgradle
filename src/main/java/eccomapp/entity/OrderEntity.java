package eccomapp.entity;

import java.util.UUID;

public class OrderEntity {
    private String listOfProduct, dateCreated, dateDelivered, quantitylist;
    private int totalCost;
    UUID orderid = UUID.randomUUID();

    public OrderEntity() {

    }

    public UUID getOrderid() {
        return orderid;
    }


    public void setListOfProduct(String listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public void setQuantity(String quantitylist) {
        this.quantitylist = quantitylist;
    }


    public void setQuantitylist(String quantitylist) {
        this.quantitylist = quantitylist;
    }

    public void setOrderid(UUID orderid) {
        this.orderid = orderid;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public String getListOfProduct() {
        return listOfProduct;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateDelivered() {
        return dateDelivered;
    }
}
