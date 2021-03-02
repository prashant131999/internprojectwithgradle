package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFibonacci {
   Fibonacci fib;
    @BeforeEach
    public void setup(){
        Fibonacci fib = new Fibonacci();
    }
    @Test
    public void testfibonacci1()
    {
        ArrayList<Integer>actual=new ArrayList<>();
        actual.add(1);
        actual.add(1);
        assertEquals(actual,fib.fibonacci(2));
    }
    @Test
    public void testfibonacci2()
    {
        ArrayList<Integer>actual=new ArrayList<Integer>();
        actual.add(1);
        actual.add(1);
        actual.add(2);
        assertEquals(actual,fib.fibonacci(3));
    }
}
