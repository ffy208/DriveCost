package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import model.*;

// Displays a list of the user's vehicles, offering functionalities for
// selection, addition, and deletion of vehicles.
// This class facilitates vehicle management by allowing users to interact
// with their fleet, enhancing the DriveCostPro's
// capabilities in vehicle tracking and management.
public class VehiclesListUI {
    private JPanel mainPanel;
    private JList<String> listbox;
    private JScrollPane scrollPane;
    private DefaultListModel<String> model;
    private JButton selectBtn = new JButton("Select");
    private JButton addBtn = new JButton("Add a Vehicle");
    private JButton deleteBtn = new JButton("Delete");
    private JButton backBtn = new JButton("Back");
    private JButton exitBtn = new JButton("Exit");
    private DriveCostProGUI driveCostProGUI;
    private ArrayList<String> vehicleNames;

    // REQUIRES: driveCostProGUI is not null.
    // MODIFIES: this
    // EFFECTS: Constructs a VehiclesListUI, initializes the UI components,
    // and sets up action listeners for buttons.
    public VehiclesListUI(DriveCostProGUI driveCostProGUI) {
        vehicleNames = new ArrayList<>();
        this.driveCostProGUI = driveCostProGUI;
        this.setLayout();
        this.selectBtnListener();
        this.deleteBtnListener();
        this.backBtnListener();
        this.exitBtnListener();
        this.addBtnListener();
    }


    // MODIFIES: this
    // EFFECTS: Initializes and sets up the layout of the main
    // panel including the list of vehicles and action buttons.
    public void setLayout() {
        mainPanel = new JPanel(new BorderLayout());
        listbox = new JList<>();
        scrollPane = new JScrollPane(listbox);
        model = new DefaultListModel<>();
        getAllVehicleNames();
        for (String name : vehicleNames) {
            model.addElement(name);
        }
        listbox.setModel(model);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);
        buttonPanel.add(exitBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private ArrayList<String> getAllVehicleNames() {
        vehicleNames = new ArrayList<>();
        for (Vehicle vehicle : driveCostProGUI.currentUser.getAllVehicles()) {
            vehicleNames.add(vehicle.getName());
        }
        return vehicleNames;
    }

    // MODIFIES: this
    // EFFECTS: Adds an action listener to the select button that allows the user to manage the selected vehicle.
    private void selectBtnListener() {
        selectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVehicleName = listbox.getSelectedValue();
                if (selectedVehicleName != null) {
                    driveCostProGUI.vehicleManagement(selectedVehicleName);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a vehicle.",
                            "No Vehicle Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the delete button that deletes the
    // selected vehicle from the user's list and updates the UI.
    private void deleteBtnListener() {
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedVehicleName = listbox.getSelectedValue();
                if (selectedVehicleName != null) {
                    driveCostProGUI.deleteVehicle(selectedVehicleName);
                    model.removeElement(selectedVehicleName); // Remove from UI
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a vehicle to delete.",
                            "No Vehicle Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the add button that displays the UI
    // for adding a new vehicle.
    private void addBtnListener() {
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayAddVehicleUI();
            }
        });
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the back button that returns the user
    // to the main functions UI.
    private void backBtnListener() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayMainFunctionsUI();
            }
        });
    }

    // MODIFIES: driveCostProGUI
    // EFFECTS: Adds an action listener to the exit button
    // that initiates the application's exit procedure.
    private void exitBtnListener() {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.exit();
            }
        });
    }


    // EFFECTS: Returns the main panel containing all UI components of the VehiclesListUI.
    public JPanel getPanel() {
        return mainPanel;
    }
}
