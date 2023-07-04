package purchase;
import entity.Product;
import entity.Euro;

public class PriceDiscountPurchase extends AbstractPurchase {
    private Euro discountPerUnit;

    public PriceDiscountPurchase() {
    }

    public PriceDiscountPurchase(Product product, int numberOfUnits, Euro discountPerUnit) {
        super(product, numberOfUnits);
        this.discountPerUnit = discountPerUnit;
    }

    public Euro getDiscountPerUnit() {
        return discountPerUnit;
    }

    public void setDiscountPerUnit(Euro discountPerUnit) {
        this.discountPerUnit = discountPerUnit;
    }

    @Override
    public Euro getCost() {
        Euro unitPrice = product.getPrice();
        Euro totalCost = new Euro(unitPrice.getPrice() * numberOfUnits);
        Euro discountAmount = new Euro(discountPerUnit.getPrice() * numberOfUnits);
        return new Euro(totalCost.getPrice() - discountAmount.getPrice());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + discountPerUnit;
    }
}
