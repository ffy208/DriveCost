package ui;

import model.User;
import model.UserDatabase;
import model.Vehicle;
import persistence.JsonDatabaseReader;
import persistence.JsonDatabaseWriter;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// Serves as the main graphical user interface for the DriveCostPro application.
// It orchestrates user interactions for managing a vehicle fleet, including
// adding, viewing, and deleting vehicles, and viewing and updating vehicle
// and user information. This class acts as the central hub for navigation
// and user interface interaction.
public class DriveCostProGUI extends JFrame {
    private static final String JSON_STORE_DATABASE = "./data/UserDatabase.json";
    private UserDatabase userDatabase;
    protected User currentUser;
    private JsonDatabaseWriter jsonDatabaseWriter;
    private JsonDatabaseReader jsonDatabaseReader;
    private final String version = "DriveCostPro Ver.1.0_a";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private LoginUI loginUI;
    private MainFunctionsUI mainFunctionsUI;
    private VehiclesListUI vehiclesListUI;
    private AddVehicleUI addVehicleUI;
    private MyAccountUI myAccountUI;

    // MODIFIES: this
    // EFFECTS: Constructs the main GUI/frame for this application and connects different panels.
    public DriveCostProGUI() {
        super();
        userDatabase = new UserDatabase();
        this.setTitle(version);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        jsonDatabaseWriter = new JsonDatabaseWriter(JSON_STORE_DATABASE);
        jsonDatabaseReader = new JsonDatabaseReader(JSON_STORE_DATABASE);
        runApp();
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * //@param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                userDatabase.printLog();
                System.exit(0);
            }

