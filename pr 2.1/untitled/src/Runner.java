import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Purchase[] purchases = new Purchase[6];

        // Чтение данных из файла
        try {
            File file = new File("src/in.txt");
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < purchases.length; i++) {
                String line = scanner.nextLine();
                String[] data = line.split(" ");

                String purchaseType = data[0];
                String productName = data[1];
                Euro price = new Euro(Integer.parseInt(data[2]));
                int numberOfUnits = Integer.parseInt(data[3]);

                if (purchaseType.equals("GENERAL_PURCHASE")) {
                    purchases[i] = new Purchase(productName, price, numberOfUnits);
                } else if (purchaseType.equals("DISCOUNT_PURCHASE")) {
                    double discount = Double.parseDouble(data[4]);
                    purchases[i] = new DiscountPurchase(productName, price, numberOfUnits, discount);
                } else if (purchaseType.equals("QUANTITY_DISCOUNT_PURCHASE")) {
                    int discountThreshold = Integer.parseInt(data[4]);
                    double discountRate = Double.parseDouble(data[5]);
                    purchases[i] = new QuantityDiscountPurchase(productName, price, numberOfUnits, discountThreshold, discountRate);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Не удалось найти файл 'src/in.txt'. Пожалуйста, проверьте правильность пути к файлу.");
            return;
        } catch (Exception e) {
            System.err.println("Ошибка при чтении данных из файла: " + e.getMessage());
            return;
        }

        // Вывод содержимого массива на консоль
        for (Purchase purchase : purchases) {
            if (purchase != null) {
                System.out.println(purchase);
            }
        }

        // Проверка наличия покупок
        boolean hasPurchases = false;
        for (Purchase purchase : purchases) {
            if (purchase != null) {
                hasPurchases = true;
                break;
            }
        }
        if (!hasPurchases) {
            System.out.println("Нет доступных покупок.");
            return;
        }

        // Поиск покупки с максимальной стоимостью
        Purchase maxCostPurchase = null;
        for (Purchase purchase : purchases) {
            if (purchase != null) {
                if (maxCostPurchase == null || purchase.getCost().compareTo(maxCostPurchase.getCost()) > 0) {
                    maxCostPurchase = purchase;
                }
            }
        }
        System.out.println("Покупка с максимальной стоимостью: " + maxCostPurchase);

        // Проверка на равенство всех покупок
        boolean allEqual = true;
        for (int i = 1; i < purchases.length; i++) {
            if (purchases[i] != null && !purchases[i].equals(purchases[0])) {
                allEqual = false;
                break;
            }
        }
        System.out.println("Все покупки равны? " + allEqual);
    }
}
