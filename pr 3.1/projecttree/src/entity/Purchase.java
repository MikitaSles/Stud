package entity;

import entity.Product;

public class Purchase {
    private Product product;
    private double quantity;

    public Purchase(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPurchaseCost() {
        return Math.round(product.getPrice() * quantity);
    }
}
