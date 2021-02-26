package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNumberTable {

    NumberTable tab;
    @BeforeEach
    public void setup(){
        NumberTable tab = new NumberTable();
    }
    @Test
    public void testfibonacci1()
    {
        ArrayList<Integer> actual=new ArrayList<>();
        for(int i=1;i<=10;i++) {
            actual.add(4*i);
        }
//        assertEquals(actual,tab.printtable(4));
    }
    @Test
    public void testfibonacci2()
    {
        ArrayList<Integer>actual=new ArrayList<Integer>();
        for(int i=1;i<=10;i++) {
            actual.add(3*i);
        }
        assertEquals(actual,tab.printtable(3));
    }


}
