package eccomapp.entity;

public class OrderEntity {
    private String listOfProduct,dateCreated,dateDelivered,quantitylist;
    private int totalCost,orderid;

    public OrderEntity(String listOfProduct,String dateCreated,String dateDelivered,int totalCost
                      ,int orderid,String quantitylist)
    {
        this.listOfProduct=listOfProduct;
        this.dateCreated=dateCreated;
        this.dateDelivered=dateDelivered;
        this.totalCost=totalCost;
        this.orderid=orderid;
        this.quantitylist=quantitylist;

    }
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateDelivered(String dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public void setListOfProduct(String listOfProduct) {
        this.listOfProduct = listOfProduct;
    }

    public void setQuantity(String quantitylist) {
        this.quantitylist = quantitylist;
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
