package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test for methods in ItemList class
class ItemListTest {
    private Item i1;
    private Item i2;
    private Item i3;
    private Item i4;
    private Item i5;

    private ItemList l1;
    private ItemList l2;


    @BeforeEach
    void runBefore() {
        i1 = new Item("apples", 5.50, 20230317);
        i2 = new Item("milk", 3.97, 20230320);
        i3 = new Item("broccoli", 4.00, 20230319);
        i4 = new Item("Lays chips", 2.19, 20230527);
        i5 = new Item("bread", 3.27, 20230405);

        l1 = new ItemList();
        l1.addItem(i1);

        l2 = new ItemList();
        l2.addItem(i1);
        l2.addItem(i2);
        l2.addItem(i3);
        l2.addItem(i4);
        l2.addItem(i5);
    }

    @Test
    public void numOfItemsTest() {
        l1.removeItem(i1);
        l2.removeItem(i3);

        assertEquals(0, l1.numOfItems());
        assertEquals(4, l2.numOfItems());
    }

    @Test
    public void sumOfCostsTest() {
        assertEquals(5.50, l1.sumOfCosts());
        assertEquals(18.93, l2.sumOfCosts());
    }
}