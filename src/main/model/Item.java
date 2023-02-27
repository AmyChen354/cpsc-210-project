package model;

// Represents an item having a name, cost (in dollars) and expiry date
public class Item implements Comparable<Item> {
    private String name;      // the name of the item
    private double cost;      // the cost of the item
    private int expiryDate;   // the expiry date of the item(e.g. 2023/02/28 would be 20230228)

    public Item(String itemName, Double itemCost, int itemED) {
        name = itemName;
        cost = itemCost;
        expiryDate = itemED;
    }

    // REQUIRES: Assumes that newName cannot be an empty string
    // MODIFIES: this
    // EFFECTS: Assign a name to the item
    public void assignItemName(String newName) {
        name = newName;
    }

    // Assumes newCost > 0
    // MODIFIES: this
    // EFFECTS: Assign a cost to the item
    public void assignItemCost(Double newCost) throws NegativeCostException {
        if (cost < 0) {
            throw new NegativeCostException();
        }
        cost = newCost;
    }

    // MODIFIES: this
    // EFFECTS: Assign an expiry date to the item
    public void assignItemExpiryDate(int newItemED) throws InvalidDateException {
        if (expiryDate < 0) {
            throw new InvalidDateException();
        }
        expiryDate = newItemED;
    }

    // EFFECTS: Returns the name of the item
    public String getName() {
        return name;
    }

    // EFFECTS: Returns the cost of the item
    public double getCost() {
        return cost;
    }

    // EFFECTS: Returns the expiry date of the item
    public int getExpiryDate() {
        return expiryDate;
    }

    @Override
    public int compareTo(Item i) {
        if (cost == i.getCost()) {
            return 0;
        } else if (cost > i.getCost()) {
            return 1;
        } else {
            return -1;
        }
    }
}
