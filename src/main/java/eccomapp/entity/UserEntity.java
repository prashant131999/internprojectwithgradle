package eccomapp.entity;

import java.util.UUID;

public class UserEntity {
    private String fname,lname,email,address,dateOfBirth,dateCreated,dateLastUpdated,mobileNumber;
    UUID userid=UUID.randomUUID();


//    public UserEntity() {
//        this.fname=fname;
//        this.lname=lname;
//        this.email=email;
//        this.address=address;
//        this.dateOfBirth=dateOfBirth;
//        this.dateCreated=dateCreated;
//        this.dateLastUpdated=dateLastUpdated;
//        this.mobileNumber=mobileNumber;
//    }


    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateLastUpdated(String dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateLastUpdated() {
        return dateLastUpdated;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public UUID getUserid() {
        return userid;
    }
}
