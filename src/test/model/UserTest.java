package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    private User testUser;
    private User testUser1;
    private User testUser2;
    private User testUser3;
    private Vehicle testCar1;
    private Vehicle testCar2;
    private Vehicle testCar3;

    @BeforeEach
    void runBefore() {
        testUser = new User("Elise");
        testUser1 = new User("User1");
        testUser2 = new User("User1");
        testUser3 = new User("User3");
        testCar1 = new GasolineCar("car1", 100);
        testCar2 = new GasolineCar("car2", 200);
        testCar3 = new GasolineCar("car3", 300);
    }

    @Test
    void testConstructor() {
        assertEquals("Elise", testUser.getUserName());
        assertTrue(testUser.getAllVehicles().isEmpty());
    }

    @Test
    void testAddOneCarToUser() {
        assertTrue(testUser.getAllVehicles().isEmpty());
        testUser.addVehicle(testCar1);
        assertEquals(1, testUser.getAllVehicles().size());
        assertEquals(testCar1, testUser.getAllVehicles().get(0));
    }

    @Test
    void testAddMultiCarsToUser() {
        assertTrue(testUser.getAllVehicles().isEmpty());
        testUser.addVehicle(testCar1);
        assertEquals(1, testUser.getAllVehicles().size());
        assertEquals(testCar1, testUser.getAllVehicles().get(0));
        testUser.addVehicle(testCar2);
        testUser.addVehicle(testCar3);
        assertEquals(3, testUser.getAllVehicles().size());
        assertEquals(testCar2, testUser.getAllVehicles().get(1));
        assertEquals(testCar3, testUser.getAllVehicles().get(2));
    }

    @Test
    void testAddVehicleAlreadyExist() {
        testUser.addVehicle(testCar1);
        assertEquals(1, testUser.getAllVehicles().size());
        testUser.addVehicle(testCar1);
        assertEquals(1, testUser.getAllVehicles().size());
        testUser.addVehicle(testCar2);
        testUser.addVehicle(testCar3);
        assertEquals(3, testUser.getAllVehicles().size());
        testUser.addVehicle(testCar3);
        assertEquals(3, testUser.getAllVehicles().size());
    }

    @Test
    void testRemoveOneCarFromUserVehicleList() {
        testUser.addVehicle(testCar1);
        assertEquals(1, testUser.getAllVehicles().size());
        testUser.removeVehicle(testCar1);
        assertTrue(testUser.getAllVehicles().isEmpty());
    }

    @Test
    void testRemoveMultiCarsFromUserVehicleList() {
        testUser.addVehicle(testCar1);
        testUser.addVehicle(testCar2);
        testUser.addVehicle(testCar3);
        assertEquals(3, testUser.getAllVehicles().size());
        testUser.removeVehicle(testCar2);
        assertEquals(2, testUser.getAllVehicles().size());
        assertEquals(testCar3, testUser.getAllVehicles().get(1));
        testUser.removeVehicle(testCar1);
        testUser.removeVehicle(testCar3);
        assertTrue(testUser.getAllVehicles().isEmpty());
    }

    @Test
    void testRemoveVehicleNotInTheList() {
        testUser.addVehicle(testCar1);
        assertEquals(1, testUser.getAllVehicles().size());
        testUser.removeVehicle(testCar2);
        assertEquals(1, testUser.getAllVehicles().size());
    }

    @Test
    void testViewTheOnlyVehicleInTheList() {
        testUser.addVehicle(testCar1);
        testCar1.setCurrentMileage(5);
        assertEquals("All Vehicles Information: \ncar1:" +
                "\nTotal Cost Until Today:  100.0\nCurrent Mileage in KM: 5" +
                "\nCost Per Kilometer: 20.0\n", testUser.viewUserVehicles());
    }

    @Test
    void TestViewMultiVehicleInTheList() {
        testUser.addVehicle(testCar1);
        testCar1.setCurrentMileage(5);
        testUser.addVehicle(testCar2);
        testCar2.setCurrentMileage(12);
        testUser.addVehicle(testCar3);
        testCar3.setCurrentMileage(82);
        assertEquals("All Vehicles Information: \n" +
                "car1:\n" +
                "Total Cost Until Today:  100.0\n" +
                "Current Mileage in KM: 5\n" +
                "Cost Per Kilometer: 20.0\n" +
                "car2:\n" +
                "Total Cost Until Today:  200.0\n" +
                "Current Mileage in KM: 12\n" +
                "Cost Per Kilometer: 16.666666666666668\n" +
                "car3:\n" +
                "Total Cost Until Today:  300.0\n" +
                "Current Mileage in KM: 82\n" +
                "Cost Per Kilometer: 3.658536585365854\n", testUser.viewUserVehicles());
    }

    @Test
    void testUserEqualsMethod() {
        assertEquals(testUser1, testUser2);
        assertNotEquals(testUser1, testUser3);
        assertNotEquals(testUser1, null);
        assertNotEquals(testUser1, new Object());
        assertEquals(testUser1, testUser1);
    }


}
