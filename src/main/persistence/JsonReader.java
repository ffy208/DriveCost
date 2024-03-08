package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Stream;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

// Represents a reader that reads User from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
        String jsonData = this.readFile(this.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return this.parseUser(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses User from JSON object and returns it
    private User parseUser(JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        // String password = jsonObject.getString("password");
        User user = new User(userName);
        this.addVehicles(user, jsonObject);
        return user;
    }

    // MODIFIES: user
    // EFFECTS: parses vehicles from JSON object and adds them to user
    private void addVehicles(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("userVehicles");
        Iterator<Object> var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextVehicle = (JSONObject) json;
            this.addVehicle(user, nextVehicle);
        }

    }

    // EFFECTS: determine vehicle type from JSON object and call corresponding methods to add
    private void addVehicle(User user, JSONObject jsonObject) {
        String vehicleType = jsonObject.getString("vehicleType");
        if (vehicleType.equals("GasolineCar")) {
            addGasolineCar(user, jsonObject);
        } else {
            addElectricCar(user, jsonObject);
        }
    }

    // MODIFIES: user
    // EFFECTS: parses GasolineCar from JSON object and adds it to user's Vehicles
    private void addGasolineCar(User user, JSONObject jsonObject) {
        String vehicleName = jsonObject.getString("vehicleName");
        double purchaseCost = jsonObject.getDouble("purchaseCost");
        double monthlyExpenses = jsonObject.getDouble("monthlyExpenses");
        double otherExpenses = jsonObject.getDouble("otherExpenses");
        int monthsOwned = jsonObject.getInt("monthsOwned");
        int currentMileage = jsonObject.getInt("currentMileage");
        //int expectedMileage = jsonObject.getInt("expectedMileage");
        double gasolineCost = jsonObject.getDouble("gasolineCost");
        GasolineCar vehicle = new GasolineCar(vehicleName, purchaseCost);
        vehicle.setMonthlyExpenses(monthlyExpenses);
        vehicle.setOtherExpenses(otherExpenses);
        vehicle.setMonthsOwned(monthsOwned);
        vehicle.setCurrentMileage(currentMileage);
        vehicle.setGasolineCost(gasolineCost);
        user.addVehicle(vehicle);
    }

    // MODIFIES: user
    // EFFECTS: parses ElectricCar from JSON object and adds it to user's Vehicles
    private void addElectricCar(User user, JSONObject jsonObject) {
        String vehicleName = jsonObject.getString("vehicleName");
        double purchaseCost = jsonObject.getDouble("purchaseCost");
        double monthlyExpenses = jsonObject.getDouble("monthlyExpenses");
        double otherExpenses = jsonObject.getDouble("otherExpenses");
        int monthsOwned = jsonObject.getInt("monthsOwned");
        int currentMileage = jsonObject.getInt("currentMileage");
        //int expectedMileage = jsonObject.getInt("expectedMileage");
        double chargingCost = jsonObject.getDouble("chargingCost");
        ElectricCar vehicle = new ElectricCar(vehicleName, purchaseCost);
        vehicle.setMonthlyExpenses(monthlyExpenses);
        vehicle.setOtherExpenses(otherExpenses);
        vehicle.setMonthsOwned(monthsOwned);
        vehicle.setCurrentMileage(currentMileage);
        vehicle.setChargingCost(chargingCost);
        user.addVehicle(vehicle);
    }
}
