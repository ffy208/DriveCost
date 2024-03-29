package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Provides an interface for users to view and edit their account information
// within the DriveCostPro application.
// Users can update their username and other account-specific details.
// This class supports account management functionalities.
public class MyAccountUI {
    private JPanel mainPanel;
    private JButton saveBtn = new JButton("Save");
    private JButton deleteBtn = new JButton("Delete My Account");
    private JButton backBtn = new JButton("Back");
    private JButton exitBtn = new JButton("Exit");
    private DriveCostProGUI driveCostProGUI;
    private User currentUser;
    private JLabel nameLabel;
    private JLabel changeNameLabel;
    private final int fieldSize = 14;
    private JTextField nameField = new JTextField(fieldSize);

    // REQUIRES: driveCostProGUI is not null and currentUser is a valid, logged-in User object.
    // MODIFIES: this
    // EFFECTS: Constructs a MyAccountUI, initializes the UI components,
    // and sets up listeners for the buttons.
    public MyAccountUI(DriveCostProGUI driveCostProGUI, User currentUser) {
        this.driveCostProGUI = driveCostProGUI;
        this.currentUser = currentUser;
        this.setLayout();
        saveBtnListener();
        backBtnListener();
        exitBtnListener();
        deleteBtnListener();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the layout for the account management UI,
    // including name display and change, and action buttons.
    public void setLayout() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        nameLabel = new JLabel("User Name: " + currentUser.getUserName());
        mainPanel.add(nameLabel, gbc);
        changeNameLabel = new JLabel("Change User Name to: ");
        mainPanel.add(changeNameLabel, gbc);
        nameField = new JTextField();
        mainPanel.add(nameField, gbc);
        mainPanel.add(saveBtn, gbc);
        mainPanel.add(deleteBtn, gbc);
        mainPanel.add(backBtn, gbc);
        mainPanel.add(exitBtn, gbc);
    }

    // MODIFIES: currentUser
    // EFFECTS: Adds an action listener to the save button that
    // updates the username of the current user if the input is
    // not empty. Displays a message indicating success or failure.
    private void saveBtnListener() {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newUsername = nameField.getText();
                if (newUsername.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "New user name can not be empty");
                } else {
                    currentUser.setUserName(newUsername.trim());
                    JOptionPane.showMessageDialog(null, "User name updated successfully");
                }
                driveCostProGUI.displayMyAccountUI();
            }
        });
    }

    // MODIFIES: driveCostProGUI
    // EFFECTS: Adds an action listener to the delete account button
    // that triggers the account deletion process in driveCostProGUI.

    private void deleteBtnListener() {
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.deleteAccount();
            }
        });
    }

    // MODIFIES: driveCostProGUI
    // EFFECTS: Adds an action listener to the back button that
    // returns the user to the main functions UI.
    private void backBtnListener() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayMainFunctionsUI();
            }
        });
    }

    // MODIFIES: driveCostProGUI
    // EFFECTS: Adds an action listener to the exit button that initiates the
    // exit procedure of the application through driveCostProGUI.
    private void exitBtnListener() {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.exit();
            }
        });
    }

    // EFFECTS: Returns the main panel containing all UI components for account management.
    public JPanel getPanel() {
        return mainPanel;
    }
}
