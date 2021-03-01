package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestConvertBinary {
    CovertBinary conv;
    @BeforeEach
    public void setup(){
        CovertBinary conv = new CovertBinary();
    }
    @Test
    public void testConvertBinary()
    {
        ArrayList<Integer>actual=new ArrayList<Integer>();
        actual.add(1);
        actual.add(1);
        assertEquals(actual,conv.convertdectobin(3));
    }
    @Test
    public void testConvertBinary2()
    {
        ArrayList<Integer>actual=new ArrayList<Integer>();
        actual.add(1);
        actual.add(1);
        actual.add(0);
        assertEquals(actual,3);

    }




}
