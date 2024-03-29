package ui;

import model.ElectricCar;
import model.GasolineCar;
import model.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

// Provides a form interface for adding a new vehicle to the DriveCostPro
// application. It supports input for both gasoline
// and electric vehicle types, capturing essential details like purchase cost,
// monthly expenses, and specific costs related
// to the vehicle type (e.g., gasoline or charging costs).
public class AddVehicleUI {
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel purchaseCostLabel;
    private JLabel monthlyExpensesLabel;
    private JLabel otherExpensesLabel;
    private JLabel monthOwnedLabel;
    private JLabel currentMileageLabel;
    private JLabel gasolineCostLabel;
    private JLabel chargingCostLabel;
    private final int fieldSize = 14;
    private JTextField nameField = new JTextField(fieldSize);
    private JTextField purchaseCostField = new JTextField(fieldSize);
    private JTextField monthlyExpensesField = new JTextField(fieldSize);
    private JTextField otherExpensesField = new JTextField(fieldSize);
    private JTextField monthOwnedField = new JTextField(fieldSize);
    private JTextField currentMileageField = new JTextField(fieldSize);
    private JTextField gasolineCostField = new JTextField(fieldSize);
    private JTextField chargingCostField = new JTextField(fieldSize);
    private JButton saveBtn = new JButton("Save");
    private JButton backBtn = new JButton("Back");
    private JButton exitBtn = new JButton("Exit");
    private DriveCostProGUI driveCostProGUI;
    private Vehicle currentVehicle;
    private String vehicleType;
    private DecimalFormat df = new DecimalFormat("#.00");

    // REQUIRES: driveCostProGUI is not null, and typeIndex is either 0
    // (for GasolineCar) or 1 (for ElectricCar).
    // MODIFIES: this
    // EFFECTS: Constructs an AddVehicleUI, initializes the UI components based
    // on the selected vehicle type, and sets up action listeners for the buttons.
    public AddVehicleUI(DriveCostProGUI driveCostProGUI, int typeIndex) {
        if (typeIndex == 0) {
            vehicleType = "GasolineCar";
        } else {
            vehicleType = "ElectricCar";
        }
        this.driveCostProGUI = driveCostProGUI;
        setLayout();
        saveBtnListener();
        backBtnListener();
        exitBtnListener();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the layout of the main panel,
    // including fields for vehicle attributes and action buttons.
    private void setLayout() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(namePanel(), gbc);
        mainPanel.add(purchaseCostPanel(), gbc);
        mainPanel.add(monthlyExpensesPanel(), gbc);
        mainPanel.add(otherExpensesPanel(), gbc);
        mainPanel.add(monthOwnedPanel(), gbc);
        mainPanel.add(currentMileagePanel(), gbc);

        if (vehicleType == "GasolineCar") {
            mainPanel.add(gasolineCostPanel(), gbc);
        } else {
            mainPanel.add(chargingCostPanel(), gbc);
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveBtn);
        buttonPanel.add(backBtn);
        buttonPanel.add(exitBtn);
        mainPanel.add(buttonPanel, gbc);
    }


