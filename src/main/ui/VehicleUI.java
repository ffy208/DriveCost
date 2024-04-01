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
public class VehicleUI {
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    private JLabel purchaseCostLabel;
    private JLabel monthlyExpensesLabel;
    private JLabel otherExpensesLabel;
    private JLabel monthOwnedLabel;
    private JLabel currentMileageLabel;
    private JLabel gasolineCostLabel;
    private JLabel chargingCostLabel;
    private JLabel costPerKMLabel;

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

    private DecimalFormat df = new DecimalFormat("#.00");

    // MODIFIES: this
    // EFFECTS: Constructs a VehicleUI, initializes the UI components based
    // on the vehicle's type, and sets up action listeners for the buttons.
    public VehicleUI(DriveCostProGUI driveCostProGUI, Vehicle vehicle) {
        this.driveCostProGUI = driveCostProGUI;
        this.currentVehicle = vehicle;
        setLayout();
        saveBtnListener();
        backBtnListener();
        exitBtnListener();
    }

    // MODIFIES: this
    // EFFECTS: Initializes and sets up the layout of the main panel,
    // including dynamic panels for vehicle-specific fields.
    private void setLayout() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(typePanel(), gbc);
        mainPanel.add(costPerKMPanel(), gbc);
        mainPanel.add(namePanel(), gbc);
        mainPanel.add(purchaseCostPanel(), gbc);
        mainPanel.add(monthlyExpensesPanel(), gbc);
        mainPanel.add(otherExpensesPanel(), gbc);
        mainPanel.add(monthOwnedPanel(), gbc);
        mainPanel.add(currentMileagePanel(), gbc);

