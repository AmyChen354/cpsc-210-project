package ui;

import model.AccountBook;
import model.Item;
import model.ItemList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

// Represents application's main window frame
public class AccountBookUI {
    private static JFrame frame;
    private AccountBook book;
    private static final String JSON_STORE = "D:/AccountBook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        frame = new JFrame();
        new AccountBookUI();
        new SplashScreen(frame, "Welcome");
    }

    // EFFECTS: Constructor sets up the components of main window: frame, writer and reader, and menu
    public AccountBookUI() {
        book = new AccountBook();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        frameComponents();
        menu();
    }

    // EFFECTS: Sets up frame with bounds and layout
    private void frameComponents() {
        frame.setBounds(400, 100, 800, 700);
        frame.setLayout(null);
        JLabel label = new JLabel();
        label.setBounds(150, 250, 800, 100);
        label.setText(
                "<html><font size = '6'><color = black></b>Welcome to your personal account book!</font>");
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // EFFECTS: Sets up menu bar with different menus as well as menu items
    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu newListMenu = new JMenu("New list");
        menuBar.add(newListMenu);
        JMenu existingListMenu = new JMenu("Existing list");
        menuBar.add(existingListMenu);
        JMenu saveMenu = new JMenu("Save");
        menuBar.add(saveMenu);
        JMenu loadMenu = new JMenu("Load");
        menuBar.add(loadMenu);

        newListMenu.addMouseListener(addListAction);
        addMenuItem(existingListMenu, new AddItemAction());
        addMenuItem(existingListMenu, new RemoveItemAction());
        addMenuItem(existingListMenu, new CountItemsAction());
        addMenuItem(existingListMenu, new ShowItemsAction());
        saveMenu.addMouseListener(saveAction);
        loadMenu.addMouseListener(loadAction);

        frame.setJMenuBar(menuBar);
    }

    // EFFECTS: Helper to add menu items with given handler to the given menu
    private void addMenuItem(JMenu menu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        menu.add(menuItem);
    }

    // EFFECTS: Represents action to be taken when user want to add a new list to the account book
    MouseListener addListAction = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent event) {
            JPanel panel = new JPanel();
            JTextField dateField = new JTextField(10);
            panel.add(new JLabel("Date: "));
            panel.add(dateField);

            int newDate = JOptionPane.showConfirmDialog(null,
                    panel, "Enter the date of the list", JOptionPane.OK_CANCEL_OPTION);

            try {
                if (newDate == JOptionPane.OK_OPTION) {
                    int date = Integer.parseInt(dateField.getText());
                    ItemList newList = new ItemList();
                    book.addList(date, newList);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };

    // EFFECTS: Represents action to be taken before user want to manage an existing list
    public ItemList selectListEvent() {
        String allDates = book.showDates().replace("[", "")
                .replace("]", "").replace(" ", "");
        String[] lists = allDates.split(",");
        String selectedDate = (String) JOptionPane.showInputDialog(null,
                "Select a date: ", "Select the date of the list you would like to process",
                JOptionPane.QUESTION_MESSAGE, null, lists, lists[0]);
        int date = Integer.parseInt(selectedDate);
        return book.findList(date);
    }

    // EFFECTS: Represents action to be taken when user want to add a new item to a selected date
    private class AddItemAction extends AbstractAction {

        AddItemAction() {
            super("Add new item");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel panel = new JPanel();
            JTextField nameField = new JTextField(10);
            JTextField costField = new JTextField(10);
            JTextField expiryDateField = new JTextField(10);
            panel.add(new JLabel("Item name: "));
            panel.add(nameField);
            panel.add(new JLabel("Item cost($): "));
            panel.add(costField);
            panel.add(new JLabel("Item expiry date(yyyymmdd): "));
            panel.add(expiryDateField);

            ItemList list = selectListEvent();
            int itemInfo = JOptionPane.showConfirmDialog(null,
                    panel, "Enter the details of item", JOptionPane.OK_CANCEL_OPTION);

            if (itemInfo == JOptionPane.OK_OPTION) {
                String inputName = nameField.getText();
                Double inputCost = Double.parseDouble(costField.getText());
                int inputED = Integer.parseInt(expiryDateField.getText());

                Item i = new Item(inputName, inputCost, inputED);
                list.addItem(i);
            }
        }
    }

    // EFFECTS: Represents action to be taken when user want to remove an item from a selected date
    private class RemoveItemAction extends AbstractAction {

        RemoveItemAction() {
            super("Remove an item");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel panel = new JPanel();
            JTextField nameField = new JTextField(10);
            panel.add(new JLabel("Item name: "));
            panel.add(nameField);
            ItemList list = selectListEvent();
            int removeName = JOptionPane.showConfirmDialog(null,
                    panel, "Enter the name of the item to remove",
                    JOptionPane.OK_CANCEL_OPTION);

            try {
                if (removeName == JOptionPane.OK_OPTION) {
                    String inputName = nameField.getText();
                    list.removeItem(inputName);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // EFFECTS: Represents action to be taken when user want to count the number of items on a selected date
    private class CountItemsAction extends AbstractAction {

        CountItemsAction() {
            super("Count the number of items");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            ItemList list = selectListEvent();
            int num = list.numOfItems();
            JOptionPane.showMessageDialog(null,
                    "You have " + num + " item(s) in the list");
        }
    }

    // EFFECTS: Represents action to be taken when user want to view all the items on a selected date
    private class ShowItemsAction extends AbstractAction {

        ShowItemsAction() {
            super("View items in the list");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            ItemList list = selectListEvent();
            JPanel panel = new JPanel();
            JTextArea area = new JTextArea();
            area.setBounds(80, 100, 100, 100);
            String s = list.showItems().replace("[", "")
                    .replace("]", "").replace(", ", "\n")
                    .replace(" ", "   ");
            area.setText(s);
            panel.add(area);

            JOptionPane.showMessageDialog(null, panel,
                    "View items", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // EFFECTS: Represents action to be taken when user want to save the account book to a directory
    MouseListener saveAction = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent event) {
            try {
                jsonWriter.open();
                jsonWriter.write(book);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null,
                        "Account book has been saved to " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };

    // EFFECTS: Represents action to be taken when user want to load the account book from a directory where it was
    //          saved
    MouseListener loadAction = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent event) {
            try {
                book = jsonReader.read();
                JOptionPane.showMessageDialog(null,
                        "Loaded Account book from " + JSON_STORE);
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    };


}
