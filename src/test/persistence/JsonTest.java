package persistence;

import model.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkItem(Item item, String name, Double cost, Integer expiryDate) {
        assertEquals(name, item.getName());
        assertEquals(cost, item.getCost());
        assertEquals(expiryDate, item.getExpiryDate());
    }
}
