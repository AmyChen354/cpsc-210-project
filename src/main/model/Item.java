package model;

import model.exceptions.NegativeCostException;
import org.json.JSONObject;
import persistence.Writable;

// Represents an item having a name, cost (in dollars) and expiry date
public class Item implements Writable {
    private String name;      // the name of the item
    private double cost;      // the cost of the item
    private int expiryDate;   // the expiry date of the item(e.g. 2023/02/28 would be 20230228)

    // EFFECTS: Constructs an item with given name, cost in dollars, and expiry date in the format yyyymmdd
    public Item(String itemName, Double itemCost, int itemED) {
        this.name = itemName;
        this.cost = itemCost;
        this.expiryDate = itemED;
    }

    // REQUIRES: Assumes that newName cannot be an empty string
    // MODIFIES: this
    // EFFECTS: Assigns a name to the item
    public void setItemName(String newName) {
        this.name = newName;
    }

    // Assumes newCost > 0
    // MODIFIES: this
    // EFFECTS: Assigns a cost to the item
    public void setItemCost(Double newCost) {
        this.cost = newCost;
    }

    // MODIFIES: this
    // EFFECTS: Assigns an expiry date to the item
    public void setItemExpiryDate(int newItemED) {
        this.expiryDate = newItemED;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getExpiryDate() {
        return expiryDate;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("cost", cost);
        json.put("expiry date", expiryDate);
        return json;
    }

}
