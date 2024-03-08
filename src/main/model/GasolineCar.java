package model;

import org.json.JSONObject;

// Represents a Gasoline Gas of same as general vehicle type, but it has own gasoline cost filed.
public class GasolineCar extends Vehicle {
    private double gasolineCost;
    private String vehicleType = "GasolineCar";

    //REQUIRES: name has a non-zero length, purchaseCost =>  0;
    //EFFECTS: Call super to assign name and purchaseCost. Initialize gasoline cost as 0.
    public GasolineCar(String carName, double purchaseCost) {
        super(carName, purchaseCost);
        this.gasolineCost = 0;
    }

    // EFFECTS: returns this Car in JSON
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("vehicleName", vehicleName);
        json.put("purchaseCost", purchaseCost);
        json.put("monthlyExpenses", monthlyExpenses);
        json.put("otherExpenses", otherExpenses);
        json.put("monthsOwned", monthsOwned);
        json.put("currentMileage", currentMileage);
        //json.put("expectedMileage", expectedMileage);
        json.put("gasolineCost", gasolineCost);
        json.put("vehicleType", vehicleType);
        return json;
    }

    //EFFECTS: calculate the total cost for this GasolineCar
    @Override
    public double totalCostUntilToday() {
        return super.totalCostUntilToday() + this.gasolineCost;
    }

    // EFFECTS: calculate the cost per kilometer
    @Override
    public double costPerKilometer() {
        double costPerKilometer;
        if (this.currentMileage == 0) {
            costPerKilometer = totalCostUntilToday();
        } else {
            costPerKilometer = totalCostUntilToday() / this.currentMileage;
        }
        return costPerKilometer;
    }

    //EFFECTS: provide total cost, current mileage and cost per kilometer
    @Override
    public String getVehicleInfo() {
        String vehicleInfo;
        vehicleInfo = vehicleName + ":\nTotal Cost Until Today:  "
                + totalCostUntilToday() + "\nCurrent Mileage in KM: "
                + currentMileage + "\nCost Per Kilometer: " + costPerKilometer();
        return vehicleInfo;
    }

    //MODIFIES: this
    //EFFECTS: update vehicle information.
    public void updateVehicleInfo(double monthlyExpenses, double otherExpenses, int monthsOwned,
                                  double gasolineCost, int currentMileage) {
        super.updateVehicleInfo(monthlyExpenses, otherExpenses, monthsOwned, currentMileage);
        this.gasolineCost = gasolineCost;
    }

    // !!! Put all simple setter and getter methods below
    public void setGasolineCost(double gasolineCost) {
        this.gasolineCost = gasolineCost;
    }

    public double getGasolineCost() {
        return this.gasolineCost;
    }

    @Override
    public String getVehicleType() {
        return this.vehicleType;
    }
}

