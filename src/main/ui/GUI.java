package ui;

import misc.SplashDemo;
import model.AccountBook;
import model.Item;
import model.ItemList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class GUI {
    private static JFrame frame;
    private JButton addButton;
    private JButton showButton;
    private JTextArea area;
    private ItemList list;
    private AccountBook book;
    private static final String JSON_STORE = "D:/AccountBook.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public static void main(String[] args) {
        frame = new JFrame();
        new GUI();
        new SplashScreen(frame, "Welcome");
    }

    public GUI() {
        list = new ItemList();
        book = new AccountBook();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        frameComponents();
//        labelComponents();
//        buttonComponents();
//        textFieldComponents();
//        textAreaComponents();
        menu();
//        showItems();
    }

    private void frameComponents() {
//        frame = new JFrame();
        frame.setBounds(200, 100, 800, 700);
        frame.setLayout(null);
//        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    private void labelComponents() {
//        JLabel nameLabel = new JLabel("Item name: ");
//        JLabel costLabel = new JLabel("Item cost($): ");
//        JLabel expiryDateLabel = new JLabel("Item expiry date(yyyymmdd): ");
//
//        nameLabel.setBounds(20, 10, 100, 100);
//        costLabel.setBounds(20, 70, 100, 100);
//        expiryDateLabel.setBounds(20, 130, 200, 100);
//
//        frame.add(nameLabel);
//        frame.add(costLabel);
//        frame.add(expiryDateLabel);
//    }
//
//    private void buttonComponents() {
//        addButton = new JButton("Add item");
//        showButton = new JButton("Show items");
//
//        addButton.setBounds(180, 230, 100, 50);
//        addButton.setSize(100, 40);
//        showButton.setBounds(180, 300, 100, 50);
//        addButton.setSize(100, 40);
//
//        frame.add(addButton);
//        frame.add(showButton);
//    }
//
//    private void textFieldComponents() {
//        JTextField nameField = new JTextField();
//        JTextField costField = new JTextField();
//        JTextField expiryDateField = new JTextField();
//
//        nameField.setBounds(100, 50, 100, 30);
//        costField.setBounds(110, 110, 100, 30);
//        expiryDateField.setBounds(200, 170, 100, 30);
//
//        frame.add(nameField);
//        frame.add(costField);
//        frame.add(expiryDateField);
//    }
//
//    private void textAreaComponents() {
//        area = new JTextArea();
//
//        area.setBounds(180, 400, 200, 200);
//
//        frame.add(area);
//    }

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

        addMenuItem(existingListMenu, new SelectListAction());
        newListMenu.addMouseListener(addListAction);
        addMenuItem(existingListMenu, new AddItemAction());
        addMenuItem(existingListMenu, new RemoveItemAction());
        addMenuItem(existingListMenu, new CountItemsAction());
        saveMenu.addMouseListener(saveAction);
        loadMenu.addMouseListener(loadAction);

        frame.setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu menu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        menu.add(menuItem);
    }

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
                    System.out.println("Created date: " + date);
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

    private class SelectListAction extends AbstractAction {

        SelectListAction() {
            super("Select an existing list");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            String allDates = book.showDates().replace("[", "")
                    .replace("]", "").replace(" ", "");
            String[] lists = allDates.split(",");
            String selectedDate = (String) JOptionPane.showInputDialog(null,
                     "Select a date: ", "Select the date of the list you would like to process",
                    JOptionPane.QUESTION_MESSAGE, null, lists, lists[0]);

//            if (selectedDate != null) {
//                int date = Integer.parseInt(selectedDate);
//                return book.findList(date);
//            }
//            return null;

            System.out.println("Selected " + selectedDate);
        }
    }

//    public ItemList selectListEvent() {
//        ActionListener l = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent event) {
//                String allDates = book.showDates().replace("[", "")
//                        .replace("]", "").replace(" ", "");
//                String[] lists = allDates.split(",");
//                String selectedDate = (String) JOptionPane.showInputDialog(null,
//                        "Select a date: ", "Select the date of the list you would like to process",
//                        JOptionPane.QUESTION_MESSAGE, null, lists, lists[0]);
//                int date = Integer.parseInt(selectedDate);
//                ItemList list = book.findList(date);
//            }
//        };
//        return list;
//    }

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

            int itemInfo = JOptionPane.showConfirmDialog(null,
                    panel, "Please enter the info of item", JOptionPane.OK_CANCEL_OPTION);
            try {
                if (itemInfo == JOptionPane.OK_OPTION) {
                    String inputName = nameField.getText();
                    Double inputCost = Double.parseDouble(costField.getText());
                    int inputED = Integer.parseInt(expiryDateField.getText());

                    Item i = new Item(inputName, inputCost, inputED);
                    list.addItem(i);
                    book = new AccountBook();
                    book.addList(1, list);
                    System.out.println("Added: " + inputName);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

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

            int removeName = JOptionPane.showConfirmDialog(null,
                    panel, "Please enter the name of the item you would like to remove",
                    JOptionPane.OK_CANCEL_OPTION);

            try {
                if (removeName == JOptionPane.OK_OPTION) {
                    String inputName = nameField.getText();
                    list.removeItem(inputName);
                    System.out.println("Removed: " + inputName);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private class CountItemsAction extends AbstractAction {

        CountItemsAction() {
            super("Count the number of items");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel panel = new JPanel();
            JTextField dateField = new JTextField(10);
            panel.add(new JLabel("Date: "));
            panel.add(dateField);

            int selectedDate = JOptionPane.showConfirmDialog(null,
                    panel, "Please enter the date of the list (yyyymmdd) you would like to process",
                    JOptionPane.OK_CANCEL_OPTION);
            try {
                if (selectedDate == JOptionPane.OK_OPTION) {
                    int date = Integer.parseInt(dateField.getText());
                    ItemList selectedList = book.findList(date);
                    int num = selectedList.numOfItems();
                    System.out.println("There are" + num + "items in the list");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private void showItems() {
        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    area.setText(list.showItems());
                } catch (Exception e1) {
                    System.out.println(e1);
                }
            }
        };
        showButton.addActionListener(l);
    }

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
