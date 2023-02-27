package model;

import java.util.*;

// Represents a list of item
public class ItemList {
    //    private int date;   //date of the item list
    private List<Item> itemList;   //the list of the item bought on a certain day

    public ItemList() {
        itemList = new ArrayList<>();
    }

    // REQUIRES: The list does not already contain an item with the same name
    // MODIFIES: this
    // EFFECTS: Adds i to the list
    public void addItem(Item i) {
        itemList.add(i);
    }

    // REQUIRES: The list already contain an item with the same name
    // MODIFIES: this
    // EFFECTS: Remove i from the list
    public void removeItem(String removeName) {
        for (int i = 0; i < itemList.size(); i++) {
            if (removeName.equals(itemList.get(i).getName())) {
                itemList.remove(i);
            }
        }
    }

    public String showItems() {
        for (Item i : itemList) {
            String allItems = i.getName();
        }
    }

    public Item findItem(String name) {
        for (Item i : itemList) {
            if (name.equals(i.getName())) {
                return i;
            } else {
                return null;
            }
        }
        return null;
    }

    // EFFECTS: Returns the number of items in the list
    public int numOfItems() {
        return itemList.size();
    }

    // EFFECTS: Returns the sum of the costs of all items in the list
    public double sumOfCosts() {
        double sum = 0;
        for (Item i : itemList) {
            sum += i.getCost();
        }
        return sum;
    }

//    public void sortInAscending(ItemList itemList) {
//        Collections.sort(itemList);
//    }

}
