package model;

import model.exceptions.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for methods in AccountBook class
public class AccountBookTest {
    // Item lists:
    private ItemList l1;
    private ItemList l2;
    private ItemList l3;

    // Account books:
    private AccountBook ab1;
    private AccountBook ab2;

    @BeforeEach
    void runBefore() throws InvalidDateException {
        // Item lists:
        l1 = new ItemList();
        l2 = new ItemList();
        l3 = new ItemList();

        // Account books:
        ab1 = new AccountBook();
        ab1.addList(20230226, l1);

        ab2 = new AccountBook();
        ab2.addList(20230226, l1);
        ab2.addList(20231205, l2);
    }

    @Test
    public void addListNoExceptionTest() {
        try {
            ab1.addList(20230802, l2);
        } catch (InvalidDateException e) {
            fail("Unexpected InvalidDateException thrown");
        }
        assertEquals(2, ab1.numOfLists());
    }

    @Test
    public void addListExceptionTest() {
        try {
            ab2.addList(-1, l3);
            fail("Expected a InvalidDateException is to be thrown");
        } catch (InvalidDateException e) {
            assertEquals(2, ab2.numOfLists());
        }
    }

    @Test
    public void showListsTest() {
        assertEquals("[20230226]", ab1.showDates());
        assertEquals("[20231205, 20230226]", ab2.showDates());
    }

    @Test
    public void findListTest() {
        assertEquals(l1, ab1.findList(20230226));
        assertEquals(l2, ab2.findList(20231205));
    }

    @Test
    public void cannotFindListTest() {
        assertNull(ab1.findList(20231205));
        assertNull(ab2.findList(20231128));
    }

}
