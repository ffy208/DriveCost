package model;

// Represents a general type of all vehicle that has a name, purchase cost, monthly expenses, other expenses
// currentMileage, expectedMileage, costPerKilometer
public abstract class Vehicle {
    protected String vehicleName; // The name of the vehicle.
    protected double purchaseCost; // The purchase cost of the vehicle.
    protected double monthlyExpenses; // Monthly expenses associated with the vehicle
    // (insurance, maintenance, loan interest, etc.).
    protected double otherExpenses; // Other related expenses (parking fees, vehicle inspections, etc.).
    protected int monthsOwned; // Months since purchase.
    protected int currentMileage; // The current mileage of the vehicle in kilometer.
    protected int expectedMileage; // The expected mileage for future cost calculations.

    //REQUIRES: name has a non-zero length, purchaseCost =>  0;
    //EFFECTS: this.vehicleName = name, this.purchaseCost = purchaseCost
    //         initial all other filed with 0;
    public Vehicle(String name, double purchaseCost) {
        this.vehicleName = name;
        this.purchaseCost = purchaseCost;
        this.monthlyExpenses = 0;
        this.otherExpenses = 0;
        this.monthsOwned = 0;
        this.currentMileage = 0;
        this.expectedMileage = 0;
    }

    //EFFECTS: calculate the total cost for this vehicle
    public double totalCostUntilToday() {
        double totalMonthlyExpenses = monthsOwned * monthlyExpenses;
        return purchaseCost + totalMonthlyExpenses + otherExpenses;
    }

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
    public void updateVehicleInfo(double monthlyExpenses, double otherExpenses, int monthsOwned, int currentMileage) {
        this.monthlyExpenses = monthlyExpenses;
        this.otherExpenses = otherExpenses;
        this.monthsOwned = monthsOwned;
        this.currentMileage = currentMileage;
    }

    // !!! Put all simple setter and getter methods below
    public String getName() {
        return this.vehicleName;
    }

    public double getPurchaseCost() {
        return this.purchaseCost;
    }

    public void setMonthlyExpenses(double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
    }

    public double getMonthlyExpenses() {
        return this.monthlyExpenses;
    }

    public void setOtherExpenses(double expenses) {
        this.otherExpenses = expenses;
    }

    public double getOtherExpenses() {
        return this.otherExpenses;
    }

    public void setMonthsOwned(int monthsOwned) {
        this.monthsOwned = monthsOwned;
    }

    public int getMonthsOwned() {
        return this.monthsOwned;
    }

    public void setCurrentMileage(int mileage) {
        this.currentMileage = mileage;
    }

    public int getCurrentMileage() {
        return this.currentMileage;
    }

    public int getExpectedMileage() {
        return this.expectedMileage;
    }
}
