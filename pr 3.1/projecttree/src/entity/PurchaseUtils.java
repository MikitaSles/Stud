package entity;

import entity.Purchase;

import java.text.DecimalFormat;
import java.util.List;

public class PurchaseUtils {
    private Purchase purchase;

    public PurchaseUtils(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void printPurchase() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        System.out.println(purchase.getProduct().getName() + "," + decimalFormat.format(purchase.getQuantity()));
    }

    public void printCost() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        System.out.println("cost = " + decimalFormat.format(purchase.getPurchaseCost()) + " Euro");
    }

    public void printCostDiff(Purchase p) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double diff = purchase.getPurchaseCost() - p.getPurchaseCost();
        String sign = diff > 0 ? "positive" : diff < 0 ? "negative" : "";
        System.out.println("diff = " + decimalFormat.format(Math.abs(diff)) + " Euro (" + sign + ")");
    }

    public void printIsSameCost(List<Purchase> purchases) {
        boolean sameCost = false;
        for (Purchase p : purchases) {
            if (purchase.getPurchaseCost() == p.getPurchaseCost()) {
                sameCost = true;
                break;
            }
        }
        System.out.println("Has same cost: " + sameCost);
    }
}
