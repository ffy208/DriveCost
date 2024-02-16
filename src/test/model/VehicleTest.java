package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VehicleTest {
    private Vehicle testCar1;
    private Vehicle testCar2;
    private Vehicle testCar3;

    @BeforeEach
    public void setUp(){
        testCar1 = new GasolineCar("car1", 100);
        testCar2 = new GasolineCar("car2", 200);
        testCar2.setCurrentMileage(1);
        testCar3 = new GasolineCar("car3", 300);
        testCar3.setMonthsOwned(10);
        testCar3.setMonthlyExpenses(20);
        testCar3.setCurrentMileage(1000);
    }

    @Test
    void testConstructor() {
        assertEquals("car1", testCar1.getName());
        assertEquals(100, testCar1.getPurchaseCost());
        assertEquals(0, testCar1.getMonthlyExpenses());
        assertEquals(0, testCar1.getOtherExpenses());
        assertEquals(0, testCar1.getMonthsOwned());
        assertEquals(0, testCar1.getCurrentMileage());
        assertEquals(0, testCar1.getExpectedMileage());
    }

    @Test
    void testTotalCostUntilTodayForNewVehicle() {
        assertEquals(100, testCar1.totalCostUntilToday());
        //another car with non-zero monthly expenses but is a new car
        testCar2.setMonthlyExpenses(20);
        testCar2.setMonthsOwned(0);
        assertEquals(200, testCar2.totalCostUntilToday());
        testCar2.setOtherExpenses(100);
        assertEquals(300, testCar2.totalCostUntilToday());
    }

    @Test
    void testTotalCostUntilTodayForUsedVehicle() {
        testCar1.setMonthlyExpenses(10);
        testCar1.setMonthsOwned(5);
        assertEquals(150, testCar1.totalCostUntilToday());
        testCar1.setOtherExpenses(200);
        assertEquals(350, testCar1.totalCostUntilToday());
    }

    @Test
    void testCostPerKilometer() {
        assertEquals(0.5,testCar3.costPerKilometer());
    }

    @Test
    void testCostPerKilometerWithZeroMileage() {
        testCar1.setCurrentMileage(0);
        try {
            testCar1.costPerKilometer();
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // want this, pass
        }
    }


    @Test
    void testGetVehicleInfo() {
        assertEquals("car2:\n" +
                "Total Cost Until Today:  200.0\n" +
                "Current Mileage in KM: 1\n" +
                "Cost Per Kilometer: 200.0", testCar2.getVehicleInfo());
        assertEquals("car3:\n" +
                "Total Cost Until Today:  500.0\n" +
                "Current Mileage in KM: 1000\n" +
                "Cost Per Kilometer: 0.5", testCar3.getVehicleInfo());
    }

    @Test
    void testUpdateVehicleInfo() {
        //Before change
        assertEquals("car2:\n" +
                "Total Cost Until Today:  200.0\n" +
                "Current Mileage in KM: 1\n" +
                "Cost Per Kilometer: 200.0", testCar2.getVehicleInfo());
        testCar2.updateVehicleInfo(10.56,204.7,3,4000);
        //After change
        assertEquals("car2:\n" +
                "Total Cost Until Today:  436.38\n" +
                "Current Mileage in KM: 4000\n" +
                "Cost Per Kilometer: 0.109095", testCar2.getVehicleInfo());
    }
}
