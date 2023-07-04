package purchase;
import entity.Product;
import entity.Euro;

public class TransportExpensePurchase extends AbstractPurchase {
    private Euro transportExpenses;

    public TransportExpensePurchase() {
    }

    public TransportExpensePurchase(Product product, int numberOfUnits, Euro transportExpenses) {
        super(product, numberOfUnits);
        this.transportExpenses = transportExpenses;
    }

    public Euro getTransportExpenses() {
        return transportExpenses;
    }

    public void setTransportExpenses(Euro transportExpenses) {
        this.transportExpenses = transportExpenses;
    }

    @Override
    public Euro getCost() {
        Euro unitPrice = product.getPrice();
        Euro totalCost = new Euro(unitPrice.getPrice() * numberOfUnits);
        return new Euro(totalCost.getPrice() + transportExpenses.getPrice());
    }

    @Override
    public String toString() {
        return super.toString() + ";" + transportExpenses;
    }
}
