import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestRunner {

    private static final String RESULT_HEAD = "result(";
    private static final String RESULT_TAIL = ") = ";
    private static final String PLUS = " + ";
    private static final String MINUS = " - ";
    private static final int PLUS_LENGTH = PLUS.length();

    private static int getResult(String csvName, StringBuilder strResult) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileReader(csvName))) {
            double result = 0;
            int errorLines = 0;
            boolean isFirstNegative = true;
            StringBuilder tempResult = new StringBuilder();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] elements = line.split(";");

                double index = Double.parseDouble(elements[0]);

                if (index >= 0 && index < elements.length) {
                    try {
                        double value = Double.parseDouble(elements[(int) index]);
                        result = isFirstNegative ? value : result - value;
                        isFirstNegative = false;
                        tempResult.append(value).append(PLUS);
                    } catch (NumberFormatException e) {
                        errorLines++;
                    }
                } else {
                    errorLines++;
                }
            }

            if (tempResult.length() > 0) {
                tempResult.setLength(tempResult.length() - PLUS_LENGTH);
            }

            strResult.insert(0, RESULT_HEAD).append(tempResult).append(RESULT_TAIL).append(result);
            return errorLines;
        }
    }


    @Test
    public void testMainScenario() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("C:\\Users\\Nik-Asus\\Desktop\\4.1\\TestingLaf\\src\\in1.csv", result);
        Assert.assertEquals(3, errorLines);
        String expectedIn1 = "result(5.2 - 3.14 + 0.0) = 2.06";
        Assert.assertEquals(expectedIn1, result.toString());
    }


    @Test(expected = FileNotFoundException.class)
    public void testWrongCsvName() throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        getResult("nonexistent.csv", result);
    }

    public static void main(String[] args) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        int errorLines = getResult("C:\\Users\\Nik-Asus\\Desktop\\4.1\\TestingLaf\\src\\in1.csv", result);
        System.out.println("Количество ошибочных строк: " + errorLines);
        System.out.println(result);
    }
}
