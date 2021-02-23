package eccomapp.entity;

import java.util.UUID;

public class OrderEntity {
    private String listOfProduct,dateCreated,dateDelivered,quantitylist;
    private int totalCost;
    UUID orderid=UUID.randomUUID();
    public OrderEntity()
    {

    }
//
//    public OrderEntity(String listOfProduct,String dateCreated,String dateDelivered,int totalCost
//                      ,int orderid,String quantitylist)
//    {
//        this.listOfProduct=listOfProduct;
//        this.dateCreated=dateCreated;
//        this.dateDelivered=dateDelivered;
//        this.totalCost=totalCost;
//        this.orderid=orderid;
//        this.quantitylist=quantitylist;
//
//    }

    public UUID getOrderid() {
        return orderid;
    }

//    public void setDateCreated(String dateCreated) {
//        this.dateCreated = dateCreated;
//    }

//    public void setDateDelivered(String dateDelivered) {
//        this.dateDelivered = dateDelivered;
//    }

    public void setListOfProduct(String listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public void setQuantity(String quantitylist) {
        this.quantitylist = quantitylist;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
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
    public String getListOfProduct()
    {
        return listOfProduct;
    }
    public String getDateCreated()
    {
        return dateCreated;
    }
    public String getDateDelivered()
    {
        return dateDelivered;
    }
}
