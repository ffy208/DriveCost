package model;

import org.json.JSONObject;

// Represents a ElectricCar of same as general vehicle type, but it has own charging cost filed.
public class ElectricCar extends Vehicle {
    private double chargingCost;
    private final String vehicleType = "ElectricCar";

    //REQUIRES: name has a non-zero length, purchaseCost =>  0;
    //EFFECTS: Call super to assign name and purchaseCost. Initialize charging cost as 0, and log the event.
    public ElectricCar(String name, double purchaseCost) {
        super(name, purchaseCost);
        this.chargingCost = 0;
        EventLog.getInstance().logEvent(new Event("New Electric Vehicle "
                + name + " has been created with purchase cost: $" + Double.toString(purchaseCost)));
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
        json.put("chargingCost", chargingCost);
        json.put("vehicleType", vehicleType);
        return json;
    }

    //EFFECTS: calculate the total cost for this ElectricCar
    @Override
    public double totalCostUntilToday() {
        return super.totalCostUntilToday() + this.chargingCost;
    }

    // EFFECTS: calculate the cost per kilometer, and log the event.
    @Override
    public double costPerKilometer() {
        double costPerKilometer;
        if (this.currentMileage == 0) {
            costPerKilometer = totalCostUntilToday();
        } else {
            costPerKilometer = totalCostUntilToday() / this.currentMileage;
        }
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " cost per KM: $" + Double.toString(costPerKilometer)));
        return costPerKilometer;
    }

    //EFFECTS: provide total cost, current mileage and cost per kilometer
    @Override
    public String getVehicleInfo() {
        String vehicleInfo;
        vehicleInfo = vehicleName + ":\nTotal Cost Until Today:  $"
                + totalCostUntilToday() + "\nCurrent Mileage: "
                + currentMileage + " KM" + "\nCost Per Kilometer: $" + costPerKilometer();
        return vehicleInfo;
    }

    //MODIFIES: this
    //EFFECTS: update vehicle information. and log the event.
    public void updateVehicleInfo(double monthlyExpenses, double otherExpenses, int monthsOwned,
                                  double chargingCost, int currentMileage) {
        super.updateVehicleInfo(monthlyExpenses, otherExpenses, monthsOwned, currentMileage);
        this.chargingCost = chargingCost;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + ":\nMonthly expenses change to: $"
                + Double.toString(monthlyExpenses) + " per month.\n" + "Other expenses change to: $"
                + Double.toString(otherExpenses) + " in total.\n" + "months owned change to: "
                + Integer.toString(monthsOwned) + " months.\n" + "Charging cost change to: $"
                + Double.toString(chargingCost) + " in total.\n" + "Current mileage change to: "
                + Integer.toString(currentMileage) + " KM."));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle charging cost information. and log the event.
    public void setChargingCost(double chargingCost) {
        this.chargingCost = chargingCost;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " charging cost change to: $"
                + Double.toString(chargingCost) + " in total."));
    }

    // !!! Put all simple setter and getter methods below
    public double getChargingCost() {
        return this.chargingCost;
    }

    @Override
    public String getVehicleType() {
        return this.vehicleType;
    }
}
