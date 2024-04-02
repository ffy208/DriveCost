package ui;

import model.*;
import persistence.JsonDatabaseReader;
import persistence.JsonDatabaseWriter;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Drive Cost Pro application
public class DriveCostProApp {
    private static final String JSON_STORE_DATABASE = "./data/UserDatabase.json";
    private String jsonStore;
    private UserDatabase userDatabase;
    private User currentUser;
    private final Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JsonDatabaseWriter jsonDatabaseWriter;
    private JsonDatabaseReader jsonDatabaseReader;
    private boolean saved = false;

    // EFFECTS: constructs a DriveCostPro application, initializes the user list and scanner for input,
    //          then runs the application
    public DriveCostProApp() throws FileNotFoundException {
        userDatabase = new UserDatabase();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonDatabaseWriter = new JsonDatabaseWriter(JSON_STORE_DATABASE);
        jsonDatabaseReader = new JsonDatabaseReader(JSON_STORE_DATABASE);
        runApp();
    }

    // EFFECTS: runs the main loop of this application
    public void runApp() {
        boolean keepGoing = true;
        loadUserDatabase();
        while (keepGoing) {
            if (currentUser == null) {
                keepGoing = loginMenuAndInput();
            } else {
                keepGoing = userMenuAndInput();
            }
        }
        if (saved) {
            saveUserDatabase();
        }
        System.out.println("\nGoodbye!");
    }

    // EFFECTS: call display login menu and processes user input for it
    private boolean loginMenuAndInput() {
        displayLoginMenu();
        String command = input.next().toLowerCase();
        switch (command) {
            case "r":
                registerUser();
                break;
            case "l":
                loginUser();
                break;
            case "q":
                return false;
            default:
                System.out.println("Invalid command");
                break;
        }
        return true;
    }

    // EFFECTS: call display user menu and processes user input for it
    private boolean userMenuAndInput() {
        displayUserMenu();
        String command = input.next().toLowerCase();
        if (command.equals("v")) {
            viewVehicles();
        } else if (command.equals("a")) {
            addVehicle();
        } else if (command.equals("s")) {
            saveUser();
        } else if (command.equals("l")) {
            loadUser();
        } else if (command.equals("d")) {
            deleteUser();
        } else if (command.equals("q")) {
            return false;
        } else {
            System.out.println("Invalid command");
        }
        return true;
    }

