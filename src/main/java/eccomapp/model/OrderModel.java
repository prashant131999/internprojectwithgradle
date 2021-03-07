package eccomapp.model;

public class OrderModel {
    private String name,email;
  //  private UUID orderid;
    public OrderModel(String name,String email)
    {

        this.email=email;
        this.name=name;
    }
    public OrderModel()
    {

    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public void setOrderid(UUID orderid) {
//        this.orderid=orderid;
//    }
}
