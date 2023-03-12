package persistence;

import model.AccountBook;
import model.Item;
import model.ItemList;
import model.exceptions.InvalidDateException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

 public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AccountBook ab = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyAccountBook() {
        try {
            AccountBook ab = new AccountBook();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyAccountBook.json");
            writer.open();
            writer.write(ab);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyAccountBook.json");
            ab = reader.read();
            assertEquals(0, ab.numOfLists());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralAccountBook() {
        try {
            Item i1 = new Item("apples", 1.25, 20230217);
            Item i2 = new Item("eggs", 2.00, 20230728);
            Item i3 = new Item("milk", 5.39, 20230407);

            ItemList l1 = new ItemList();
            l1.addItem(i1);
            l1.addItem(i2);
            ItemList l2 = new ItemList();
            l2.addItem(i3);

            AccountBook ab = new AccountBook();
            ab.addList(20230504, l1);
            ab.addList(20231219, l2);

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralAccountBook.json");
            writer.open();
            writer.write(ab);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralAccountBook.json");
            ab = reader.read();
            assertEquals(2, ab.numOfLists());
            assertEquals(2, l1.numOfItems());
            assertEquals(1, l2.numOfItems());
            checkItem(i1, "apples", 1.25, 20230217);
            checkItem(i2, "eggs", 2.00, 20230728);
            checkItem(i3, "milk", 5.39, 20230407);
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (InvalidDateException e) {
            fail("InvalidDateException should not have been thrown");
        }
    }
}
