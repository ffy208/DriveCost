package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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

}
