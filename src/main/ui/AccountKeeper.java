package ui;

import model.*;

import java.util.Scanner;

public class AccountKeeper {
    private AccountBook book;
    private ItemList newList;
    private Item item;
    private Scanner input;

    public AccountKeeper() {
        runKeeper();
    }

    private void init() {
        item = new Item("", 0.00, 0);
        newList = new ItemList();
        book = new AccountBook();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

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
                processCommand(command);
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

    private void processCommand(String command) {
        if (command.equals("n")) {
            createNewList();
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

    public void existingListMenu() {
        System.out.println("\t1 -> Record a spending");
        System.out.println("\t2 -> Manage your spending");
//        System.out.println("\t3 -> Track the expiry dates");
    }

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

    private void createNewList() {
        try {
            newList = new ItemList();
            System.out.println("Please enter the date of the list");
            Integer date = input.nextInt();
            input.nextLine();
            book.addList(date, newList);
        } catch (InvalidDateException e) {
            System.out.println("The date is invalid");
        }
    }

    private void existingListRecordSpending() {
        ItemList selectedList = newList;

        try {
            selectedList = selectItemList();
            Item newItem = new Item("", 0.00, 0);
            System.out.println("Please enter the NAME, COST($), and the EXPIRY DATE (yyyymmdd) of the item");
            recordName(newItem);
            recordCost(newItem);
            recordExpiryDate(newItem);

            selectedList.addItem(newItem);
            System.out.printf("You bought %s today, and it costs $%s, which expires at %s", newItem.getName(),
                    newItem.getCost(), newItem.getExpiryDate());
        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

    private void recordName(Item newItem) {
        String name = input.next();
        input.nextLine();
        newItem.assignItemName(name);
    }

    private void recordCost(Item newItem) {
        try {
            double cost = input.nextDouble();
            input.nextLine();
            newItem.assignItemCost(cost);
        } catch (NegativeCostException e) {
            System.out.println("The cost cannot be negative");
        }
    }

    private void recordExpiryDate(Item newItem) {
        try {
            int ed = input.nextInt();
            input.nextLine();
            newItem.assignItemExpiryDate(ed);
        } catch (InvalidDateException e) {
            System.out.println("The expiry date is invalid");
        }
    }

    private void manageSpending() {
        System.out.println("\t1 -> Remove a spending from a list");
//        System.out.println("\t2 -> Sort spending in ascending order");
//        System.out.println("\t3 -> Sort spending in descending order");
        System.out.println("\t4 -> Count the number of items bought");
        System.out.println("\t5 -> Calculate the total cost");
    }

    private void manageListCommand(String command) {
        manageSpending();
        command = input.next();

        if (command.equals("1")) {
            removeSpending();
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

    private void removeSpending() {
        ItemList selectedList = newList;
        try {
            selectedList = selectItemList();
            System.out.println("Please enter the NAME of the item you want to remove");
            String removeName = input.next();
            input.nextLine();

            if (selectedList.findItem(removeName) == null) {
                System.out.println("This item does not exist");
            } else {
                selectedList.removeItem(removeName);
                System.out.printf("%s has been removed!", removeName);
            }

        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

//    private void sortInAscending() {
//        Collections.sort(list);
//    }

//    private void sortInDescending() {
//    TODO
//    }

    private void countNum() {
        ItemList selectedList = newList;
        try {
            selectedList = selectItemList();
            System.out.printf("There are %s items in the list", selectedList.numOfItems());
        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

    private void calcTotalCost() {
        ItemList selectedList = newList;
        try {
            selectedList = selectItemList();
            System.out.printf("The total cost of this list is $%s", selectedList.sumOfCosts());
        } catch (ListNonexistentException e) {
            System.out.println("This list does not exist");
        }
    }

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


}
