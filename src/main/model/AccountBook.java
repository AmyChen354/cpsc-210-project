package model;

import java.util.*;

public class AccountBook {
    private Map<Integer, ItemList> book;

    public AccountBook() {
        book = new HashMap<>();
    }

    public void addList(Integer date, ItemList list) throws InvalidDateException {
        if (date < 0) {
            throw new InvalidDateException();
        }
        book.put(date, list);
    }

    public int numOfLists() {
        return book.size();
    }

    public String showLists() {
        Integer[] allLists = book.keySet().toArray(new Integer[0]);
        return Arrays.toString(allLists);
    }

    public ItemList findList(Integer date) {
        if (book.containsKey(date)) {
            return book.get(date);
        } else {
            return null;
        }
    }
}
