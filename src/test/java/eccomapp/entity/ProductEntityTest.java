package eccomapp.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductEntityTest {
    private static ProductEntity productEntity;

    @BeforeAll
    public static void setup()
    {
        productEntity= new ProductEntity();



    }
    @Test
    public  void testGet()
    {
        productEntity.setQuantity(4);
        int quant=productEntity.getQuantity();
        assertEquals(4,quant);

    }

    @Test
    public void testSet()
    {
        productEntity.setQuantity(2);
        assertEquals(2,productEntity.getQuantity());
    }


}
