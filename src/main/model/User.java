package model;

import java.util.ArrayList;
import java.util.List;


// Represents a DriveCostPro user having a name and their List Cars.
public class User {
    private final String userName;               // User's name
    private List<Vehicle> userVehicles;    // User's vehicles in a list

    //REQUIRES: name has a non-zero length
    //EFFECTS: username = name, and create a new userVehicles list
    public User(String name) {
        this.userName = name;
        userVehicles = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: add the given vehicle to userVehicles
    public void addVehicle(Vehicle vehicle) {
        if (!(userVehicles.contains(vehicle))) {
            this.userVehicles.add(vehicle);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the given vehicle from userVehicles
    public void removeVehicle(Vehicle vehicle) {
        if (userVehicles.contains(vehicle)) {
            this.userVehicles.remove(vehicle);
        }
    }

    //REQUIRES: userVehicles is not empty
    //EFFECTS: return all Vehicles information in String
    public String viewUserVehicles() {
        StringBuilder allVehiclesInfo = new StringBuilder("All Vehicles Information: \n");
        for (Vehicle vehicle : userVehicles) {
            allVehiclesInfo.append(vehicle.getVehicleInfo())
                    .append("\n");
        }
        return allVehiclesInfo.toString();
    }

    // !!! Put all simple setter and getter methods below
    public String getUserName() {
        return this.userName;
    }

    public List<Vehicle> getAllVehicles() {
        return this.userVehicles;
    }
}
