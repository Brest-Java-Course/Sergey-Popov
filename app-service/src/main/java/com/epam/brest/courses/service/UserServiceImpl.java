package com.epam.brest.courses.service;

import com.epam.brest.courses.dao.UserDao;
import com.epam.brest.courses.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by beast on 27.10.14. At 11.17
 */
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    @Transactional
    public Long addUser(User user) {
        Assert.notNull(user);
        Assert.isNull(user.getUserId());
        Assert.notNull(user.getLogin(), "User login should be specified.");
        Assert.notNull(user.getName(), "User name should be specified.");
        User existingUser = getUserByLogin(user.getLogin());
        if (existingUser != null) {
            throw new IllegalArgumentException(user + " is present in DB.");
        }
        LOGGER.debug("addUser({})", user);
        return userDao.addUser(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        LOGGER.debug("get users()");
        return userDao.getUsers();
    }

    @Override
    @Transactional
    public User getUserById(Long userId) {
        User user = null;
        try {
            user = userDao.getUserById(userId);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getUserById(id={}) this userId does not exist", userId);
        }
        return user;
    }

    @Override
    @Transactional
    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (EmptyResultDataAccessException e) {
            LOGGER.error("getUserByLogin(login={}), this login does not exist", login);
        }
        return user;
    }

    @Override
    @Transactional
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
    @Transactional
    public void removeUser(Long userId) {
        Assert.notNull(userId, "User id should be specified.");
        Assert.isTrue(!getUserById(userId).getLogin().equals("admin"));
        LOGGER.debug("removeUser(userId={}) ", userId);
        userDao.removeUser(userId);
    }

}