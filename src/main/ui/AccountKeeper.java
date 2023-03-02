package ui;

import model.*;
import model.exceptions.InvalidDateException;
import model.exceptions.ListNonexistentException;
import model.exceptions.NegativeCostException;
import model.exceptions.NoItemsException;

import java.util.Scanner;

// Account keeper application
public class AccountKeeper {
    private AccountBook book;
    private Scanner input;

    // EFFECTS: Runs the account keeper application
    public AccountKeeper() {
        runKeeper();
    }

    // MODIFIES: this
    // EFFECTS: Initializes account book
    private void init() {
        book = new AccountBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: Processes user input
    private void runKeeper() {
        boolean keepGoing = true;
        String command = null;
        init();

        while (keepGoing) {
            mainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMainCommand(command);
            }
        }
        System.out.println("\nThanks for using!");
    }

    // EFFECTS: Displays menu of options to user
    private void mainMenu() {
        System.out.println("\nWelcome! Please select from:");
        System.out.println("\tn -> Create a new list");
        System.out.println("\te -> Existing list");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: Processes user main command
    private void processMainCommand(String command) {
        if (command.equals("n")) {
            ItemList newList = new ItemList();
            createNewList(newList);
        } else if (command.equals("e")) {
            if (book.numOfLists() <= 0) {
                System.out.println("You don't have any item list in the account book yet, "
                        + "please start with creating a new item list");
            } else {
                existingListCommand(command);
            }
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: Displays menu of options for an existing date to user
    public void existingListMenu() {
        System.out.println("\t1 -> Record a spending");
        System.out.println("\t2 -> Manage your spending");
//        System.out.println("\t3 -> Track the expiry dates");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for an existing date
    private void existingListCommand(String command) {
        existingListMenu();
        command = input.next();

        if (command.equals("1")) {
            existingListRecordSpending();
        } else if (command.equals("2")) {
            manageListCommand(command);
//        } else if (command.equals("3")) {
//        TODO
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates a new date by prompting user to enter a date
    private void createNewList(ItemList newList) {
        try {
            System.out.println("Please enter the date of the list");
            Integer date = input.nextInt();
            input.nextLine();
            book.addList(date, newList);
            System.out.printf("You have created a list dated %s %n", date);
        } catch (InvalidDateException e) {
            System.out.println("The date is invalid");
        }
    }

    // MODIFIES: this
    // EFFECTS: Record a new item to an item list by prompting user to enter the
    //          name, cost, and expiry date of it
    private void existingListRecordSpending() {
        Item newItem = new Item("", 0.00, 0);

        try {
            ItemList selectedList = selectItemList();
            System.out.println("Please enter the NAME, COST($), and the EXPIRY DATE (yyyymmdd) of the item");

            recordName(newItem);
            recordCost(newItem, newItem.getCost());
            recordExpiryDate(newItem, newItem.getExpiryDate());

            selectedList.addItem(newItem);
            System.out.printf("You bought %s today, and it costs $%s, which expires at %s %n", newItem.getName(),
                    newItem.getCost(), newItem.getExpiryDate());

        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        } catch (NegativeCostException e) {
            System.out.println("The cost cannot be negative");
        } catch (InvalidDateException e) {
            System.out.println("The expiry date is invalid");
        }
    }

    // MODIFIES: newItem
    // EFFECTS: Record the entered name of a new item
    private void recordName(Item newItem) {
        String name = input.next();
        input.nextLine();
        newItem.setItemName(name);
    }

    // MODIFIES: newItem
    // EFFECTS: Record the entered cost of a new item;
    //          if newCost < 0, then throws NegativeCostException
    private void recordCost(Item newItem, Double newCost) throws NegativeCostException {
        newCost = input.nextDouble();
        input.nextLine();
        if (newCost < 0) {
            throw new NegativeCostException();
        }
        newItem.setItemCost(newCost);
    }

    // MODIFIES: newItem
    // EFFECTS: Record the entered expiry date of a new item;
    //          if newED < 0, then throws InvalidDateException
    private void recordExpiryDate(Item newItem, Integer newED) throws InvalidDateException {
        newED = input.nextInt();
        input.nextLine();
        if (newED < 0) {
            throw new InvalidDateException();
        }
        newItem.setItemExpiryDate(newED);
    }

    // EFFECTS: Displays menu of managing a date to user
    private void manageListMenu() {
        System.out.println("\t1 -> Remove a spending from a list");
//        System.out.println("\t2 -> Sort spending in ascending order");
//        System.out.println("\t3 -> Sort spending in descending order");
        System.out.println("\t4 -> Count the number of items bought");
        System.out.println("\t5 -> Calculate the total cost");
    }

    // MODIFIES: this
    // EFFECTS: Processes user command for managing a date
    private void manageListCommand(String command) {
        manageListMenu();
        command = input.next();

        if (command.equals("1")) {
            removeItem();
//            } else if (command.equals("2")) {
//                sortInAscending();
//            } else if (command.equals("3")) {
//                sortInDescending();
        } else if (command.equals("4")) {
            countNum();
        } else if (command.equals("5")) {
            calcTotalCost();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS:Removes an item bought with matching name from a date the user selected
    private void removeItem() {
        try {
            ItemList selectedList = selectItemList();

            try {
                showItems(selectedList);
                System.out.println("Please enter the NAME of the item you want to remove");
                String removeName = input.next();
                input.nextLine();

                if (selectedList.findItem(removeName) == null) {
                    System.out.println("This item does not exist");
                } else {
                    selectedList.removeItem(removeName);
                    System.out.printf("%s has been removed! %n", removeName);
                }

            } catch (NoItemsException e) {
                System.out.println("This list don't have any item yet");
            }

        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

//    private void sortInAscending() {
//    TODO
//    }

//    private void sortInDescending() {
//    TODO
//    }

    // EFFECTS: Prints the number of items bought on a date the user selected
    private void countNum() {
        try {
            ItemList selectedList = selectItemList();
            System.out.printf("There are %s items in the list %n", selectedList.numOfItems());
        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

    // EFFECTS: Prints the total cost of items bought on a date the user selected
    private void calcTotalCost() {
        try {
            ItemList selectedList = selectItemList();
            System.out.printf("The total cost of this list is $%s %n", selectedList.sumOfCosts());
        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

    // EFFECTS: Displays all dates in book and prompts user to select a date from it to proceed;
    //          if cannot find the date from the book, then throws ListNonexistentException
    private ItemList selectItemList() throws ListNonexistentException {
        System.out.printf("You have these dates in your account book: %n %s %n", book.showLists());
        Integer selectDate = 0;

        while (!(selectDate > 0)) {
            System.out.println("Please enter the date of the list (yyyymmdd) you would like to process");
            selectDate = input.nextInt();
            input.nextLine();
        }

        if (book.findList(selectDate) == null) {
            throw new ListNonexistentException();
        }
        return book.findList(selectDate);
    }

    // EFFECTS: Prints all items bought on a date the user selected;
    //          if no items bought on a date, then throws NoItemException
    private void showItems(ItemList selectedList) throws NoItemsException {
        if (selectedList.showItems() == null) {
            throw new NoItemsException();
        }
        System.out.printf("You have these items in this list: %n %s %n", selectedList.showItems());
    }


}
