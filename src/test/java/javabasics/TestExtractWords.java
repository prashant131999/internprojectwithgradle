package javabasics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExtractWords {
 ExtractWords ext;
    @BeforeEach
    public void setup(){
        ExtractWords ext = new ExtractWords();
    }
    @Test
    public void testExtractWords1()
    {
        ArrayList<String>actual=new ArrayList<>();
        actual.add("prashant");
        actual.add("sahrawat");
        assertEquals(actual,ext.extractWords("prashant sahrawat"));
    }
    @Test
    public void testExtractWords2()
    {
        ArrayList<String>actual=new ArrayList<>();
        actual.add("prashant");
        actual.add("kumar");
        assertEquals(actual,ext.extractWords("prashant kumar"));
    }



}
