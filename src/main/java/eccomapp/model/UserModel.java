package eccomapp.model;

public class UserModel {

    String email,mobileNo,address;
    public UserModel(String email, String address)
    {
        this.email=email;
        this.address=address;
    }
    public UserModel()
    {
    }
    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }


}