    // EFFECTS: Creates and returns a JPanel for entering the vehicle's name.
    private JPanel namePanel() {
        JPanel panel = new JPanel();
        nameLabel = new JLabel("Vehicle Name: ");
        panel.add(nameLabel);
        nameField.addFocusListener(new HintListener(nameField, "Set vehicle name"));
        panel.add(nameField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering the purchase
    // cost of the vehicle.
    private JPanel purchaseCostPanel() {
        JPanel panel = new JPanel();
        purchaseCostLabel = new JLabel("Purchase Cost: ");
        panel.add(purchaseCostLabel);
        purchaseCostField.addFocusListener(new HintListener(purchaseCostField, "Set purchase cost"));
        panel.add(purchaseCostField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering the
    // monthly expenses associated with the vehicle.
    private JPanel monthlyExpensesPanel() {
        JPanel panel = new JPanel();
        monthlyExpensesLabel = new JLabel("Monthly Expenses: ");
        panel.add(monthlyExpensesLabel);
        monthlyExpensesField.addFocusListener(new HintListener(monthlyExpensesField, "Set monthly expenses"));
        panel.add(monthlyExpensesField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering any other
    // expenses associated with the vehicle.
    private JPanel otherExpensesPanel() {
        JPanel panel = new JPanel();
        otherExpensesLabel = new JLabel("Other Expenses: ");
        panel.add(otherExpensesLabel);
        otherExpensesField.addFocusListener(new HintListener(otherExpensesField, "Set other expenses"));
        panel.add(otherExpensesField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering the number
    // of months the vehicle has been owned.
    private JPanel monthOwnedPanel() {
        JPanel panel = new JPanel();
        monthOwnedLabel = new JLabel("Months Owned: ");
        panel.add(monthOwnedLabel);
        monthOwnedField.addFocusListener(new HintListener(monthOwnedField, "Set months owned"));
        panel.add(monthOwnedField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering
    // the current mileage of the vehicle.
    private JPanel currentMileagePanel() {
        JPanel panel = new JPanel();
        currentMileageLabel = new JLabel("currentMileage: ");
        panel.add(currentMileageLabel);
        currentMileageField.addFocusListener(new HintListener(currentMileageField, "Set current mileage"));
        panel.add(currentMileageField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering the gasoline cost
    //         for GasolineCar vehicles。 This panel is only applicable if
    //         the vehicle being added is a GasolineCar.
    private JPanel gasolineCostPanel() {
        JPanel panel = new JPanel();
        gasolineCostLabel = new JLabel("Gasoline Cost: ");
        panel.add(gasolineCostLabel);
        gasolineCostField.addFocusListener(new HintListener(gasolineCostField, "Set gasoline cost"));
        panel.add(gasolineCostField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel for entering the charging cost
    // for ElectricCar vehicles. This panel is only applicable if the vehicle
    // being added is an ElectricCar.
    private JPanel chargingCostPanel() {
        JPanel panel = new JPanel();
        chargingCostLabel = new JLabel("Charging Cost: ");
        panel.add(chargingCostLabel);
        chargingCostField.addFocusListener(new HintListener(chargingCostField, "Set charging cost"));
        panel.add(chargingCostField);
        return panel;
    }

    // MODIFIES: this, currentVehicle, driveCostProGUI
    // EFFECTS: Adds an action listener to the save button that creates a new
    // vehicle with the specified attributes and adds it to the system.
    private void saveBtnListener() {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vehicleName = nameField.getText();
                double purchaseCost = Double.parseDouble(purchaseCostField.getText());
                double monthlyExpenses = Double.parseDouble(monthlyExpensesField.getText());
                double otherExpenses = Double.parseDouble(otherExpensesField.getText());
                int monthsOwned = Integer.parseInt(monthOwnedField.getText());
                int currentMileage = Integer.parseInt(currentMileageField.getText());

                if (vehicleType == "GasolineCar") {
                    currentVehicle = new GasolineCar(vehicleName, purchaseCost);
                    double gasolineCost = Double.parseDouble(gasolineCostField.getText());
                    ((GasolineCar) currentVehicle).updateVehicleInfo(monthlyExpenses,
                            otherExpenses, monthsOwned, gasolineCost, currentMileage);
                } else {
                    currentVehicle = new ElectricCar(vehicleName, purchaseCost);
                    double chargingCost = Double.parseDouble(chargingCostField.getText());
                    ((ElectricCar) currentVehicle).updateVehicleInfo(monthlyExpenses,
                            otherExpenses, monthsOwned, chargingCost, currentMileage);
                }
                driveCostProGUI.addNewVehicle(currentVehicle);
            }
        });
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the back button that navigates back
    // to the previous UI.
    private void backBtnListener() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayVehicleList();
            }
        });
    }


    // MODIFIES: driveCostProGUI
    // EFFECTS: Adds an action listener to the exit button
    // that triggers the application's exit procedure.
    private void exitBtnListener() {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.exit();
            }
        });
    }

    // EFFECTS: Returns the main panel containing all UI components of the AddVehicleUI.
    public JPanel getPanel() {
        return mainPanel;
    }

    // EFFECTS: Returns the newly created vehicle.
    public Vehicle getNewVehicle() {
        return this.currentVehicle;
    }
}


