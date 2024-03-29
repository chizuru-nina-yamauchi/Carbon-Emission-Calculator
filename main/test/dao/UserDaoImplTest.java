package dao;

import model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {
    private UserDao userDao;


    // all the test has passed successfully

    /*
    @Test
    void createUser() {
        User newUser = new User("newUser", "newPassword", "newUser@test.com");
        User createdUser = userDao.createUser(newUser);
        assertNotNull(createdUser);
        assertNotNull(createdUser.getUserId());
        assertEquals(newUser.getUsername(), createdUser.getUsername());
        assertEquals(newUser.getEmail(), createdUser.getEmail());
        assertEquals(newUser.getPassword(), createdUser.getPassword());
    }

     */

    @Test
    void testDeleteUser() {
        userDao = new UserDaoImpl();
        boolean deleted = userDao.deleteUser(12);
        assertTrue(deleted);

        User deletedUser = userDao.readUserById(12);
        assertNull(deletedUser);
    }

    @Test
    void testReadUserById() {
        userDao = new UserDaoImpl();
        User user = userDao.readUserById(1);
        assertNotNull(user);
        assertEquals(1, user.getUserId());
        assertEquals("Giyu Tomioka", user.getUsername());
        assertEquals("giyu@hashira_training.com", user.getEmail());
        assertEquals("\\x6c1b87a4b96cd134822a1aeb8e919cbc5992c76c5d2d4d7c6f6784fa1710ef5f", user.getPassword());
    }

    @Test
    void testReadAllUsers() {
        userDao = new UserDaoImpl();
        Set<User> users = userDao.readAllUsers();
        assertNotNull(users);
    }

    @Test
    void testUpdateUser() {
        userDao = new UserDaoImpl();
        User userToUpdate = userDao.readUserById(9);
        userToUpdate.setUsername("updatedUsername");
        userToUpdate.setEmail("updated@test.com");
        userToUpdate.setPassword("updatedPassword");

        boolean updated = userDao.updateUser(userToUpdate);
        assertTrue(updated);

        User updatedUser = userDao.readUserById(9);
        assertNotNull(updatedUser);
        assertEquals(userToUpdate.getUsername(), updatedUser.getUsername());
        assertEquals(userToUpdate.getEmail(), updatedUser.getEmail());
        assertEquals(userToUpdate.getPassword(), updatedUser.getPassword());
    }





}