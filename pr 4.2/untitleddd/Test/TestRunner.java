import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRunner {

    @Test
    public void testFirstScenario() throws FileNotFoundException {
        double sum = 8.24;
        int errorLines = 3;
        Map<String, Double> expectedValues = new HashMap<>();
        expectedValues.put("value11", 0.0);
        expectedValues.put("value12", 4.0);
        expectedValues.put("value13", 5.1);
        expectedValues.put("value14", 2.7);

        String filename = "resources/in1";
        testScenario(filename, sum, errorLines, expectedValues);
    }

    @Test
    public void testSecondScenario() throws FileNotFoundException {
        double sum = 30.242;
        int errorLines = 9;
        Map<String, Double> expectedValues = new HashMap<>();
        expectedValues.put("value23", 5.0);
        expectedValues.put("value73", -10.0);
        expectedValues.put("value81", 10.0);
        expectedValues.put("value201", -1.0);
        expectedValues.put("value5a", 2.0);
        expectedValues.put("value1a2", 10.0);
        expectedValues.put("value21-2", 10.0);
        expectedValues.put("value15", 2.0);
        expectedValues.put("value015", 2.0);
        expectedValues.put("value1005", 2.0);
        expectedValues.put("value202", 3.24);
        expectedValues.put("value5", 2.0);
        expectedValues.put("value351", 2.0);
        expectedValues.put("value41", 0.0);
        expectedValues.put("value343", 0.001);
        expectedValues.put("value910000000000000000000000000000000000000000000", 20.0);

        String filename = "src.resources.in2";
        testScenario(filename, sum, errorLines, expectedValues);
    }

    @Test
    public void testThirdScenario() throws FileNotFoundException {
        double sum = 1.9;
        int errorLines = 0;
        Map<String, Double> expectedValues = new HashMap<>();
        expectedValues.put("value11", 2.2);
        expectedValues.put("value21", -0.3);

        String filename = "src.resources.in3";
        testScenario(filename, sum, errorLines, expectedValues);
    }

    private void testScenario(String filename, double expectedSum, int expectedErrorLines, Map<String, Double> expectedValues) throws FileNotFoundException {
        ResourceBundle rb = ResourceBundle.getBundle(filename);
        Enumeration<String> keys = rb.getKeys();
        double sum = 0;
        int errorLines = 0;
        Map<String, Double> values = new HashMap<>();

        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            String value = rb.getString(key);
            String trimmedKey = key.trim();
            String trimmedValue = value.trim();

            Pattern indexPattern = Pattern.compile("index(\\d+)");
            Matcher indexMatcher = indexPattern.matcher(trimmedKey);

            Pattern valuePattern = Pattern.compile("value(\\d+)");
            Matcher valueMatcher = valuePattern.matcher(trimmedKey);

            if (indexMatcher.matches()) {
                String indexValue = trimmedValue.isEmpty() ? "0" : trimmedValue;
                int index = Integer.parseInt(indexValue);
                if (valueMatcher.matches()) {
                    String valueKey = "value" + indexMatcher.group(1) + valueMatcher.group(1);
                    try {
                        double doubleValue = Double.parseDouble(trimmedValue);
                        sum += doubleValue;
                        values.put(valueKey, doubleValue);
                    } catch (NumberFormatException ignored) {
                        errorLines++;
                    }
                }
            }
        }

        Assert.assertEquals(expectedSum, sum, 0.001);
        Assert.assertEquals(expectedErrorLines, errorLines);
        for (Map.Entry<String, Double> entry : expectedValues.entrySet()) {
            Assert.assertEquals(entry.getValue(), values.get(entry.getKey()), 0.001);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        TestRunner testRunner = new TestRunner();
        testRunner.testFirstScenario();
        testRunner.testSecondScenario();
        testRunner.testThirdScenario();
    }
}
