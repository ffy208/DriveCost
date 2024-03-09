package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonDatabaseWriterTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.


    @Test
    void testDatabaseWriterInvalidFile() {
        try {
            UserDatabase userDatabase = new UserDatabase();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testDatabaseWriterEmptyUsers() {
        try {
            UserDatabase userDatabase = new UserDatabase();
            JsonDatabaseWriter writer = new JsonDatabaseWriter("./data/testDatabaseWriterEmptyUsers.json");
            writer.open();
            writer.write(userDatabase);
            writer.close();

            JsonDatabaseReader reader = new JsonDatabaseReader("./data/testDatabaseWriterEmptyUsers.json");
            userDatabase = reader.readDatabase();
            assertEquals(0, userDatabase.getUserDatabase().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testDatabaseWriterGeneralDatabase() {
        try {
            UserDatabase userDatabase = new UserDatabase();
            JsonDatabaseWriter writer = new JsonDatabaseWriter("./data/testDatabaseWriterGeneralUserDatabase.json");
            User user = new User("TestUser2");
            user.addVehicle(new GasolineCar("TestCar1", 2000));
            user.addVehicle(new ElectricCar("TestCar2", 3000));
            userDatabase.addUser(user);
            writer.open();
            writer.write(userDatabase);
            writer.close();

            JsonDatabaseReader reader = new JsonDatabaseReader("./data/testDatabaseWriterGeneralUserDatabase.json");
            userDatabase = reader.readDatabase();
            assertEquals(1, userDatabase.getUserDatabase().size());
            userDatabase = reader.readDatabase();
            user = userDatabase.getUserDatabase().get(0);
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