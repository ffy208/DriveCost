package persistence;

import model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.


    @Test
    void testWriterInvalidFile() {
        try {
            User user = new User("TestUser1");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterUserWithEmptyVehicle() {
        try {
            User user = new User("TestUser1");
            JsonWriter writer = new JsonWriter("./data/testWriterUserWithEmptyVehicle.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterUserWithEmptyVehicle.json");
            user = reader.read();
            assertEquals("TestUser1", user.getUserName());
            assertEquals(0, user.getAllVehicles().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralUser() {
        try {
            User user = new User("TestUser2");
            user.addVehicle(new GasolineCar("TestCar1", 2000));
            user.addVehicle(new ElectricCar("TestCar2", 3000));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralUser.json");
            writer.open();
            writer.write(user);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralUser.json");
            user = reader.read();
            assertEquals("TestUser2", user.getUserName());
            List<Vehicle> cars = user.getAllVehicles();
            assertEquals(2, cars.size());
            assertEquals("TestCar1", cars.get(0).getName());
            assertEquals(2000, cars.get(0).getPurchaseCost());
            assertEquals("TestCar2", cars.get(1).getName());
            assertEquals(3000, cars.get(1).getPurchaseCost());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}