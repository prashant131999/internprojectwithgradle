package eccomerceoops;

public class Books extends Product {


    /**
     *  This constructor takes input through user for book name,baseprice,description,weight,salesTax,
     *  shippingCost and quantity
     * @param name for book name
     * @param basePrice for baseprice of book
     * @param description for description of book
     * @param weight for weight of book
     * @param salesTax for salesTax of book
     * @param shippingCost for shippingCost of book
     * @param quantity for quantity of book
     */
    public Books(String name, int basePrice, String description, float weight, float salesTax, float shippingCost
    ,int quantity) {
        super(name, basePrice, description, weight,salesTax,shippingCost,quantity);

    }





}
