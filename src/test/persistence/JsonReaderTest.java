package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

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
    void testReaderUserWithoutCar() {
        JsonReader reader = new JsonReader("./data/testReaderUserWithoutCar.json");
        try {
            User user = reader.read();
            assertEquals("testName", user.getUserName());
            assertEquals(0, user.getAllVehicles().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderUserWithoutName() {
        JsonReader reader = new JsonReader("./data/testReaderUserWithoutName.json");
        try {
            User user = reader.read();
            assertEquals("", user.getUserName());
            assertEquals(0, user.getAllVehicles().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralUser() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralUser.json");
        try {
            User user = reader.read();
            assertEquals("GeneralUser", user.getUserName());
            List<Vehicle> userVehicles = user.getAllVehicles();
            assertEquals(2, userVehicles.size());
            assertEquals(20000, userVehicles.get(0).getCurrentMileage());
            assertEquals(23000, userVehicles.get(1).getOtherExpenses());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}