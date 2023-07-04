package purchase;
import entity.Product;
import entity.Euro;

public class QuantityDiscountPurchase extends AbstractPurchase {
    private static final int DISCOUNT_THRESHOLD = 5;
    private double discountRate;

    public QuantityDiscountPurchase() {
    }

    public QuantityDiscountPurchase(Product product, int numberOfUnits, double discountRate) {
        super(product, numberOfUnits);
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public Euro getCost() {
        Euro unitPrice = product.getPrice();
        Euro totalCost = new Euro(unitPrice.getPrice() * numberOfUnits);
        if (numberOfUnits > DISCOUNT_THRESHOLD) {
            Euro discountAmount = new Euro(totalCost.getPrice() * discountRate);
            return new Euro(totalCost.getPrice() - discountAmount.getPrice());
        }
        return totalCost;
    }

    @Override
    public String toString() {
        return super.toString() + ";" + discountRate;
    }
}
