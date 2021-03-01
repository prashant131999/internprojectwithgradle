package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestPrimeOrNot {
IsPrime pr;
    @BeforeEach
    public void setup(){
        IsPrime pr = new IsPrime();
    }
    @Test
    public void testPrime1()
    {
        assertTrue(pr.check(23));
    }
    @Test
    public void testPrime2()
    {
        assertFalse(pr.check(40));
    }
}
