package persistence;

import model.User;
import model.UserDatabase;
import model.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonDatabaseReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderIllegalCharactersPath() {
        JsonReader reader = new JsonReader("./data/?()&*@Q$_+)*||\\noSuchFile.json");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderIllegalCharactersJson() {
        JsonReader reader = new JsonReader("./data/testReaderUserWithIllegalCharacterDoc.jsonn");
        try {
            User user = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testReaderEmptyUserDatabase() {
        JsonDatabaseReader reader = new JsonDatabaseReader("./data/testDatabaseReaderEmptyUserDatabase.json");
        try {
            UserDatabase userDatabase = reader.readDatabase();
            assertEquals(0, userDatabase.getUserDatabase().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderGeneralUser() {
        JsonDatabaseReader reader = new JsonDatabaseReader("./data/testDatabaseReaderGeneralUserDatabase.json");
        try {
            UserDatabase userDatabase = reader.readDatabase();
            assertEquals(2, userDatabase.getUserDatabase().size());
            User user1 = userDatabase.getUserDatabase().get(0);
            User user2 = userDatabase.getUserDatabase().get(1);
            List<Vehicle> user1Vehicles = user1.getAllVehicles();
            assertEquals(2, user1Vehicles.size());
            assertEquals(100, user1Vehicles.get(0).getCurrentMileage());
            assertEquals("ElectricCar", user1Vehicles.get(0).getVehicleType());
            assertEquals(300, user1Vehicles.get(1).getOtherExpenses());
            assertEquals("GasolineCar", user1Vehicles.get(1).getVehicleType());

            List<Vehicle> user2Vehicles = user2.getAllVehicles();
            assertEquals(2, user2Vehicles.size());
            assertEquals(100, user2Vehicles.get(0).getCurrentMileage());
            assertEquals("ElectricCar", user2Vehicles.get(0).getVehicleType());
            assertEquals(300, user2Vehicles.get(1).getOtherExpenses());
            assertEquals("GasolineCar", user2Vehicles.get(1).getVehicleType());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser2() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser2.json");
        try {
            User user = reader.read();
            assertEquals("GeneralUser2", user.getUserName());
            List<Vehicle> userVehicles = user.getAllVehicles();
            assertEquals(4, userVehicles.size());
            assertEquals(20000, userVehicles.get(0).getCurrentMileage());
            assertEquals("ElectricCar", userVehicles.get(0).getVehicleType());
            assertEquals(23000, userVehicles.get(1).getOtherExpenses());
            assertEquals("GasolineCar", userVehicles.get(1).getVehicleType());
            assertEquals("ElectricCar", userVehicles.get(2).getVehicleType());
            assertEquals("ElectricCar", userVehicles.get(3).getVehicleType());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}