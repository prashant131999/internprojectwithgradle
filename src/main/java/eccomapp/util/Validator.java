package eccomapp.util;

import eccomapp.exception.InvalidInputException;
import java.util.regex.Pattern;

public class Validator {
    private static  int availableQuantity=50;
    public void validateQuantity(int quant) throws InvalidInputException
    {
        if(quant>availableQuantity)
        {
            throw new InvalidInputException(400,"check input");
        }
        else {
            availableQuantity = availableQuantity - quant;
        }
    }
    public void addQuantity(int quantity)
    {
        availableQuantity=availableQuantity+quantity;
    }
    public  static boolean  validateNames(String name) throws InvalidInputException
    {
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isDigit(ch)) {
                throw new InvalidInputException(400,"check input");
            }
        }
        return true;

    }
    public static boolean validateMobileNumber(String mobileNumber ) throws InvalidInputException
    {
        if (mobileNumber.length() == 10) {
            return true;
        }


        throw new InvalidInputException(400,"invalid mobile number");

    }
    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public  void validateEmailAddress(String email) throws InvalidInputException
    {
        if(!(isValidEmail(email)))
        {
            throw new InvalidInputException(400,"invalid email address");
        }
    }
}
