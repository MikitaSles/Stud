package purchase;
import entity.Product;
import entity.Euro;

public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    protected Product product;
    protected int numberOfUnits;

    public AbstractPurchase() {
    }

    public AbstractPurchase(Product product, int numberOfUnits) {
        this.product = product;
        this.numberOfUnits = numberOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public abstract Euro getCost();

    @Override
    public String toString() {
        return getClass().getSimpleName() + ";" + product.getName() + ";" + product.getPrice() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase purchase) {
        return Double.compare(purchase.getCost().getPrice(), getCost().getPrice());
    }
}
