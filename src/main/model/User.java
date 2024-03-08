package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a DriveCostPro user having a name and their List Cars.
public class User implements Writable {
    private final String userName;               // User's name
    // private String password;                     // User's password
    private final List<Vehicle> userVehicles;    // User's vehicles in a list

    //REQUIRES: name has a non-zero length
    //EFFECTS: username = name, and create a new userVehicles list
    public User(String name) {
        this.userName = name;
        // this.password = password;
        userVehicles = new ArrayList<>();
    }

    // EFFECTS: returns this user with all filed in JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("userName", userName);
        //json.put("password", password);
        json.put("userVehicles", vehiclesToJson());
        return json;
    }

    // EFFECTS: returns vehicles belong to this user as a JSON array
    private JSONArray vehiclesToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Vehicle v : userVehicles) {
            jsonArray.put(v.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns ture if the passwords is correct, false otherwise
    // public boolean passwordCorrect(String password) {
    //      return this.password.equals(password);
    // }


    //MODIFIES: this
    //EFFECTS: add the given vehicle to userVehicles
    //         return true for success, false otherwise
    public void addVehicle(Vehicle vehicle) {
        if (!(userVehicles.contains(vehicle))) {
            this.userVehicles.add(vehicle);
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the given vehicle from userVehicles
    //         return true for success, false otherwise
    public boolean removeVehicle(Vehicle vehicle) {
        if (userVehicles.contains(vehicle)) {
            this.userVehicles.remove(vehicle);
            return true;
        }
        return false;
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

//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getPassword() {
//        return password;
//    }


}
