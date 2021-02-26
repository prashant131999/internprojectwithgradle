package eccomapp.util;

import eccomapp.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestValidator {
    static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = new Validator();

    }

    @Test
    public void testValidateMobileNumber() throws InvalidInputException {
        assertEquals(true, validator.validateMobileNumber("9639402926"));
    }
    @Test
    public void testMobileNumber()
    {
        boolean thrown = false;
        try {
            validator.validateMobileNumber("90327382");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }


    @Test
    public void testValidateNames() throws InvalidInputException {
        assertEquals(true, validator.validateNames("prashant"));
    }

    @Test
    public void testEmail() throws InvalidInputException {
        assertEquals(false, validator.isValidEmail("prashant"));
    }

    @Test
    public void testValidateQuantity() throws InvalidInputException {
        validator.validateQuantity(20);

    }

    @Test
    public void testAddQuantity() {
        validator.addQuantity(20);
    }

    @Test
    public void testIsValidEmail() {
        boolean thrown = false;
        try {
            validator.validateEmailAddress("prashant");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }

    @Test
    public void testValidNames() {
        boolean thrown = false;
        try {
            validator.validateNames("prashant4");
        } catch (InvalidInputException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }

    @Test
    public void testemail() {
        assertTrue(validator.isValidEmail("prashant@gmail.com"));
    }


}
