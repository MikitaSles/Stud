package entity;

public class Product {
    private String name;
    private Euro price;

    public Product() {
    }

    public Product(String name, Euro price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Euro getPrice() {
        return price;
    }

    public void setPrice(Euro price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + ";" + price;
    }
}
