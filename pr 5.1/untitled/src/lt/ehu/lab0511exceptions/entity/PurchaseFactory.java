package lt.ehu.lab0511exceptions.entity;

import lt.ehu.lab0511exceptions.entity.PriceDiscountPurchase;
import lt.ehu.lab0511exceptions.entity.Purchase;

public class PurchaseFactory {
    public static Purchase createPurchase(String csvString) {
        String[] parts = csvString.split(";");

        if (parts.length < 3) {
            return null;
        }

        try {
            String product = parts[0];
            int price = Integer.parseInt(parts[1]);
            int quantity = Integer.parseInt(parts[2]);

            if (parts.length == 4) {
                int discount = Integer.parseInt(parts[3]);
                return new PriceDiscountPurchase(product, price, quantity, discount);
            }

            return new Purchase(product, price, quantity);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}