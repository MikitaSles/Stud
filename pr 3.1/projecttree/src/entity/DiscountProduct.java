package entity;

public class DiscountProduct extends Product {
    private double priceDiscount;

    public DiscountProduct(String name, double price, double priceDiscount) {
        super(name, price);
        this.priceDiscount = priceDiscount;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }
}
