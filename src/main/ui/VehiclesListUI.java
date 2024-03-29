package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JButton filterBtn = new JButton("Filter");
    private JTextField filterField = new JTextField(10);
    private DriveCostProGUI driveCostProGUI;
    private ArrayList<String> vehicleNames;

    // REQUIRES: driveCostProGUI is not null.
    // MODIFIES: this
    // EFFECTS: Constructs a VehiclesListUI, initializes the UI components,
    // and sets up action listeners for buttons.
    public VehiclesListUI(DriveCostProGUI driveCostProGUI) {
        this.driveCostProGUI = driveCostProGUI;
        vehicleNames = new ArrayList<>();
        setLayout();
        selectBtnListener();
        deleteBtnListener();
        backBtnListener();
        exitBtnListener();
        addBtnListener();
        filterBtnListener();
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
        listbox.setModel(model);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectBtn);
        buttonPanel.add(addBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);
        buttonPanel.add(exitBtn);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        JPanel filterPanel = new JPanel();
        filterPanel.add(new JLabel("Filter:"));
        filterPanel.add(filterField);
        filterPanel.add(filterBtn);
        mainPanel.add(filterPanel, BorderLayout.NORTH);
        updateVehicleList();
    }

    // MODIFIES: this
    // EFFECTS: Retrieves all vehicle names from the current user's list of vehicles
    // and stores them in the vehicleNames list.
    private void getAllVehicleNames() {
        vehicleNames.clear();
        for (Vehicle vehicle : driveCostProGUI.currentUser.getAllVehicles()) {
            vehicleNames.add(vehicle.getName());
        }
    }

    // MODIFIES: this
    // EFFECTS: Updates the list model with the names of vehicles stored in the
    // vehicleNames list. Clears the model before adding new elements.
    private void updateVehicleList() {
        model.clear();
        for (String name : vehicleNames) {
            model.addElement(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds an action listener to the select button that allows
    // the user to manage the selected vehicle.
    private void selectBtnListener() {
        selectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedVehicleName = listbox.getSelectedValue();
                if (selectedVehicleName != null) {
                    driveCostProGUI.vehicleManagement(selectedVehicleName);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a vehicle.", "No Vehicle Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the delete button that deletes the
    // selected vehicle from the user's list and updates the UI.
    private void deleteBtnListener() {
        deleteBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedVehicleName = listbox.getSelectedValue();
                if (selectedVehicleName != null) {
                    driveCostProGUI.deleteVehicle(selectedVehicleName);
                    getAllVehicleNames();
                    updateVehicleList();
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Please select a vehicle to delete.", "No Vehicle Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the add button that displays the UI
    // for adding a new vehicle.
    private void addBtnListener() {
        addBtn.addActionListener(new ActionListener() {
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
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.exit();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Adds an action listener to the filter button that updates the list
    // to show only vehicles matching the filter text. Filters based on whether vehicle
    // names contain the filter text, ignoring case.
    private void filterBtnListener() {
        filterBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String filterText = filterField.getText();
                ArrayList<String> filteredNames = new ArrayList<>();
                for (String name : vehicleNames) {
                    if (name.toLowerCase().contains(filterText.toLowerCase())) {
                        filteredNames.add(name);
                    }
                }
                model.clear();
                for (String name : filteredNames) {
                    model.addElement(name);
                }
            }
        });
    }

    // EFFECTS: Returns the main panel containing all UI components of the VehiclesListUI.
    public JPanel getPanel() {
        return mainPanel;
    }
}
