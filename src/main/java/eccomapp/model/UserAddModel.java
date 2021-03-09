package eccomapp.model;

import java.util.UUID;

public class UserAddModel {
    private String dateOfBirth, email, address,fname, mobileNumber,lname;
    UUID userid ;
    public UserAddModel(String dateOfBirth, String email, String address, String fname, String mobileNumber, String lname) {
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.fname = fname;
        this.mobileNumber = mobileNumber;
        this.lname = lname;
    }
    public UserAddModel()
    {
    }

    public void setUserid(UUID userid) {
        this.userid = userid;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getFname() {
        return fname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getLname() {
        return lname;
    }


    public UUID getUserid() {
        return userid;
    }
}
