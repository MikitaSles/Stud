package entity;

public class PricePurchase extends Purchase {
    private double discount;

    public PricePurchase(String name, double price, double discount) {
        super(name, price);
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "PricePurchase{" +
                "name='" + getName() + '\'' +
                ", price=" + getPrice() +
                ", discount=" + discount +
                '}';
    }
}
