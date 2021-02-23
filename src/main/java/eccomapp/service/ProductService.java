package eccomapp.service;

import eccomapp.exception.InvalidInputException;

public class ProductService {
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
}
