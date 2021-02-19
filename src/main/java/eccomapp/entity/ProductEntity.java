package eccomapp.entity;

public class ProductEntity {
    private int productId,quantity;
    private String prodName,prodType,prodDedcription;
    private float  totalcost;

    public ProductEntity(int quantity,float totalcost,String prodName,String prodType,String prodDedcription)
    {
        this.quantity=quantity;
        this.totalcost=totalcost;
        this.prodName=prodName;
        this.prodType=prodType;
        this.prodDedcription=prodDedcription;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProdDedcription(String prodDedcription) {
        this.prodDedcription = prodDedcription;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public void setTotalcost(float totalcost) {
        this.totalcost = totalcost;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalcost() {
        return totalcost;
    }
}
