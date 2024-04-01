package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a general type of all vehicle that has a name, purchase cost, monthly expenses, other expenses
// currentMileage, expectedMileage, costPerKilometer
public abstract class Vehicle implements Writable {
    protected String vehicleName; // The name of the vehicle.
    protected double purchaseCost; // The purchase cost of the vehicle.
    protected double monthlyExpenses; // Monthly expenses associated with the vehicle
    // (insurance, maintenance, loan interest, etc.).
    protected double otherExpenses; // Other related expenses (parking fees, vehicle inspections, etc.).
    protected int monthsOwned; // Months since purchase.
    protected int currentMileage; // The current mileage of the vehicle in kilometer.
    //protected int expectedMileage; // The expected mileage for future cost calculations.

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
        //this.expectedMileage = 0;
    }

    //EFFECTS: calculate the total cost for this vehicle
    public double totalCostUntilToday() {
        double totalMonthlyExpenses = monthsOwned * monthlyExpenses;
        return purchaseCost + totalMonthlyExpenses + otherExpenses;
    }

    // EFFECTS: calculate the cost per kilometer
    public abstract double costPerKilometer();

    //EFFECTS: provide total cost, current mileage and cost per kilometer
    public abstract String getVehicleInfo();

    //MODIFIES: this
    //EFFECTS: update vehicle information.
    public void updateVehicleInfo(double monthlyExpenses, double otherExpenses, int monthsOwned, int currentMileage) {
        this.monthlyExpenses = monthlyExpenses;
        this.otherExpenses = otherExpenses;
        this.monthsOwned = monthsOwned;
        this.currentMileage = currentMileage;
    }

    //MODIFIES: this
    //EFFECTS: update vehicle name, and log the event.
    public void setName(String name) {
        String preName = this.vehicleName;
        this.vehicleName = name;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + preName + " changed name to: " + this.vehicleName));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle purchase cost, and log the event.
    public void setPurchaseCost(double cost) {
        this.purchaseCost = cost;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " changed purchase cost to: " + this.purchaseCost));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle Monthly Expenses, and log the event.
    public void setMonthlyExpenses(double monthlyExpenses) {
        this.monthlyExpenses = monthlyExpenses;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " changed monthly expenses to: " + this.monthlyExpenses));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle Other Expenses, and log the event.
    public void setOtherExpenses(double expenses) {
        this.otherExpenses = expenses;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " changed other expenses to: " + this.otherExpenses));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle months owned, and log the event.
    public void setMonthsOwned(int monthsOwned) {
        this.monthsOwned = monthsOwned;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " changed months owned to: " + this.monthsOwned));
    }

    //MODIFIES: this
    //EFFECTS: update vehicle Current Mileage and log the event.
    public void setCurrentMileage(int mileage) {
        this.currentMileage = mileage;
        EventLog.getInstance().logEvent(new Event("Vehicle "
                + this.vehicleName + " changed current mileage to: " + this.currentMileage + " KM"));
    }

    // !!! Put all simple setter and getter methods below
    public String getName() {
        return this.vehicleName;
    }

    public double getPurchaseCost() {
        return this.purchaseCost;
    }

    public double getMonthlyExpenses() {
        return this.monthlyExpenses;
    }

    public double getOtherExpenses() {
        return this.otherExpenses;
    }

    public int getMonthsOwned() {
        return this.monthsOwned;
    }

    public int getCurrentMileage() {
        return this.currentMileage;
    }

    public abstract String getVehicleType();

    // public int getExpectedMileage() {
    //     return this.expectedMileage;
    // }
}
