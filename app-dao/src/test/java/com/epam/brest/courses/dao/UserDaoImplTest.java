package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by beast on 23.10.14. At 14.20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring-dao-test.xml" })
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    /*
    * ADD USER TESTS
    */
    @Test
    public void addUser() {
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();

        User user = new User();
        user.setUserId(3L);
        user.setLogin("userLogin3");
        user.setName("userName3");

        userDao.addUser(user);

        users = userDao.getUsers();
        assertEquals(sizeBefore, users.size() - 1);
    }

    /*
    * GET USER TESTS
    */
    @Test
    public void getUsers() {
        List<User> users = userDao.getUsers();
        assertNotNull(users);
        assertFalse(users.isEmpty());
    }

    @Test
    public void getUserById() {
        User user = new User();
        user.setUserId(4L);
        user.setLogin("userLogin4");
        user.setName("userName4");

        userDao.addUser(user);

        User user2 = userDao.getUserById(4L);
        assertEquals(user.getUserId(), user2.getUserId());
        assertEquals(user.getLogin(), user2.getLogin());
        assertEquals(user.getName(), user2.getName());
    }

    @Test
    public void getUserByLogin() {
        User user = new User();
        user.setUserId(5L);
        user.setLogin("userLogin5");
        user.setName("userName5");

        userDao.addUser(user);

        User user2 = userDao.getUserByLogin("userLogin5");
        assertEquals(user.getUserId(), user2.getUserId());
        assertEquals(user.getLogin(), user2.getLogin());
        assertEquals(user.getName(), user2.getName());
    }

    /*
    * UPDATE USER TESTS
    */
    @Test
    public void updateUser() {
        User user = new User(6L, "userLogin6", "userName6");

        userDao.addUser(user);

        User user2 = new User(6L, "noob6", "noob6");

        userDao.updateUser(user2);
        User currentUser = userDao.getUserById(user2.getUserId());
        assertEquals(user2.getUserId(), currentUser.getUserId());
        assertEquals(user2.getLogin(), currentUser.getLogin());
        assertEquals(user2.getName(), currentUser.getName());
    }

    /*
    * REMOVE USER TESTS
    */
    @Test
    public void removeUser() {
        List<User> users = userDao.getUsers();
        int sizeBefore = users.size();

        userDao.removeUser(users.get(users.size()-1).getUserId());

        users = userDao.getUsers();
        int sizeAfter = users.size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }
}