package eccomerceoops;

public class Groceries extends Product {
    /**
     * This is a constructor used  for initialize the parameter given by user
     *
     * @param name         for name of product
     * @param basePrice    for baseprice of product
     * @param description  for decription of product
     * @param weight       for weight of product
     * @param salesTax     for salestax of product
     * @param shippingCost for shiping charges of product
     * @param quantity     for quantity of product
     */
    public Groceries(String name, int basePrice, String description, float weight, float salesTax
                     ,float shippingCost,int quantity) {
        super(name, basePrice, description, weight,salesTax,shippingCost,quantity);

    }

}
