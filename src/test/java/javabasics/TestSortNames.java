package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSortNames {
    SortNames sortobj;
    @BeforeEach
    public void setup(){
        SortNames sortobj = new SortNames();
    }
    @Test
    public void testSortInAcsendingOrder()
    {
        ArrayList<String>word=new ArrayList<>();
        word.add("prashant");
        word.add("sahrawat");
        String [] names={"prashant","sahrawat"};
        assertEquals(word,sortobj.sortnames(names,2));
    }
}

