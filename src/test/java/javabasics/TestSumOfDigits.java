package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSumOfDigits {
    SumOfDigits sumobj;
    @BeforeEach
    public void setup(){
        SumOfDigits sumobj = new SumOfDigits();
    }
    @Test
    public void testSumOfDigit1()
    {
        int actual=2;
        assertEquals(actual,sumobj.sumofdigits(11));
    }
    @Test
    public void testSumOfDigit12()
    {
        int actual=3;
        assertEquals(actual,sumobj.sumofdigits(111));
    }


}
