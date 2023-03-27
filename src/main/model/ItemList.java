package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.lang.reflect.WildcardType;
import java.util.*;

// Represents a list of item
public class ItemList implements Writable {
    private List<Item> itemList;   //the list of the item bought on a certain day

    // EFFECTS: Constructs an empty item list set to itemList
    public ItemList() {
        itemList = new ArrayList<>();
    }

    // REQUIRES: The list does not already contain an item with the same name
    // MODIFIES: this
    // EFFECTS: Adds i to the list
    public void addItem(Item i) {
        itemList.add(i);
    }

    // REQUIRES: The list already contain an i with removeName
    // MODIFIES: this
    // EFFECTS: Searches for an i with matching removeName and remove it from itemList
    public void removeItem(String removeName) {
        for (int i = 0; i < itemList.size(); i++) {
            if (removeName.equals(itemList.get(i).getName())) {
                itemList.remove(i);
            }
        }
    }

    // EFFECTS: Constructs an empty list set to allItems;
    //          if itemList.size != 0 then add the name of all items in itemList to allItems
    //          and returns allItems as a string;
    //          otherwise returns null
    public String showItems() {
        List<String> allItems = new ArrayList<>();

        if (!(itemList.size() == 0)) {
            for (int i = 0; i < itemList.size(); i++) {
                allItems.add(itemList.get(i).getName());
            }
            return allItems.toString();
        } else {
            return null;
        }
    }

    // EFFECTS: Searches for an i in the itemList with matching name and returns i;
    //          otherwise returns null
    public Item findItem(String name) {
        for (Item item : itemList) {
            if (name.equals(item.getName())) {
                return item;
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
        for (Item item : itemList) {
            sum += item.getCost();
        }
        return sum;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("items", itemsToJson());
        return json;
    }

    // EFFECTS: Returns items in an item list as a JSON array
    public JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : itemList) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
