package eccomapp.controller;

import eccomapp.doa.UserDao;
import eccomapp.entity.UserEntity;
import eccomapp.service.UserService;

import java.util.Scanner;

public class UserController {
    Scanner sc = new Scanner(System.in);

    private String fname, lname, email, address, dateOfBirth, dateCreated, dateLastUpdated, mobileNumber;
    private int userid;

    UserEntity userEntityObj = new UserEntity();
    UserDao userDaoObject=new UserDao();
    UserService userServobj = new UserService();

    public void createuser() {
        System.out.println("Enter the first name");
        fname = sc.next();
        userEntityObj.setFname(fname);
        if (!(validatefname(userEntityObj.getFname(fname)))) {
            break;
        }
        System.out.println("Enter the last name");
        lname = sc.next();
        userEntityObj.setLname(lname);
        if (!(validatelname(userEntityObj.getLname(lname)))) {
            break;
        }
        System.out.println("Enter the email");
        email = sc.next();
        userEntityObj.setEmail(email);
        if (!(validateemail(userEntityObj.getEmail(email)))) {
            break;
        }
        System.out.println("Enter the address");
        address = sc.next();
        System.out.println("date of birth");
        dateOfBirth = sc.next();
        userEntityObj.setDateOfBirth(dateOfBirth);
        if (!(validateDob(userEntityObj.getDateOfBirth(dateOfBirth)))) {
            break;
        }
        System.out.println("date last created");
        dateCreated = sc.next();
        userEntityObj.setDateCreated();
        System.out.println("date last updated");
        dateLastUpdated = sc.next();
        userEntityObj.setDateLastUpdated();
        System.out.println("Enter the mobile number");
        mobileNumber = sc.next();
        userEntityObj.setMobileNumber(mobileNumber);

    }

    public void deleteuser(String emailaddress) {

        userDaoObject.delete(emailaddress);
        logger.info("user deleted");

    }
    public void updateUser(String address)
    {
        userEntityObj.setAddress(address);
        userDaoObject.update(address);
        logger.info("user data updated");

    }
        System.out.println("Enter 1 to create user");
        System.out.println("Enter 2 to delete user");
        System.out.println("Enter 3 to update user detail ");
        int choice = sc.nextInt();
        switch(choice)

    {
        case 1:
            createuser();
            break;
        case 2:
            System.out.println("Enter the email address of user to delete");
            String emailaddress=sc.next();
            deleteuser(emailaddress);
            break;
        case 3:
            System.out.println("enter the new address");
            String address=sc.next();
            updateUser(address);
            break;
    }
}
