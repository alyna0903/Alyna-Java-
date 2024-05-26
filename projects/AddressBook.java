import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class AddressBook extends JFrame {
    private ArrayList<Contact> contacts;
    private JTextField nameField, phoneField, emailField;
    private JList<String> contactList;
    private DefaultListModel<String> listModel;
    private JLabel nameLabel, phoneLabel, emailLabel;

    public AddressBook() {
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);

        contacts = new ArrayList<>();

        // Create GUI components
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);
        inputPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(e -> addContact());

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(e -> deleteContact());

        JButton deleteContactButton = new JButton("Delete");
        deleteContactButton.addActionListener(e -> deleteSelectedContact());

        listModel = new DefaultListModel<>();
        contactList = new JList<>(listModel);
        contactList.addListSelectionListener(e -> updateContactDetails());
        JScrollPane scrollPane = new JScrollPane(contactList);

        JPanel detailsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        nameLabel = new JLabel();
        phoneLabel = new JLabel();
        emailLabel = new JLabel();
        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(nameLabel);
        detailsPanel.add(new JLabel("Phone:"));
        detailsPanel.add(phoneLabel);
        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(emailLabel);

        // Layout the components
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.WEST);
        add(deleteButton, BorderLayout.CENTER);
        add(deleteContactButton, BorderLayout.EAST);
        add(scrollPane, BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addContact() {
        String name = nameField.getText().trim();
        String phone = phoneField.getText().trim();
        String email = emailField.getText().trim();

        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            Contact contact = new Contact(name, phone, email);
            contacts.add(contact);
            listModel.addElement(contact.getName());
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        }
    }

    private void deleteContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex >= 0) {
            contacts.remove(selectedIndex);
            listModel.removeElementAt(selectedIndex);
            updateContactDetails();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.");
        }
    }

    private void deleteSelectedContact() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex >= 0) {
            contacts.remove(selectedIndex);
            listModel.removeElementAt(selectedIndex);
            updateContactDetails();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a contact to delete.");
        }
    }

    private void updateContactDetails() {
        int selectedIndex = contactList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Contact contact = contacts.get(selectedIndex);
            nameLabel.setText(contact.getName());
            phoneLabel.setText(contact.getPhone());
            emailLabel.setText(contact.getEmail());
        } else {
            nameLabel.setText("");
            phoneLabel.setText("");
            emailLabel.setText("");
        }
    }

    private static class Contact {
        private String name;
        private String phone;
        private String email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddressBook::new);
    }
}