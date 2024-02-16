package model;

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
    //EFFECTS: calculate the total cost for this ElectricCar
    public double totalCostUntilToday() {
        return super.totalCostUntilToday() + this.chargingCost;
    }

    @Override
    // EFFECTS: calculate the cost per kilometer
    public double costPerKilometer() throws IllegalArgumentException {
        if (this.currentMileage == 0) {
            throw new IllegalArgumentException("Current mileage cannot be zero.");
        }
        return totalCostUntilToday() / this.currentMileage;
    }

    @Override
    //EFFECTS: provide total cost, current mileage and cost per kilometer
    public String getVehicleInfo() throws IllegalArgumentException {
        return vehicleName + ":\nTotal Cost Until Today:  "
                + totalCostUntilToday() + "\nCurrent Mileage in KM: "
                + currentMileage + "\nCost Per Kilometer: " + costPerKilometer();
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
