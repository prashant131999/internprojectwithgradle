package eccomerceoops;

public class Product {
    protected String name;
    protected int basePrice;
    protected String description;
    // weight is in kg
    protected float weight;
    protected float salesTax;
    protected float shippingCost;
    protected float totalCost;
    protected int quantity;

    /**
     * This constructor initialize name,baseprice,description and weight given by user
     *
     * @param name        for product name
     * @param basePrice   for baseprice of product
     * @param description for description of product
     * @param weight      for weight of product
     */
    public Product(String name, int basePrice, String description, float weight, float salesTax,
                   float shippingCost, int quantity) {
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.weight = weight;
        this.salesTax = salesTax;
        this.shippingCost = shippingCost;
        this.quantity = quantity;
    }

    /**
     * This method return the total price of product after including every taxes
     *
     * @return final cost
     */
    public float GetTotalPrice() {
        return (float) ((getPrice() + (salesTax / 100.0) * (getPrice()) + shippingCost * getWeight()) * quantity);
    }

    /**
     * This method return the name of product
     *
     * @return name of product
     */

    public String getName() {
        return name;
    }

    /**
     * This method return the base price of product given by user
     *
     * @return base price
     */
    public int getPrice() {
        return basePrice;
    }

    /**
     * This method return the weight given by user
     *
     * @return weight
     */
    public float getWeight() {
        return weight;
    }

    /**
     * This method return the description of product given by user
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method prints the information about product
     */
    public void promptsinfo() {
        System.out.println("Name of product: " + name + "\n" +
                "Base_Price: " + basePrice + "\n" +
                "Weight: " + weight + "\n" +
                "Description: " + description);
    }

    /**
     * This method used to get salestax from user
     *
     * @return salestax
     */
    public float GetTax() {
        return salesTax;
    }


    /**
     * This method returns the shipping cost of product
     *
     * @return shipping cost
     */
    public float GetShipping() {
        return shippingCost * getWeight();

    }


    /**
     * This method returns the quantity
     *
     * @return the quantity
     */

    public int GetQuantity() {
        return quantity;
    }


}
