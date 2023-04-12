package model;

import model.exceptions.InvalidDateException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

// Represents an account book with a list of item lists with specified dates
public class AccountBook {

    private Map<Integer, ItemList> book;

    // Constructs an empty account book
    public AccountBook() {
        book = new HashMap<>();
    }

    // EFFECTS: book getter
    public Map<Integer, ItemList> getBook() {
        return book;
    }

    // MODIFIES: this
    // EFFECTS: Adds an item list to book with date specified
    //          if date < 0, throws InvalidDateException
    public void addList(Integer date, ItemList list) throws InvalidDateException {
        if (date < 0) {
            throw new InvalidDateException();
        }
        book.put(date, list);
    }

    // EFFECTS: Returns the number of item lists in book
    public int numOfLists() {
        return book.size();
    }

    // EFFECTS: Returns all dates in book as a string
    public String showDates() {
        Integer[] allDates = book.keySet().toArray(new Integer[0]);
        return Arrays.toString(allDates);
    }

    // EFFECTS: Returns an item list with the matching date,
    //          otherwise returns null
    public ItemList findList(Integer date) {
        if (book.containsKey(date)) {
            EventLog.getInstance().logEvent(new Event("Selected date: " + date));
            return book.get(date);
        } else {
            return null;
        }
    }

    public JSONArray toJson() {
        JSONArray array = new JSONArray();

        for (Map.Entry<Integer, ItemList> accountBook : book.entrySet()) {
            JSONObject json = new JSONObject();
            Integer date = accountBook.getKey();
            ItemList items = accountBook.getValue();
            JSONArray itemList = items.itemsToJson();

            json.put("date", String.valueOf(date));
            json.put("items", itemList);
            array.put(json);
        }
        return array;
    }

}
