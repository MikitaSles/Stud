package lt.ehu.lab0511exceptions.entity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

public class PurchaseList {
    private List<Purchase> purchases;

    public PurchaseList(String filename) {
        purchases = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length < 3 || parts.length > 4) {
                    System.err.println("Invalid line: " + line);
                    continue;
                }
                try {
                    String product = parts[0];
                    int price = Integer.parseInt(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    if (parts.length == 3) {
                        purchases.add(new Purchase(product, price, quantity));
                    } else {
                        int discount = Integer.parseInt(parts[3]);
                        purchases.add(new PriceDiscountPurchase(product, price, quantity, discount));
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            purchases = new ArrayList<>();
        }
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void insertPurchase(int index, Purchase purchase) {
        if (purchase == null) {
            System.err.println("Invalid purchase");
            return;
        }

        if (index < 0) {
            purchases.add(0, purchase);
        } else if (index > purchases.size()) {
            purchases.add(purchase);
        } else {
            purchases.add(index, purchase);
        }
    }

    public void deleteSubsequence(int from, int to) {
        if (from < 0) {
            from = 0;
        }
        if (to > purchases.size()) {
            to = purchases.size();
        }
        if (from > to) {
            System.err.println("Invalid range");
            return;
        }

        purchases.subList(from, to).clear();
    }

    public int totalCost() {
        int total = 0;
        for (Purchase purchase : purchases) {
            total += purchase.getTotalCost();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Purchase purchase : purchases) {
            sb.append(purchase.toString()).append("\n");
        }
        return sb.toString();
    }

    public void sortPurchases() {
        purchases.sort(comparing(Purchase::getProduct));
    }

    public int searchPurchase(Purchase purchase) {
        sortPurchases();
        return Collections.binarySearch(purchases, purchase, comparing(Purchase::getProduct));
    }

    public int getSize() {
        return purchases.size();
    }

}