package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by beast on 27.10.14. At 11.17
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser(User user) {
        Assert.notNull(user);
        Assert.isNull(user.getUserId());
        Assert.notNull(user.getLogin(), "User login should be specified.");
        Assert.notNull(user.getName(), "User name should be specified.");
        User existingUser = getUserByLogin(user.getLogin());
        if (existingUser != null) {
            throw new IllegalArgumentException("User is present in DB.");
        }
        LOGGER.debug("addUser({})", user);
        userDao.addUser(user);
    }

    @Override
    public List<User> getUsers() {
        LOGGER.debug("get users()");
        return userDao.getUsers();
    }

    @Override
    public User getUserById(Long userId) {
        User user = null;
        try {
            user = userDao.getUserById(userId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.debug("getUserByLogin(login={}) ", userId);
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getUserByLogin(login={}) ", login);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Assert.notNull(user);
        Assert.notNull(user.getUserId(), "User id should be specified.");
        User existingUser = getUserById(user.getUserId());
        if (existingUser.getLogin().equals("admin")) {
            throw new IllegalArgumentException("UserLogin is admin.");
        }
        LOGGER.debug("updateUser(user={}) ", user);
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(Long userId) {
        Assert.notNull(userId, "User id should be specified.");
        Assert.isTrue(!getUserById(userId).getLogin().equals("admin"));
        LOGGER.debug("removeUser(userId={}) ", userId);
        userDao.removeUser(userId);
    }

}