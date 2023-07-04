import entity.Product;
import purchase.AbstractPurchase;
import purchase.PriceDiscountPurchase;
import purchase.QuantityDiscountPurchase;
import purchase.TransportExpensePurchase;
import entity.Euro;

import java.util.Arrays;

public class Runner {
    public static void main(String[] args) {
        Product product = new Product("Пример товара", new Euro(10.00));

        AbstractPurchase[] purchases = new AbstractPurchase[6];
        purchases[0] = new PriceDiscountPurchase(product, 3, new Euro(1.50));
        purchases[1] = new PriceDiscountPurchase(product, 2, new Euro(0.50));
        purchases[2] = new QuantityDiscountPurchase(product, 4, 0.10);
        purchases[3] = new QuantityDiscountPurchase(product, 6, 0.15);
        purchases[4] = new TransportExpensePurchase(product, 5, new Euro(5.00));
        purchases[5] = new TransportExpensePurchase(product, 3, new Euro(3.00));

        System.out.println("Исходный массив:");
        printArray(purchases);

        Arrays.sort(purchases);
        System.out.println("\nОтсортированный массив по стоимости по убыванию:");
        printArray(purchases);

        double minimumCost = purchases[purchases.length - 1].getCost().getPrice();
        System.out.println("\nМинимальная стоимость = " + minimumCost);

        int searchIndex = search(purchases, new Euro(5.00));
        if (searchIndex != -1) {
            System.out.println("\nНайдена покупка: " + purchases[searchIndex]);
        } else {
            System.out.println("\nНе удалось найти покупку со стоимостью 5,00 евро.");
        }
    }

    private static void printArray(AbstractPurchase[] purchases) {
        for (AbstractPurchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    private static int search(AbstractPurchase[] purchases, Euro targetCost) {
        for (int i = 0; i < purchases.length; i++) {
            if (purchases[i].getCost().equals(targetCost)) {
                return i;
            }
        }
        return -1;
    }
}
