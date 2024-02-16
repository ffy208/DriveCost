package model;

// Represents a Gasoline Gas of same as general vehicle type, but it has own gasoline cost filed.
public class GasolineCar extends Vehicle {
    private double gasolineCost;

    //REQUIRES: name has a non-zero length, purchaseCost =>  0;
    //EFFECTS: Call super to assign name and purchaseCost. Initialize gasoline cost as 0.
    public GasolineCar(String carName, double purchaseCost) {
        super(carName, purchaseCost);
        this.gasolineCost = 0;
    }

    @Override
    //EFFECTS: calculate the total cost for this GasolineCar
    public double totalCostUntilToday() {
        return super.totalCostUntilToday() + this.gasolineCost;
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
    public String getVehicleInfo() {
        return vehicleName + ":\nTotal Cost Until Today:  "
                + totalCostUntilToday() + "\nCurrent Mileage in KM: "
                + currentMileage + "\nCost Per Kilometer: " + costPerKilometer();
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
}

