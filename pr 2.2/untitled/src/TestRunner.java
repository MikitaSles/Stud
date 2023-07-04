import entity.Euro;
import org.junit.Assert;
import org.junit.Test;

public class TestRunner {
    @Test
    public void testGetCost() {
        Product product = new Product("Тестовый товар", new Euro(10.00));
        AbstractPurchase purchase = new PriceDiscountPurchase(product, 3, new Euro(1.50));
        Euro expectedCost = new Euro(27.00);
        Assert.assertEquals(expectedCost, purchase.getCost());
    }

    @Test
    public void testToString() {
        Product product = new Product("Тестовый товар", new Euro(10.00));
        AbstractPurchase purchase = new PriceDiscountPurchase(product, 3, new Euro(1.50));
        String expectedString = "PriceDiscountPurchase;Тестовый товар;10.00;27.00;1.50";
        Assert.assertEquals(expectedString, purchase.toString());
    }
}