    // EFFECTS: saves the User to file
    private void saveUser() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentUser);
            jsonWriter.close();
            System.out.println("Saved " + currentUser.getUserName() + " to " + jsonStore);
            this.saved = true;
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + jsonStore);
        }

    }

    // EFFECTS: saves the UserDatabase to file
    private void saveUserDatabase() {
        try {
            jsonDatabaseWriter.open();
            jsonDatabaseWriter.write(userDatabase);
            jsonDatabaseWriter.close();
            System.out.println("Updated UserDatabase to " + JSON_STORE_DATABASE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_DATABASE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads User from file
    private void loadUser() {
        try {
            currentUser = jsonReader.read();
            System.out.println("Loaded " + currentUser.getUserName() + " from " + jsonStore);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + jsonStore);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads UserDatabase from file
    private void loadUserDatabase() {
        try {
            userDatabase = jsonDatabaseReader.readDatabase();
            System.out.println("Loaded UserDatabase from " + JSON_STORE_DATABASE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_DATABASE);
        }
    }

    // EFFECTS: displays login menu of options for user
    private void displayLoginMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tr -> register");
        System.out.println("\tl -> login");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays user menu of options for user
    private void displayUserMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> view all my vehicles");
        System.out.println("\ta -> add a vehicle");
        System.out.println("\ts -> save my data to file");
        System.out.println("\tl -> load my data from file");
        System.out.println("\td -> delete account");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: displays the list of vehicles owned by the current user
    private void viewVehicles() {
        if (currentUser.getAllVehicles().isEmpty()) {
            System.out.println("Your Vehicles list is empty.");
            return;
        }
        displayVehicleList();
        handleVehicleSelection();
    }

    // EFFECTS: prints the list of vehicles owned by the current user to the console
    private void displayVehicleList() {
        System.out.println("Your Vehicles: \n");
        int index = 1;
        for (Vehicle vehicle : currentUser.getAllVehicles()) {
            System.out.println(index + "->" + vehicle.getName());
            index++;
        }
    }

    // EFFECTS: handles user selection of a vehicle from the list for management
    private void handleVehicleSelection() {
        System.out.println("Select a vehicle by number to manage it, or enter b to go back: ");
        String selection = input.next();
        if (selection.equals("b")) {
            return;
        }
        try {
            int vehicleIndex = Integer.parseInt(selection) - 1;
            if (vehicleIndex >= 0 && vehicleIndex < currentUser.getAllVehicles().size()) {
                manageVehicle(currentUser.getAllVehicles().get(vehicleIndex));
            } else {
                System.out.println("Invalid vehicle selection");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    // EFFECTS: enters a management loop for user to manage their vehicles.
    public void manageVehicle(Vehicle vehicle) {
        boolean keepGoing = true;
        while (keepGoing) {
            displayVehicleManagementOptions(vehicle.getName());
            keepGoing = handleManagementCommand(vehicle, input.next());
        }
    }

    // EFFECTS: displays management options for the selected vehicle
    private void displayVehicleManagementOptions(String vehicleName) {
        System.out.println("Managing vehicle: " + vehicleName);
        System.out.println("Select an option:");
        System.out.println("1 -> Update vehicle information");
        System.out.println("2 -> Get vehicle cost");
        System.out.println("3 -> Delete this vehicle");
        System.out.println("b -> Go back");
    }

    // EFFECTS: processes user input command for managing the selected vehicle
    private boolean handleManagementCommand(Vehicle vehicle, String command) {
        switch (command) {
            case "1":
                updateVehicleInformation(vehicle);
                return true;
            case "2":
                getVehicleCost(vehicle);
                return true;
            case "3":
                deleteVehicle(vehicle);
                return false;
            case "b":
                return false;
            default:
                System.out.println("Invalid command.");
                return true;
        }

    }

    // MODIFIES: this
    // EFFECTS: registers a new user with a username if the username does not already exist
    public void registerUser() {
        System.out.println("Enter username to register:");
        String username = input.next();
        if (userDatabase.registerUser(username)) {
            System.out.println("Registration successful. You can now log in with your username.");
        } else {
            System.out.println("Registration failed. Username already exists.");
        }
    }

    // MODIFIES: this
    // EFFECTS: logs in a user if the username exists, setting the current user to the logged-in user
    public void loginUser() {
        System.out.println("Enter your username:");
        String username = input.next();
        User user = userDatabase.findUser(username);
        if (user != null) {
            System.out.println("Login successful. Welcome, " + username + "!");
            this.currentUser = user;
            jsonStore = "./data/" + currentUser.getUserName() + ".json";
            jsonWriter = new JsonWriter(jsonStore);
            jsonReader = new JsonReader(jsonStore);
        } else {
            System.out.println("Login failed. User not found.");
        }
    }


    // MODIFIES: this
    // EFFECTS: prompts the user to add a new vehicle to current user vehicle list
    public void addVehicle() {
        System.out.println("Adding a new vehicle");
        System.out.println("Enter vehicle name: ");
        String name = input.next();
        System.out.println("Enter purchase cost: ");
        double cost = input.nextDouble();
        System.out.println("Enter vehicle type: ");
        System.out.println("E -> Electric Car");
        System.out.println("G -> Gasoline Car");
        String type = input.next();
        Vehicle vehicle;
        switch (type.toLowerCase()) {
            case "e":
                vehicle = new ElectricCar(name, cost);
                break;
            case "g":
                vehicle = new GasolineCar(name, cost);
                break;
            default:
                System.out.println("Please enter a valid type.");
                return;
        }
        currentUser.addVehicle(vehicle);
        System.out.println(name + " added successfully");
    }

    // EFFECTS: displays a menu for updating vehicle information and processes the user's selection
    private void updateVehicleInformation(Vehicle vehicle) {
        displayUpdatingVehicleInformationMenu(vehicle);
        boolean validSelection = processVehicleUpdateSelection(vehicle);
        if (!validSelection) {
            System.out.println("Invalid option selected.");
        }
    }

    // EFFECTS: processes the user's selection for updating vehicle information
    private boolean processVehicleUpdateSelection(Vehicle vehicle) {
        int option = input.nextInt();
        switch (option) {
            case 1:
                changeMonthlyExpenses(vehicle);
                return true;
            case 2:
                changeOtherExpenses(vehicle);
                return true;
            case 3:
                changeMonthsOwned(vehicle);
                return true;
            case 4:
                changeCurrentMileage(vehicle);
                return true;
            case 5:
                handleSpecialVehicleUpdate(vehicle);
                return true;
            default:
                return false;
        }
    }

    // EFFECTS: updates specific information for EV or Gas Car
    private void handleSpecialVehicleUpdate(Vehicle vehicle) {
        if (vehicle instanceof ElectricCar) {
            changeChargingCost((ElectricCar) vehicle);
        } else if (vehicle instanceof GasolineCar) {
            changeGasolineCost((GasolineCar) vehicle);
        }
    }

    // EFFECTS: displays options for updating the information
    private void displayUpdatingVehicleInformationMenu(Vehicle vehicle) {
        System.out.println("Updating information for " + vehicle.getName());
        System.out.println("Please choose one to update: ");
        System.out.println("1 -> Monthly Expenses");
        System.out.println("2 -> Other Expenses");
        System.out.println("3 -> Months Owned");
        System.out.println("4 -> Current Mileage");
        if (vehicle instanceof ElectricCar) {
            System.out.println("5 -> Charging Cost");
        } else if (vehicle instanceof GasolineCar) {
            System.out.println("5 -> Gasoline Cost");
        }
    }

    // MODIFIES: vehicle
    // EFFECTS: updates the average monthly expenses of the vehicle
    public void changeMonthlyExpenses(Vehicle vehicle) {
        System.out.println("Please enter new average monthly expenses since purchase: ");
        double monthlyExpenses = input.nextDouble();
        vehicle.setMonthlyExpenses(monthlyExpenses);
        System.out.println("Your vehicle monthly expenses changed to " + monthlyExpenses);
    }

    // MODIFIES: vehicle
    // EFFECTS: updates the other expenses associated with the vehicle
    public void changeOtherExpenses(Vehicle vehicle) {
        System.out.println("Please enter new other expenses since purchase: ");
        double otherExpenses = input.nextDouble();
        vehicle.setOtherExpenses(otherExpenses);
        System.out.println("Your vehicle other expenses changed to " + otherExpenses);
    }

    // MODIFIES: vehicle
    // EFFECTS: updates the number of months the vehicle has been owned
    public void changeMonthsOwned(Vehicle vehicle) {
        System.out.println("Please enter months owned since purchase: ");
        int monthsOwned = input.nextInt();
        vehicle.setMonthsOwned(monthsOwned);
        System.out.println("Your vehicle months owned changed to " + monthsOwned);
    }

    // MODIFIES: vehicle
    // EFFECTS: updates the current mileage of the vehicle in KM
    public void changeCurrentMileage(Vehicle vehicle) {
        System.out.println("Please enter current mileage in KM: ");
        int currentMileage = input.nextInt();
        vehicle.setCurrentMileage(currentMileage);
        System.out.println("Your vehicle current mileage changed to " + currentMileage + " KM");
    }

    // MODIFIES: vehicle
    // EFFECTS: updates the total charging cost for this EV
    public void changeChargingCost(ElectricCar vehicle) {
        System.out.println("Please enter your total charging cost: ");
        double chargingCost = input.nextDouble();
        vehicle.setChargingCost(chargingCost);
        System.out.println("Your vehicle total charging cost changed to " + chargingCost + " KM");
    }

    // MODIFIES: vehicle
    // EFFECTS: updates the total gasoline cost for this gas car
    public void changeGasolineCost(GasolineCar vehicle) {
        System.out.println("Please enter your total Gasoline cost: ");
        double gasolineCost = input.nextDouble();
        vehicle.setGasolineCost(gasolineCost);
        System.out.println("Your vehicle total Gasoline cost changed to " + gasolineCost + " KM");
    }

    // EFFECTS: displays the total cost until today and cost per kilometer for the selected vehicle
    private void getVehicleCost(Vehicle vehicle) {
        System.out.println("Cost information for " + vehicle.getName() + ":");
        System.out.println("Total cost until today: " + vehicle.totalCostUntilToday());
        System.out.println("Cost per kilometer: " + vehicle.costPerKilometer());
    }

    // MODIFIES: this
    // EFFECTS: deletes the selected vehicle from the current user's vehicle list if confirmed
    private void deleteVehicle(Vehicle vehicle) {
        System.out.println("Are you sure you want to delete " + vehicle.getName() + " ?");
        System.out.println("Press Y to confirm.");
        System.out.println("Press N to cancel.");
        String confirmation = input.next();
        switch (confirmation.toLowerCase()) {
            case "y":
                if (currentUser.removeVehicle(vehicle)) {
                    System.out.println("This vehicle has been successfully deleted.");
                } else {
                    System.out.println("Failed to delete " + vehicle.getName() + ".");
                }
                break;
            case "n":
                System.out.println("Deletion cancelled.");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: deletes the current user account if confirmed
    public void deleteUser() {
        System.out.println("Are you sure you want to delete your account,"
                + currentUser.getUserName() + "? \nThis operation cannot be undone.");
        System.out.println("Press Y to confirm.");
        System.out.println("Press N to cancel.");
        String confirmation = input.next();
        switch (confirmation.toLowerCase()) {
            case "y":
                if (userDatabase.removeUser(currentUser)) {
                    deleteUserFile();
                    currentUser = null;
                    saveUserDatabase();
                    System.out.println("Your account has been successfully deleted.");
                } else {
                    System.out.println("Failed to delete your account.");
                }
                break;
            case "n":
                System.out.println("Account delete cancelled.");
                break;
            default:
                System.out.println("Invalid input");
                break;
        }
    }

    // EFFECTS: Delete User file as request
    private void deleteUserFile() {
        File file = new File(jsonStore);
        if (file.delete()) {
            System.out.println("Deleted user data file: " + jsonStore);
        } else {
            System.out.println("Failed to delete user data file: " + jsonStore);
        }

    }
}