package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleTest {
    private Vehicle testCar1;
    private Vehicle testCar2;
    private Vehicle testCar3;

    @BeforeEach
    public void setUp() {
        testCar1 = new GasolineCar("car1", 100);
        testCar2 = new GasolineCar("car2", 200);
        testCar2.setCurrentMileage(1);
        testCar3 = new GasolineCar("car3", 300);
        testCar3.setMonthsOwned(10);
        testCar3.setMonthlyExpenses(20);
        testCar3.setCurrentMileage(1000);
    }

    @Test
    void testSetName() {
        testCar1.setName("car5");
        assertEquals("car5", testCar1.getName());
    }

    @Test
    void testSetPurchaseCost() {
        testCar1.setPurchaseCost(234.56);
        assertEquals(234.56, testCar1.getPurchaseCost());
    }

    @Test
    void testToJson() {

    }

    @Test
    void testConstructor() {
        assertEquals("car1", testCar1.getName());
        assertEquals(100, testCar1.getPurchaseCost());
        assertEquals(0, testCar1.getMonthlyExpenses());
        assertEquals(0, testCar1.getOtherExpenses());
        assertEquals(0, testCar1.getMonthsOwned());
        assertEquals(0, testCar1.getCurrentMileage());
        //assertEquals(0, testCar1.getExpectedMileage());
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
}
