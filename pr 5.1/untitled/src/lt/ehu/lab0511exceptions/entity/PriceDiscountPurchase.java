package lt.ehu.lab0511exceptions.entity;

public class PriceDiscountPurchase extends Purchase {
    private final int discount;

    public PriceDiscountPurchase(String product, int price, int quantity, int discount) {
        super(product, price, quantity);
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + discount;
    }

    @Override
    public int getTotalCost() {
        return super.getTotalCost() - discount;
    }
}