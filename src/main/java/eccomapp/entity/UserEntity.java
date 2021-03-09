package eccomapp.entity;

import eccomapp.annotation.NotNullAnnotation;

import java.util.UUID;

public class UserEntity {
    @NotNullAnnotation
    private String  lname;

    private String dateOfBirth, email, address,fname, dateLastUpdated, mobileNumber;
    private String dateCreated;

    UUID userid ;
    public UserEntity(String fname, String lname, String email, String dateOfBirth, String address
    , String mobileNumber)
    {
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.dateOfBirth=dateOfBirth;
        this.address=address;
        this.mobileNumber=mobileNumber;
    }

    public UserEntity(UUID userid,String fname, String lname, String email, String dateOfBirth, String address
            , String mobileNumber, String date)
    {
        this.userid=userid;
        this.fname=fname;
        this.lname=lname;
        this.email=email;
        this.dateOfBirth=dateOfBirth;
        this.address=address;
        this.mobileNumber=mobileNumber;
        this.dateCreated = date;
    }

    public UserEntity()
    {
    }


    public void setAddress(String address) {
        this.address = address;
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
    public void setUserid(UUID userid)
    {
        this.userid=userid;
    }
}
