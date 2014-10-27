package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;

import java.util.List;

/**
 * Created by beast on 21.10.14. At 14.17
 */
public interface UserDao {

    public void addUser(User user);

    public User getUserById(Long userId);

    public User getUserByLogin(String login);

    public List<User> getUsers();

    public void removeUser(Long userId);

    public void updateUser(User user);
}
