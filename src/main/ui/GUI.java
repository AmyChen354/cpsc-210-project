package ui;

import model.AccountBook;
import model.Item;
import model.ItemList;
import model.exceptions.InvalidDateException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JFrame frame;
    private JButton addButton;
    private JButton showButton;
    private JTextField nameField;
    private JTextField costField;
    private JTextField expiryDateField;
    private JTextArea area;
    private ItemList list;
    private AccountBook book;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        list = new ItemList();
        frameComponents();
//        labelComponents();
//        buttonComponents();
//        textFieldComponents();
//        textAreaComponents();
        menu();
//        showItems();
    }

    private void frameComponents() {
        frame = new JFrame();
        frame.setBounds(200, 100, 800, 700);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void labelComponents() {
        JLabel nameLabel = new JLabel("Item name: ");
        JLabel costLabel = new JLabel("Item cost($): ");
        JLabel expiryDateLabel = new JLabel("Item expiry date(yyyymmdd): ");

        nameLabel.setBounds(20, 10, 100, 100);
        costLabel.setBounds(20, 70, 100, 100);
        expiryDateLabel.setBounds(20, 130, 200, 100);

        frame.add(nameLabel);
        frame.add(costLabel);
        frame.add(expiryDateLabel);
    }

    private void buttonComponents() {
        addButton = new JButton("Add item");
        showButton = new JButton("Show items");

        addButton.setBounds(180, 230, 100, 50);
        addButton.setSize(100, 40);
        showButton.setBounds(180, 300, 100, 50);
        addButton.setSize(100, 40);

        frame.add(addButton);
        frame.add(showButton);
    }

    private void textFieldComponents() {
        nameField = new JTextField();
        costField = new JTextField();
        expiryDateField = new JTextField();

        nameField.setBounds(100, 50, 100, 30);
        costField.setBounds(110, 110, 100, 30);
        expiryDateField.setBounds(200, 170, 100, 30);

        frame.add(nameField);
        frame.add(costField);
        frame.add(expiryDateField);
    }

    private void textAreaComponents() {
        area = new JTextArea();

        area.setBounds(180, 400, 200, 200);

        frame.add(area);
    }

    private void menu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu existingListMenu = new JMenu("Existing list");


        addMenuItem(existingListMenu, new AddItemAction());
        menuBar.add(existingListMenu);

        frame.setJMenuBar(menuBar);
    }

    private void addMenuItem(JMenu menu, AbstractAction action) {
        JMenuItem menuItem = new JMenuItem(action);
        menu.add(menuItem);
    }

//    private void addItem() {
//        ActionListener l = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    String inputName = nameField.getText();
//                    Double inputCost = Double.parseDouble(costField.getText());
//                    Integer inputED = Integer.parseInt(expiryDateField.getText());
//
//                    Item i = new Item(inputName, inputCost, inputED);
//                    list.addItem(i);
//                    book = new AccountBook();
//                    book.addList(1, list);
//                    System.out.println("Added: " + inputName);
//                } catch (Exception e1) {
//                    System.out.println(e1);
//                }
//            }
//        };
//        addButton.addActionListener(l);
//    }

    private class AddItemAction extends AbstractAction {

        AddItemAction() {
            super("Add new item");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            JPanel panel = new JPanel();
            nameField = new JTextField(10);
            costField = new JTextField(10);
            expiryDateField = new JTextField(10);
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
                    Integer inputED = Integer.parseInt(expiryDateField.getText());

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

//    private void removeItem() {
//        ActionListener l = new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//
//                }
//            }
//        }
//    }

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
}
