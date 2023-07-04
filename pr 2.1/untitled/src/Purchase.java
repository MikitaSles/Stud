public class Purchase {
    private String productName;
    private Euro price;
    protected int numberOfUnits;

    public Purchase() {
        // No-argument constructor
    }

    public Purchase(String productName, Euro price, int numberOfUnits) {
        this.productName = productName;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Euro getCost() {
        return new Euro(price.getCents() * numberOfUnits);
    }

    public String toString() {
        return getClass().getSimpleName() + ";" + productName + ";" + price + ";" + numberOfUnits;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Purchase other = (Purchase) obj;
        return this.productName.equals(other.productName) && this.price.equals(other.price);
    }
}
