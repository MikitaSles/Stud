package lt.ehu.lab0511exceptions.entity;

import lt.ehu.lab0511exceptions.PurchaseFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparing;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PurchaseListTest {
    private PurchaseList purchaseList;

    @BeforeClass
    public void setUp() {
        purchaseList = new PurchaseList("src/main/resources/lab0511exceptions/in.csv");
    }

    @Test(priority = 1)
    public void testInsert() {
        Purchase purchase = PurchaseFactory.createPurchase("soda;200;3");
        assertNotNull(purchase);
        int sizeBeforeInsert = purchaseList.getSize();
        purchaseList.insertPurchase(2, purchase);
        assertEquals(purchaseList.getSize(), sizeBeforeInsert + 1);
    }

    @Test(priority = 6)
    public void testDeleteSubsequence() {
        Purchase purchase = PurchaseFactory.createPurchase("soda;200;3");
        purchaseList.insertPurchase(2, purchase);
        purchaseList.insertPurchase(3, purchase);
        purchaseList.insertPurchase(4, purchase);
        int sizeBeforeDelete = purchaseList.getSize();
        purchaseList.deleteSubsequence(2, 4);
        assertEquals(purchaseList.getSize(), sizeBeforeDelete - 2);
    }

    @Test(priority = 2)
    public void testTotalCost() {
        // Manually calculate the total cost for comparison
        int expectedTotal = 0;
        for (Purchase purchase : purchaseList.getPurchases()) {
            expectedTotal += purchase.getTotalCost();
        }
        assertEquals(purchaseList.totalCost(), expectedTotal);
    }

    @Test(priority = 3)
    public void testToString() {
        StringBuilder sb = new StringBuilder();
        for (Purchase purchase : purchaseList.getPurchases()) {
            sb.append(purchase.toString()).append("\n");
        }
        assertEquals(purchaseList.toString(), sb.toString());
    }

    @Test(priority = 4)
    public void testSortPurchases() {
        // Make a manual copy of the list and sort it for comparison
        List<Purchase> expectedSortedList = new ArrayList<>(purchaseList.getPurchases());
        expectedSortedList.sort(comparing(Purchase::getProduct));
        purchaseList.sortPurchases();
        assertEquals(purchaseList.getPurchases(), expectedSortedList);
    }

    @Test(priority = 5)
    public void testSearchPurchase() {
        // Search for an existing Purchase
        Purchase searchFor = purchaseList.getPurchases().get(0);
        purchaseList.sortPurchases();
        int foundIndex = purchaseList.searchPurchase(searchFor);
        assertEquals(searchFor, purchaseList.getPurchases().get(foundIndex));
    }

    @AfterClass
    public void tearDown() {
        purchaseList = null;
    }
}