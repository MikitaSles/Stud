package entity;

import entity.Purchase;
import entity.PricePurchase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Runner {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\Nik-Asus\\Desktop\\pr 6.1\\untitled\\src\\entity\\in.csv";

        Map<Purchase, String> lastPurchaseWeekdays = loadLastPurchaseWeekdaysFromFile(filePath);
        System.out.println("Map with last purchase weekdays:");
        printMap(lastPurchaseWeekdays);

        Map<Purchase, String> firstPurchaseWeekdays = loadFirstPurchaseWeekdaysFromFile(filePath);
        System.out.println("\nMap with first purchase weekdays:");
        printMap(firstPurchaseWeekdays);

        String breadFirstWeekday = findFirstWeekdayForProduct(lastPurchaseWeekdays, "bread", 1.55);
        String breadLastWeekday = findLastWeekdayForProduct(lastPurchaseWeekdays, "bread", 1.55);
        System.out.println("\nFirst and last weekdays for bread with price 1.55:");
        System.out.println("First weekday: " + breadFirstWeekday);
        System.out.println("Last weekday: " + breadLastWeekday);

        String breadFirstWeekdayPrice170 = findFirstWeekdayForProduct(lastPurchaseWeekdays, "bread", 1.70);
        System.out.println("\nFirst weekday for bread with price 1.70: " + breadFirstWeekdayPrice170);

        removePurchasesByName(lastPurchaseWeekdays, "meat");
        removePurchasesByWeekday(firstPurchaseWeekdays, "FRIDAY");
        System.out.println("\nMaps after removal:");
        System.out.println("Map with last purchase weekdays:");
        printMap(lastPurchaseWeekdays);
        System.out.println("Map with first purchase weekdays:");
        printMap(firstPurchaseWeekdays);

        List<PricePurchase> pricePurchases = loadPricePurchasesFromFile(filePath);
        System.out.println("\nPrice purchases:");
        pricePurchases.forEach(System.out::println);

        double totalCost = calculateTotalCost(pricePurchases);
        System.out.println("\nTotal cost of price purchases: " + totalCost);

        Map<String, List<Purchase>> purchasesByWeekday = loadPurchasesByWeekday(filePath);
        System.out.println("\nPurchases by weekday:");
        printMap(purchasesByWeekday);

        System.out.println("\nTotal cost of all purchases for each weekday:");
        calculateTotalCostByWeekday(purchasesByWeekday);

        String mondayPurchases = findPurchasesByWeekday(purchasesByWeekday, "MONDAY");
        System.out.println("\nPurchases on MONDAY:");
        System.out.println(mondayPurchases);
    }

    private static Map<Purchase, String> loadLastPurchaseWeekdaysFromFile(String filename) {
        Map<Purchase, String> lastPurchaseWeekdays = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Purchase purchase = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length % 2 == 1) {
                    purchase = createPurchase(values);
                } else {
                    String weekday = values[0];
                    if (purchase != null) {
                        lastPurchaseWeekdays.put(purchase, weekday);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastPurchaseWeekdays;
    }

    private static Map<Purchase, String> loadFirstPurchaseWeekdaysFromFile(String filename) {
        Map<Purchase, String> firstPurchaseWeekdays = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Purchase purchase = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length % 2 == 1) {
                    purchase = createPurchase(values);
                    if (purchase != null) {
                        firstPurchaseWeekdays.put(purchase, null);
                    }
                } else {
                    String weekday = values[0];
                    if (purchase != null && firstPurchaseWeekdays.get(purchase) == null) {
                        firstPurchaseWeekdays.put(purchase, weekday);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return firstPurchaseWeekdays;
    }

    private static Purchase createPurchase(String[] values) {
        if (values.length == 3) {
            String name = values[0];
            double price = Double.parseDouble(values[1]);
            return new Purchase(name, price);
        } else if (values.length == 4) {
            String name = values[0];
            double price = Double.parseDouble(values[1]);
            double discount = Double.parseDouble(values[3]);
            return new PricePurchase(name, price, discount);
        }
        return null;
    }

    private static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private static String findFirstWeekdayForProduct(Map<Purchase, String> map, String name, double price) {
        for (Map.Entry<Purchase, String> entry : map.entrySet()) {
            Purchase purchase = entry.getKey();
            if (purchase.getName().equals(name) && purchase.getPrice() == price) {
                return entry.getValue();
            }
        }
        return null;
    }


    private static String findLastWeekdayForProduct(Map<Purchase, String> map, String name, double price) {
        String lastWeekday = null;
        for (Map.Entry<Purchase, String> entry : map.entrySet()) {
            Purchase purchase = entry.getKey();
            if (purchase.getName().equals(name) && purchase.getPrice() == price) {
                lastWeekday = entry.getValue();
            }
        }
        return lastWeekday;
    }

    private static void removePurchasesByName(Map<Purchase, String> map, String name) {
        map.entrySet().removeIf(entry -> entry.getKey().getName().equals(name));
    }

    private static void removePurchasesByWeekday(Map<Purchase, String> map, String weekday) {
        map.entrySet().removeIf(entry -> {
            String value = entry.getValue();
            return value != null && value.equals(weekday);
        });
    }


    private static List<PricePurchase> loadPricePurchasesFromFile(String filename) {
        List<PricePurchase> pricePurchases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Purchase purchase = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length == 4) {
                    purchase = createPurchase(values);
                    if (purchase instanceof PricePurchase) {
                        pricePurchases.add((PricePurchase) purchase);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pricePurchases;
    }

    private static double calculateTotalCost(List<PricePurchase> pricePurchases) {
        double totalCost = 0;
        for (PricePurchase pricePurchase : pricePurchases) {
            totalCost += (pricePurchase.getPrice() - pricePurchase.getDiscount());
        }
        return totalCost;
    }

    private static Map<String, List<Purchase>> loadPurchasesByWeekday(String filename) {
        Map<String, List<Purchase>> purchasesByWeekday = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String weekday = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                if (values.length % 2 == 1) {
                    Purchase purchase = createPurchase(values);
                    if (purchase != null) {
                        List<Purchase> purchases = purchasesByWeekday.getOrDefault(weekday, new ArrayList<>());
                        purchases.add(purchase);
                        purchasesByWeekday.put(weekday, purchases);
                    }
                } else {
                    weekday = values[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return purchasesByWeekday;
    }

    private static void calculateTotalCostByWeekday(Map<String, List<Purchase>> purchasesByWeekday) {
        for (Map.Entry<String, List<Purchase>> entry : purchasesByWeekday.entrySet()) {
            String weekday = entry.getKey();
            List<Purchase> purchases = entry.getValue();
            double totalCost = 0;
            for (Purchase purchase : purchases) {
                totalCost += purchase.getPrice();
            }
            System.out.println("Total cost of purchases on " + weekday + ": " + totalCost);
        }
    }

    private static String findPurchasesByWeekday(Map<String, List<Purchase>> purchasesByWeekday, String weekday) {
        List<Purchase> purchases = purchasesByWeekday.get(weekday);
        StringBuilder result = new StringBuilder();
        if (purchases != null) {
            for (Purchase purchase : purchases) {
                result.append(purchase).append("\n");
            }
        }
        return result.toString();
    }
}
