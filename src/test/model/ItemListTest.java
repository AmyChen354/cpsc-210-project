package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Test for methods in ItemList class
public class ItemListTest {
    private Item i1;
    private Item i2;
    private Item i3;
    private Item i4;
    private Item i5;

    private ItemList l1;
    private ItemList l2;
    private ItemList l3;


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

        l3 = new ItemList();
    }

    @Test
    public void showItemsTest() {
        assertEquals("[apples $5.5 20230317]", l1.showItems());
        assertEquals("[apples $5.5 20230317, milk $3.97 20230320, broccoli $4.0 20230319, " +
                "Lays chips $2.19 20230527, bread $3.27 20230405]", l2.showItems());
    }

    @Test
    public void showItemsEmptyListTest() {
        assertNull(l3.showItems());
    }

    @Test
    public void findItemTest() {
        assertEquals(i1, l1.findItem("apples"));
        assertEquals(i5, l2.findItem("bread"));
    }

    @Test
    public void cannotFindItemTest() {
        assertNull(l1.findItem("bread"));
        assertNull(l2.findItem("cake"));
        assertNull(l3.findItem("broccoli"));
    }

    @Test
    public void numOfItemsTest() {
        l1.removeItem("apples");
        l2.removeItem("broccoli");

        assertEquals(0, l1.numOfItems());
        assertEquals(4, l2.numOfItems());
        assertEquals(0, l3.numOfItems());
    }

    @Test
    public void sumOfCostsTest() {
        assertEquals(5.50, l1.sumOfCosts());
        assertEquals(18.93, l2.sumOfCosts());
        assertEquals(0.00, l3.sumOfCosts());
    }
}