public class QuantityDiscountPurchase extends Purchase {
    private int discountThreshold;
    private double discountRate;

    public QuantityDiscountPurchase() {
        // Конструктор без аргументов
    }

    public QuantityDiscountPurchase(String productName, Euro price, int numberOfUnits, int discountThreshold, double discountRate) {
        super(productName, price, numberOfUnits);
        this.discountThreshold = discountThreshold;
        this.discountRate = discountRate;
    }

    @Override
    public Euro getCost() {
        Euro cost = super.getCost();
        if (numberOfUnits > discountThreshold) {
            double discountAmount = cost.getCents() * discountRate / 100.0;
            return new Euro((int) (cost.getCents() - discountAmount));
        } else {
            return cost;
        }
    }
}
