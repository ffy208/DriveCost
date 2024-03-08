package model;

import org.json.JSONObject;

// Represents a ElectricCar of same as general vehicle type, but it has own charging cost filed.
public class ElectricCar extends Vehicle {
    private double chargingCost;

    //REQUIRES: name has a non-zero length, purchaseCost =>  0;
    //EFFECTS: Call super to assign name and purchaseCost. Initialize charging cost as 0.
    public ElectricCar(String name, double purchaseCost) {
        super(name, purchaseCost);
        this.chargingCost = 0;
    }

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
        json.put("chargingCost", chargingCost);
        json.put("vehicleType", "ElectricCar");
        return json;
    }

    @Override
    //EFFECTS: calculate the total cost for this ElectricCar
    public double totalCostUntilToday() {
        return super.totalCostUntilToday() + this.chargingCost;
    }

    @Override
    // EFFECTS: calculate the cost per kilometer
    public double costPerKilometer() {
        double costPerKilometer;
        if (this.currentMileage == 0) {
            costPerKilometer = totalCostUntilToday();
        } else {
            costPerKilometer = totalCostUntilToday() / this.currentMileage;
        }
        return costPerKilometer;
    }

    @Override
    //EFFECTS: provide total cost, current mileage and cost per kilometer
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
                                  double chargingCost, int currentMileage) {
        super.updateVehicleInfo(monthlyExpenses, otherExpenses, monthsOwned, currentMileage);
        this.chargingCost = chargingCost;
    }

    // !!! Put all simple setter and getter methods below
    public void setChargingCost(double chargingCost) {
        this.chargingCost = chargingCost;
    }

    public double getChargingCost() {
        return this.chargingCost;
    }
}
