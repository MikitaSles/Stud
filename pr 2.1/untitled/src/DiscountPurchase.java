public class DiscountPurchase extends Purchase {
    private double discount;

    public DiscountPurchase() {
        // Конструктор без аргументов
    }

    public DiscountPurchase(String productName, Euro price, int numberOfUnits, double discount) {
        super(productName, price, numberOfUnits);
        this.discount = discount;
    }

    @Override
    public Euro getCost() {
        Euro cost = super.getCost();
        double discountAmount = cost.getCents() * discount / 100.0;
        return new Euro((int) (cost.getCents() - discountAmount));
    }
}
