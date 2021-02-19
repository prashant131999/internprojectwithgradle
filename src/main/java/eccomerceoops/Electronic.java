package eccomerceoops;

public class Electronic extends Product {

    /**
     *  This constructor takes input through user for book name,baseprice,description,weight,salesTax,
     *  shippingCost and quantity
     * @param name for electronic product
     * @param basePrice for baseprice of electronic product
     * @param description for description of electronic product
     * @param weight for weight of electronic product
     * @param salesTax for salesTax of electronic product
     * @param shippingCost for shippingCost of electronic product
     * @param quantity for quantity of electronic product
     */
    public Electronic(String name, int basePrice, String description, float weight, float salesTax
            , float shippingCost,int quantity) {
        super(name, basePrice, description, weight,salesTax,shippingCost,quantity);

    }
}
