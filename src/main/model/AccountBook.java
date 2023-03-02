package model;

import model.exceptions.InvalidDateException;

import java.util.*;

// Represents an account book with a list of item lists with specified dates
public class AccountBook {
    private Map<Integer, ItemList> book;

    // Constructs an empty account book set to book
    public AccountBook() {
        book = new HashMap<>();
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

    // EFFECTS: Returns all item lists in book as a string
    public String showLists() {
        Integer[] allLists = book.keySet().toArray(new Integer[0]);
        return Arrays.toString(allLists);
    }

    // EFFECTS: Returns an item list with the matching date,
    //          otherwise returns null
    public ItemList findList(Integer date) {
        if (book.containsKey(date)) {
            return book.get(date);
        } else {
            return null;
        }
    }
}
