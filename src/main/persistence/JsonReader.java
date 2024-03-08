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

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

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

    private User parseUser(JSONObject jsonObject) {
        String userName = jsonObject.getString("userName");
        // String password = jsonObject.getString("password");
        User user = new User(userName);
        this.addVehicles(user, jsonObject);
        return user;
    }

    private void addVehicles(User user, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("userVehicles");
        Iterator<Object> var4 = jsonArray.iterator();

        while (var4.hasNext()) {
            Object json = var4.next();
            JSONObject nextVehicle = (JSONObject) json;
            this.addVehicle(user, nextVehicle);
        }

    }

    private void addVehicle(User user, JSONObject jsonObject) {
        String vehicleType = jsonObject.getString("vehicleType");

        switch (vehicleType) {
            case "GasolineCar":
                addGasolineCar(user, jsonObject);
                break;
            case "ElectricCar":
                addElectricCar(user, jsonObject);
                break;
        }
    }

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
