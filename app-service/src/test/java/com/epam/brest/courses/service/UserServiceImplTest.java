package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


/**
 * Created by beast on 27.10.14. At 11.29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring-services-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class UserServiceImplTest {

    public static final String ADMIN = "admin";

    public static final String NOOB = "noob";

    public static final String TEST_USER = "testUser";

    @Autowired
    private UserService userService;

    @Before
    public void setUp() throws Exception {

    }

    /*
    * ADD USER TESTS
    */
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws Exception {
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyUser() throws Exception {
        userService.addUser(new User());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNotNullIdUser() throws Exception {
        userService.addUser(new User(12L, "", ""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithSameLogin() throws Exception {
        userService.addUser(new User(null, ADMIN, ADMIN));
        userService.addUser(new User(null, ADMIN, ADMIN));
    }

    @Test
    public void testAddUser() throws Exception {
        userService.addUser(new User(null, ADMIN, ADMIN));
        User user = userService.getUserByLogin(ADMIN);
        Assert.assertEquals(ADMIN, user.getLogin());
    }

    /*
    * GET USER TESTS
    */
    @Test
    public void testGetUsers() throws Exception {
        List<User> users = userService.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }


    /*
    * UPDATE USER TESTS
    */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAdminUser() throws Exception {
        userService.addUser(new User(null, ADMIN, ADMIN));
        User existingUser = userService.getUserByLogin(ADMIN);
        User updateUser = new User(existingUser.getUserId(), NOOB, NOOB);
        userService.updateUser(updateUser);
    }

    @Test
    public void testUpdateUser() throws Exception {
        userService.addUser(new User(null, TEST_USER, TEST_USER));
        User existingUser = userService.getUserByLogin(TEST_USER);
        User beforeUpdateUser = new User(existingUser.getUserId(), NOOB, NOOB);
        userService.updateUser(beforeUpdateUser);
        User afterUpdateUser = userService.getUserById(existingUser.getUserId());
        assertEquals(beforeUpdateUser.getLogin(), afterUpdateUser.getLogin());
        assertEquals(beforeUpdateUser.getName(), afterUpdateUser.getName());
    }

    /*
    * REMOVE USER TESTS
    */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAdminUser() throws Exception {
        userService.addUser(new User(null, ADMIN, ADMIN));
        User user = userService.getUserByLogin(ADMIN);
        userService.removeUser(user.getUserId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUserNullId() throws Exception {
        userService.removeUser(null);
    }

    @Test
    public void testRemoveUser() throws Exception {
        userService.addUser(new User(null, TEST_USER, TEST_USER));
        List<User> users = userService.getUsers();
        int sizeBefore = users.size();

        userService.removeUser((long) (users.size() - 1));

        users = userService.getUsers();
        int sizeAfter = users.size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

}