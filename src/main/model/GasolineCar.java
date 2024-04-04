package model;

import org.json.JSONObject;

// Represents a Gasoline Gas of same as general vehicle type, but it has own gasoline cost filed.
public class GasolineCar extends Vehicle {
    private double gasolineCost;
    private String vehicleType = "GasolineCar";

    //REQUIRES: name has a non-zero length, purchaseCost =>  0;
    //EFFECTS: Call super to assign name and purchaseCost. Initialize gasoline cost as 0, and log the event.
    public GasolineCar(String carName, double purchaseCost) {
        super(carName, purchaseCost);
        this.gasolineCost = 0;
        EventLog.getInstance().logEvent(new Event("New Gasoline Vehicle "
                + carName + " has been created with purchase cost: $" + Double.toString(purchaseCost)));
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
        vehicleInfo = vehicleName + ":\nTotal Cost Until Today: $"
                + totalCostUntilToday() + "\nCurrent Mileage: "
                + currentMileage + " KM" + "\nCost Per Kilometer: $" + costPerKilometer();
        return vehicleInfo;
    }

    //MODIFIES: this
    //EFFECTS: update vehicle information, and log the event.
    public void updateVehicleInfo(double monthlyExpenses, double otherExpenses, int monthsOwned,
                                  double gasolineCost, int currentMileage) {
        super.updateVehicleInfo(monthlyExpenses, otherExpenses, monthsOwned, currentMileage);
        this.gasolineCost = gasolineCost;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + ":\nMonthly expenses change to: $"
                + Double.toString(monthlyExpenses) + " per month.\n" + "Other expenses change to: $"
                + Double.toString(otherExpenses) + " in total.\n" + "Months owned change to: "
                + Integer.toString(monthsOwned) + " months.\n" + "Gasoline cost change to: $"
                + Double.toString(gasolineCost) + " in total.\n" + "Current mileage change to: $"
                + Integer.toString(currentMileage) + " in KM."));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle gasoline  cost information, and log the event.
    public void setGasolineCost(double gasolineCost) {
        this.gasolineCost = gasolineCost;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " gasoline cost change to: $"
                + Double.toString(gasolineCost) + " in total."));
    }

    // !!! Put all simple setter and getter methods below
    public double getGasolineCost() {
        return this.gasolineCost;
    }

    @Override
    public String getVehicleType() {
        return this.vehicleType;
    }
}

