package entity;

public class Euro {
    private double price;

    public Euro(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%.2f", price);
    }
}