        if (currentVehicle instanceof GasolineCar) {
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

    // EFFECTS: Returns a panel containing the vehicle's type label.
    private JPanel typePanel() {
        JPanel panel = new JPanel();
        typeLabel = new JLabel("Vehicle type: " + currentVehicle.getVehicleType() + "    ");
        panel.add(typeLabel);
        return panel;
    }

    // EFFECTS: Returns a panel containing the vehicle's name label
    // and editable field.
    private JPanel namePanel() {
        JPanel panel = new JPanel();
        nameLabel = new JLabel("Vehicle Name: " + currentVehicle.getName() + "    ");
        panel.add(nameLabel);
        nameField.addFocusListener(new HintListener(nameField, "Change vehicle name"));
        panel.add(nameField);
        return panel;
    }

    // EFFECTS: Returns a panel with the vehicle's cost per kilometer, formatted.
    private JPanel costPerKMPanel() {
        JPanel costPanel = new JPanel();
        costPerKMLabel = new JLabel("Cost per KM: $" + df.format(currentVehicle.costPerKilometer()));
        costPanel.add(costPerKMLabel);
        mainPanel.add(costPanel);
        return costPanel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the vehicle's purchase
    // cost information, including a label and an editable field.
    private JPanel purchaseCostPanel() {
        JPanel panel = new JPanel();
        purchaseCostLabel = new JLabel("Purchase Cost: " + df.format(currentVehicle.getPurchaseCost()) + "    ");
        panel.add(purchaseCostLabel);
        purchaseCostField.addFocusListener(new HintListener(purchaseCostField, "Change purchase cost"));
        panel.add(purchaseCostField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the vehicle's monthly
    // expenses information, including a label and an editable field.
    private JPanel monthlyExpensesPanel() {
        JPanel panel = new JPanel();
        monthlyExpensesLabel = new JLabel("Monthly Expenses: "
                + df.format(currentVehicle.getMonthlyExpenses()) + "    ");
        panel.add(monthlyExpensesLabel);
        monthlyExpensesField.addFocusListener(new HintListener(monthlyExpensesField, "Change monthly expenses"));
        panel.add(monthlyExpensesField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the vehicle's other expenses
    // information, including a label and an editable field.
    private JPanel otherExpensesPanel() {
        JPanel panel = new JPanel();
        otherExpensesLabel = new JLabel("Other Expenses: " + df.format(currentVehicle.getOtherExpenses()) + "    ");
        panel.add(otherExpensesLabel);
        otherExpensesField.addFocusListener(new HintListener(otherExpensesField, "Change other expenses"));
        panel.add(otherExpensesField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the vehicle's months owned
    // information, including a label and an editable field.
    private JPanel monthOwnedPanel() {
        JPanel panel = new JPanel();
        monthOwnedLabel = new JLabel("Months Owned: " + currentVehicle.getMonthsOwned() + "    ");
        panel.add(monthOwnedLabel);
        monthOwnedField.addFocusListener(new HintListener(monthOwnedField, "Change months owned"));
        panel.add(monthOwnedField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the vehicle's
    // current mileage information, including a label and an editable field.
    private JPanel currentMileagePanel() {
        JPanel panel = new JPanel();
        currentMileageLabel = new JLabel("currentMileage: " + currentVehicle.getCurrentMileage() + "    ");
        panel.add(currentMileageLabel);
        currentMileageField.addFocusListener(new HintListener(currentMileageField, "Change current mileage"));
        panel.add(currentMileageField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the gasoline cost for a
    // GasolineCar, including a label and an editable field
    private JPanel gasolineCostPanel() {
        JPanel panel = new JPanel();
        gasolineCostLabel = new JLabel("Gasoline Cost: "
                + df.format(((GasolineCar) currentVehicle).getGasolineCost()) + "    ");
        panel.add(gasolineCostLabel);
        gasolineCostField.addFocusListener(new HintListener(gasolineCostField, "Change gasoline cost"));
        panel.add(gasolineCostField);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a JPanel containing the charging cost for an
    // ElectricCar, including a label and an editable field.
    private JPanel chargingCostPanel() {
        JPanel panel = new JPanel();
        chargingCostLabel = new JLabel("Charging Cost: "
                + df.format(((ElectricCar) currentVehicle).getChargingCost()) + "    ");
        panel.add(chargingCostLabel);
        chargingCostField.addFocusListener(new HintListener(chargingCostField, "Change charging cost"));
        panel.add(chargingCostField);
        return panel;
    }

    // MODIFIES: this, currentVehicle
    // EFFECTS: Adds an action listener to the save button that validates
    // input, updates the vehicle's information, and refreshes the UI.
    private void saveBtnListener() {
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateAndParseInputs()) {
                    updateVehicleInfo();
                    driveCostProGUI.vehicleManagement(currentVehicle.getName());
                }
            }
        });
    }

    // EFFECTS: Updates the currentVehicle's information with the values
    // from the input fields.
    private void updateVehicleInfo() {
        String vehicleName = nameField.getText().trim();
        double purchaseCost = Double.parseDouble(purchaseCostField.getText().trim());
        double monthlyExpenses = Double.parseDouble(monthlyExpensesField.getText().trim());
        double otherExpenses = Double.parseDouble(otherExpensesField.getText().trim());
        int monthsOwned = Integer.parseInt(monthOwnedField.getText().trim());
        int currentMileage = Integer.parseInt(currentMileageField.getText().trim());
        currentVehicle.setName(vehicleName);
        currentVehicle.setPurchaseCost(purchaseCost);
        if (currentVehicle instanceof GasolineCar) {
            double gasolineCost = Double.parseDouble(gasolineCostField.getText());
            ((GasolineCar) currentVehicle).updateVehicleInfo(monthlyExpenses, otherExpenses,
                    monthsOwned, gasolineCost, currentMileage);
        } else {
            double chargingCost = Double.parseDouble(chargingCostField.getText());
            ((ElectricCar) currentVehicle).updateVehicleInfo(monthlyExpenses, otherExpenses,
                    monthsOwned, chargingCost, currentMileage);
        }
    }

    // EFFECTS: Validates the inputs from all editable fields. Returns true
    // if all inputs are valid; false otherwise.
    private boolean validateAndParseInputs() {
        try {
            if (nameField.getText().trim().isEmpty()
                    || !isValidDouble(purchaseCostField.getText().trim())
                    || !isValidDouble(monthlyExpensesField.getText().trim())
                    || !isValidDouble(otherExpensesField.getText().trim())
                    || !isValidInt(monthOwnedField.getText().trim())
                    || !isValidInt(currentMileageField.getText().trim())
                    || (currentVehicle instanceof GasolineCar && !isValidDouble(gasolineCostField.getText().trim()))
                    || (currentVehicle instanceof ElectricCar && !isValidDouble(chargingCostField.getText().trim()))) {
                JOptionPane.showMessageDialog(mainPanel,
                        "Please check your inputs.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(mainPanel,
                    "Please check your inputs.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    // EFFECTS: Checks if the input string can be parsed as a double.
    // Returns true if yes; false otherwise.
    private boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // EFFECTS: Checks if the input string can be parsed as an integer.
    // Returns true if yes; false otherwise.
    private boolean isValidInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // MODIFIES: this, driveCostProGUI
    // EFFECTS: Adds an action listener to the back button that displays
    // the vehicle list UI.
    private void backBtnListener() {
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.displayVehicleList();
            }
        });
    }

    // MODIFIES: driveCostProGUI
    // EFFECTS: Adds an action listener to the exit button that triggers
    // the application's exit procedure.
    private void exitBtnListener() {
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                driveCostProGUI.saveAndExitOption();
            }
        });
    }

    // EFFECTS: Returns the main panel containing all UI components of the VehicleUI.
    public JPanel getPanel() {
        return mainPanel;
    }
}


