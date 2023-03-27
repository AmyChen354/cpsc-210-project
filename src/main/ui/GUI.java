package ui;

import javax.swing.*;

public class GUI {
    JFrame frame;
    JLabel nameLabel;
    JLabel costLabel;
    JLabel expiryDateLabel;
    JButton button;
    JTextField nameField;
    JTextField costField;
    JTextField expiryDateField;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI() {
        frameComponents();
        labelComponents();
        buttonComponents();
        textFieldComponents();
    }

    private void frameComponents() {
        frame = new JFrame();
        frame.setBounds(300, 300, 500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    private void labelComponents() {
        nameLabel = new JLabel("Item name: ");
        costLabel = new JLabel("Item cost($): ");
        expiryDateLabel = new JLabel("Item expiry date(yyyymmdd): ");

        nameLabel.setBounds(20, 10, 100, 100);
        costLabel.setBounds(20, 70, 100, 100);
        expiryDateLabel.setBounds(20, 130, 200, 100);

        frame.add(nameLabel);
        frame.add(costLabel);
        frame.add(expiryDateLabel);
    }

    private void buttonComponents() {
        button = new JButton("Add item");

        button.setBounds(180, 230, 100, 50);
        button.setSize(100,40);

        frame.add(button);
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
}
