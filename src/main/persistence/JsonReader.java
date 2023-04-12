package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads account book from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: Constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Reads account book from file and returns it;
    // Throws IOException if an error occurs reading data from file
    public AccountBook read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        EventLog.getInstance().logEvent(new Event("Read account book"));
        return parseAccountBook(jsonArray);
    }

    // EFFECTS: Reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Parses account book from JSON array and returns it
    private AccountBook parseAccountBook(JSONArray jsonArray) {
        AccountBook ab = new AccountBook();
        addDates(ab, jsonArray);
        return ab;
    }

    // MODIFIES: ab
    // EFFECTS: Parses items from JSON array and adds it to account book
    private void addDates(AccountBook ab, JSONArray jsonArray) {
        for (Object object : jsonArray) {
            Integer date = ((JSONObject) object).getInt("date");
            JSONArray items = ((JSONObject) object).getJSONArray("items");
            ItemList list = new ItemList();
            for (Object json : items) {
                JSONObject nextItem = (JSONObject) json;
                addItem(list, nextItem);
            }
            ab.getBook().put(date, list);
        }

    }

    // MODIFIES: list
    // EFFECTS: Parses item from JSON object and adds it to item list
    private void addItem(ItemList list, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Double cost = jsonObject.getDouble("cost");
        Integer expiryDate = jsonObject.getInt("expiry date");
        Item item  = new Item(name, cost, expiryDate);
        list.addItem(item);
    }
}
