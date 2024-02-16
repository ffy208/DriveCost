package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
    private Users testUsers;
    private User testUser1;
    private User testUser2;


    @BeforeEach
    public void setUp() {
        testUsers = new Users();
        testUser1 = new User("John");
        testUser2 = new User("Peter");
    }

    @Test
    public void testConstructor() {
        assertTrue(testUsers.getUsers().isEmpty());
    }

    @Test
    public void testFindUserNotExist() {
        assertTrue(testUsers.getUsers().isEmpty());
        assertNull(testUsers.findUser("Tom"));
    }

    @Test
    public void testFindUserInTheList() {
        testUsers.addUser(testUser1);
        testUsers.addUser(testUser2);
        assertEquals(testUser1, testUsers.findUser("John"));
    }

    @Test
    public void testRegisterUserNotExist() {
        assertTrue(testUsers.getUsers().isEmpty());
        assertTrue(testUsers.registerUser("Matthew"));
        assertEquals(1,testUsers.getUsers().size());
    }

    @Test
    public void testRegisterUserExistInUsers() {
        assertTrue(testUsers.getUsers().isEmpty());
        testUsers.addUser(testUser1);
        assertEquals(1,testUsers.getUsers().size());
        assertFalse(testUsers.registerUser("John"));
        assertEquals(1,testUsers.getUsers().size());
    }

    @Test
    public void testRemoveUserForOne() {
        assertTrue(testUsers.getUsers().isEmpty());
        testUsers.addUser(testUser1);
        assertEquals(1,testUsers.getUsers().size());
        testUsers.removeUser(testUser1);
        assertTrue(testUsers.getUsers().isEmpty());
    }

    @Test
    public void testRemoveUserInTheList() {
        assertTrue(testUsers.getUsers().isEmpty());
        testUsers.addUser(testUser1);
        assertEquals(1,testUsers.getUsers().size());
        assertTrue(testUsers.removeUser(testUser1));
        assertTrue(testUsers.getUsers().isEmpty());
    }

    @Test
    public void testRemoveUserNotInTheList() {
        assertTrue(testUsers.getUsers().isEmpty());
        testUsers.addUser(testUser1);
        assertEquals(1,testUsers.getUsers().size());
        assertFalse(testUsers.removeUser(testUser2));
        assertEquals(1,testUsers.getUsers().size());
    }

}