            /**
             * Invoked when a window has been closed.
             *
             * //@param e
             */
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Frame closed");
            }
        });

        this.setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: Prompts the user to choose between loading data or continuing without loading.
    //          Initializes the UI based on the user's choice.
    private void runApp() {
        int result = JOptionPane.showConfirmDialog(null, "Do you want to load data from file?");
        if (result == JOptionPane.YES_OPTION) {
            loadDataAndContinue();
        } else {
            continueWithoutLoading();
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the UserDatabase from a file and displays the login UI.
    // Shows a message based on the success of data loading.
    protected void loadDataAndContinue() {
        loadUserDatabase();
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Directly displays the login UI
    // without attempting to load data from file.
    protected void continueWithoutLoading() {
        displayLoginUI();
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Loads UserDatabase from the specified file. If successful, shows
    // a confirmation message and displays the login UI. Otherwise, shows an
    // error message and displays the login UI.
    private void loadUserDatabase() {
        try {
            userDatabase = jsonDatabaseReader.readDatabase();
            JOptionPane.showMessageDialog(null, "Data has been successfully loaded");
            displayLoginUI();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Data loading failed");
            displayLoginUI();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the login UI panel.
    protected void displayLoginUI() {
        loginUI = new LoginUI(this);
        setContentPane(loginUI.getPanel());
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: logs in a user if the username exists, setting the current user to the logged-in user
    public void loginVerify(String username) {
        User user = userDatabase.findUser(username);
        if (user != null) {
            JOptionPane.showMessageDialog(null, "Login successful. Welcome, " + username + "!");
            this.currentUser = user;
            displayMainFunctionsUI();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. User not found.");
            displayLoginUI();
        }

    }


    // MODIFIES: this
    // EFFECTS: registers a new user with a username if the username does not already exist
    public void registerVerify(String username) {
        if (userDatabase.registerUser(username)) {
            JOptionPane.showMessageDialog(null,
                    "Registration successful. You can now log in with your username.");
            displayLoginUI();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Registration failed. Username already exists.");
            displayLoginUI();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the main functions UI panel.
    protected void displayMainFunctionsUI() {
        mainFunctionsUI = new MainFunctionsUI(this);
        setContentPane(mainFunctionsUI.getPanel());
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Displays the vehicle list UI panel.
    public void displayVehicleList() {
        vehiclesListUI = new VehiclesListUI(this);
        setContentPane(vehiclesListUI.getPanel());
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: Displays the add vehicle UI panel, allowing the user to select between GasolineCar and ElectricCar.
    //          Does nothing if the user cancels the operation.
    public void displayAddVehicleUI() {
        Object[] options = {"GasolineCar", "ElectricCar"};
        int result = JOptionPane.showOptionDialog(null, "Choose Your vehicle type", null,
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
        if (result == 0) {
            addVehicleUI = new AddVehicleUI(this, 0);
        } else if (result == 1) {
            addVehicleUI = new AddVehicleUI(this, 1);
        } else {
            // Back nothing and return to the app
        }
        setContentPane(addVehicleUI.getPanel());
        this.validate();
        this.repaint();
    }

    // MODIFIES: currentUser
    // EFFECTS: Adds a new vehicle to the current user's vehicle list and
    // displays the vehicle list UI panel with a confirmation message.
    public void addNewVehicle(Vehicle newVehicle) {
        currentUser.addVehicle(newVehicle);
        JOptionPane.showMessageDialog(null,
                "This vehicle has been successfully added.");
        displayVehicleList();
    }

    // MODIFIES: this
    // EFFECTS: Displays the vehicle management UI for the vehicle
    // with the given name.
    public void vehicleManagement(String vehicleName) {
        Vehicle vehicle = currentUser.findVehicleByName(vehicleName);
        VehicleUI vehicleUI = new VehicleUI(this, vehicle);
        setContentPane(vehicleUI.getPanel());
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: deletes the selected vehicle from the current user's vehicle list if confirmed
    public void deleteVehicle(String vehicleName) {
        Vehicle vehicle = currentUser.findVehicleByName(vehicleName);
        int result = JOptionPane.showConfirmDialog(null, "Do you want to delete " + vehicleName + "?");
        if (result == JOptionPane.YES_OPTION) {
            if (currentUser.removeVehicle(vehicle)) {
                JOptionPane.showMessageDialog(null,
                        "This vehicle has been successfully deleted.");
                displayVehicleList();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Failed to delete " + vehicleName + ".");
                displayVehicleList();
            }
        } else {
            displayVehicleList();
        }
    }

    // EFFECTS: Prompts the user to save data and exit. Saves the UserDatabase
    // to file if confirmed, then exits. Exits directly if saving is declined.
    // Returns to the app if canceled.
    public void saveAndExitOption() {
        int result = JOptionPane.showConfirmDialog(null, "Do you want to save the data and exit?");
        if (result == JOptionPane.YES_OPTION) {
            saveUserDatabase();
            userDatabase.printLog();
            System.exit(0);
        } else if (result == JOptionPane.NO_OPTION) {
            userDatabase.printLog();
            System.exit(0);
        } else {
            // Back nothing and return to the app
        }
    }

    // MODIFIES: JSON_STORE_DATABASE
    // EFFECTS: saves the UserDatabase to file
    private void saveUserDatabase() {
        try {
            jsonDatabaseWriter.open();
            jsonDatabaseWriter.write(userDatabase);
            jsonDatabaseWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Updated UserDatabase to " + JSON_STORE_DATABASE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to write to file: " + JSON_STORE_DATABASE);
            saveAndExitOption();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the "My Account" UI panel.
    public void displayMyAccountUI() {
        myAccountUI = new MyAccountUI(this, currentUser);
        setContentPane(myAccountUI.getPanel());
        this.validate();
        this.repaint();
    }

    // MODIFIES: this
    // EFFECTS: deletes the current user
    public void deleteAccount() {
        int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete your account,"
                + currentUser.getUserName() + "? \nThis operation cannot be undone.");
        if (result == JOptionPane.YES_OPTION) {
            if (userDatabase.removeUser(currentUser)) {
                //deleteUserFile();
                currentUser = null;
                saveUserDatabase();
                JOptionPane.showMessageDialog(null,
                        "Your account has been successfully deleted.");
                displayLoginUI();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Failed to delete your account.");
                displayMyAccountUI();
            }
        } else {
            displayMyAccountUI();
        }
    }

//    // MODIFIES: File system
//    // EFFECTS: Deletes the current user's data file.
//    // Prints a message indicating success or failure.
//    private void deleteUserFile() {
//        File file = new File(jsonStore);
//        if (file.delete()) {
//            System.out.println("Deleted user data file: " + jsonStore);
//        } else {
//            System.out.println("Failed to delete user data file: " + jsonStore);
//        }
//    }
}
