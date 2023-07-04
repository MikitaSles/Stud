package entity;

// Runner.java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Runner {
    private static final String FILE_PATH = "src/in.txt";

    public static void main(String[] args) {
        Purchase[] purchases = readPurchasesFromFile(FILE_PATH);

        // Output the array content
        System.out.println("Class constants");
        for (int i = 0; i < purchases.length; i++) {
            System.out.println("purchase[" + i + "]: " + purchases[i]);
        }

        // Calculate average cost, total cost on Monday, and day with maximum purchase cost
        double totalCost = 0;
        int mondayTotalCost = 0;
        double maxCost = purchases[0].getCost();
        WeekDay dayWithMaxCost = purchases[0].getWeekDay();

        for (Purchase purchase : purchases) {
            totalCost += purchase.getCost();

            if (purchase.getWeekDay() == WeekDay.MONDAY) {
                mondayTotalCost += purchase.getCost();
            }

            if (purchase.getCost() > maxCost) {
                maxCost = purchase.getCost();
                dayWithMaxCost = purchase.getWeekDay();
            }
        }

        double averageCost = totalCost / purchases.length;
        System.out.println("Average cost of all purchases: " + String.format("%.3f", averageCost));
        System.out.println("Total cost of all purchases on Monday: " + mondayTotalCost);
        System.out.println("Day with the maximum purchase cost: " + dayWithMaxCost);

        // Sort the array by the field 'numberOfUnits' in ascending order
        Arrays.sort(purchases);

        // Output the sorted array content
        System.out.println("\nSorted array:");
        for (int i = 0; i < purchases.length; i++) {
            System.out.println("purchase[" + i + "]: " + purchases[i]);
        }

        // Find a purchase with 'numberOfUnits' equal to 5 using binary search
        int index = Arrays.binarySearch(purchases, new Purchase("", 0, 5, 0, WeekDay.SUNDAY));
        if (index >= 0) {
            System.out.println("\nFound purchase with 'numberOfUnits' equal to 5:");
            System.out.println("purchase[" + index + "]: " + purchases[index]);
        } else {
            System.out.println("\nPurchase with 'numberOfUnits' equal to 5 not found.");
        }
    }

    private static Purchase[] readPurchasesFromFile(String filePath) {
        Purchase[] purchases = null;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            int purchasesNumber = Integer.parseInt(reader.readLine().trim());
            purchases = new Purchase[purchasesNumber];

            for (int i = 0; i < purchasesNumber; i++) {
                String[] purchaseData = reader.readLine().trim().split("\\s+");
                String productName = purchaseData[0];
                int price = Integer.parseInt(purchaseData[1]);
                int numberOfUnits = Integer.parseInt(purchaseData[2]);
                double discountPercent = Double.parseDouble(purchaseData[3]);
                WeekDay weekDay = WeekDay.values()[Integer.parseInt(purchaseData[4])];
                purchases[i] = new Purchase(productName, price, numberOfUnits, discountPercent, weekDay);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return purchases;
    }
}
