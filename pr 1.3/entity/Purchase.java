package entity;

// Purchase.java
import java.text.DecimalFormat;

public class Purchase implements Comparable<Purchase> {
    private String productName;
    private int price;
    private int numberOfUnits;
    private double discountPercent;
    private WeekDay weekDay;

    public Purchase() {
    }

    public Purchase(String productName, int price, int numberOfUnits, double discountPercent, WeekDay weekDay) {
        this.productName = productName;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
        this.discountPercent = discountPercent;
        this.weekDay = weekDay;
    }

    // Getters and setters

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public double getCost() {
        double cost = price * numberOfUnits * (100 - discountPercent) / 100.0;
        return Math.round(cost) / 100.0; // Rounded to 1.00 Euro (100 cents)
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return productName + ";" + price + ";" + numberOfUnits + ";" + discountPercent + ";" + weekDay + ";" + df.format(getCost());
    }

    @Override
    public int compareTo(Purchase purchase) {
        return numberOfUnits - purchase.getNumberOfUnits();
    }
}
