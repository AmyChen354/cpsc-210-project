package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test for methods in the Item class
public class ItemTest {

    private Item i1;
    private Item i2;


    @BeforeEach
    void runBefore() {
        i1 = new Item("apples", 5.50, 20230317);
        i2 = new Item("milk", 3.97, 20230320);
    }

    @Test
    public void setItemNameTest() {
        i1.setItemName("oranges");
        i2.setItemName("coke");

        assertEquals("oranges", i1.getName());
        assertEquals("coke", i2.getName());
    }

    @Test
    public void setItemCostTest() {
        i1.setItemCost(29.48);
        i2.setItemCost(237.00);

        assertEquals(29.48, i1.getCost());
        assertEquals(237.00, i2.getCost());
    }

    @Test
    public void setItemExpiryDateTest() {
        i1.setItemExpiryDate(20240918);
        i2.setItemExpiryDate(20271201);

        assertEquals(20240918, i1.getExpiryDate());
        assertEquals(20271201, i2.getExpiryDate());
    }

}
