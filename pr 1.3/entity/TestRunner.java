package entity;

// TestRunner.java
import org.junit.Assert;
import org.junit.Test;

public class TestRunner {

    @Test
    public void testPurchaseConstructors() {
        Purchase purchase = new Purchase();
        Assert.assertNotNull(purchase);

        purchase = new Purchase("Product", 100, 5, 10.0, WeekDay.MONDAY);
        Assert.assertEquals("Product", purchase.getProductName());
        Assert.assertEquals(100, purchase.getPrice());
        Assert.assertEquals(5, purchase.getNumberOfUnits());
        Assert.assertEquals(10.0, purchase.getDiscountPercent(), 0.01);
        Assert.assertEquals(WeekDay.MONDAY, purchase.getWeekDay());
    }

    @Test
    public void testGetCost() {
        Purchase purchase = new Purchase("Product", 100, 5, 10.0, WeekDay.MONDAY);
        double cost = purchase.getCost();
        Assert.assertEquals(450.0, cost, 0.01);
    }

    @Test
    public void testToString() {
        Purchase purchase = new Purchase("Product", 100, 5, 10.0, WeekDay.MONDAY);
        String expected = "Product;100;5;10.0;MONDAY;4.50";
        String actual = purchase.toString();
        Assert.assertEquals(expected, actual);
    }
}
