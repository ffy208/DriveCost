package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDatabaseTest {
    private UserDatabase testUserDatabase;
    private User testUser1;
    private User testUser2;


    @BeforeEach
    public void setUp() {
        testUserDatabase = new UserDatabase();
        testUser1 = new User("John");
        testUser2 = new User("Peter");
    }

    @Test
    public void testConstructor() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
    }

    @Test
    public void testFindUserNotExist() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
        testUserDatabase.addUser(testUser1);
        testUserDatabase.addUser(testUser2);
        assertNull(testUserDatabase.findUser("Tom"));
    }

    @Test
    public void testFindUserInTheList() {
        testUserDatabase.addUser(testUser1);
        testUserDatabase.addUser(testUser2);
        assertEquals(testUser1, testUserDatabase.findUser("John"));
    }

    @Test
    public void testRegisterUserNotExist() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
        assertTrue(testUserDatabase.registerUser("Matthew"));
        assertEquals(1, testUserDatabase.getUserDatabase().size());
    }

    @Test
    public void testRegisterUserExistInUsers() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
        testUserDatabase.addUser(testUser1);
        assertEquals(1, testUserDatabase.getUserDatabase().size());
        assertFalse(testUserDatabase.registerUser("John"));
        assertEquals(1, testUserDatabase.getUserDatabase().size());
    }

    @Test
    public void testRemoveUserForOne() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
        testUserDatabase.addUser(testUser1);
        assertEquals(1, testUserDatabase.getUserDatabase().size());
        testUserDatabase.removeUser(testUser1);
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
    }

    @Test
    public void testRemoveUserInTheList() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
        testUserDatabase.addUser(testUser1);
        assertEquals(1, testUserDatabase.getUserDatabase().size());
        assertTrue(testUserDatabase.removeUser(testUser1));
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
    }

    @Test
    public void testRemoveUserNotInTheList() {
        assertTrue(testUserDatabase.getUserDatabase().isEmpty());
        testUserDatabase.addUser(testUser1);
        assertEquals(1, testUserDatabase.getUserDatabase().size());
        assertFalse(testUserDatabase.removeUser(testUser2));
        assertEquals(1, testUserDatabase.getUserDatabase().size());
    }

    @Test
    public void testPrintLog() {
        Event e1 = new Event("A1");
        Event e2 = new Event("A2");
        Event e3 = new Event("A3");
        EventLog el = EventLog.getInstance();
        el.logEvent(e1);
        el.logEvent(e2);
        el.logEvent(e3);
        List<String> l = new ArrayList<String>();
        for (Event next : el) {
            l.add(next.toString());
        }
        assertTrue(l.get(0).contains("New user: John has been created."));
        assertTrue(l.get(1).contains("New user: Peter has been created."));
        assertTrue(l.get(2).contains("A1"));
        assertTrue(l.get(3).contains("A2"));
        assertTrue(l.get(4).contains("A3"));
    }

}
