package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

import java.util.List;

/**
 * Created by beast on 27.10.14. At 11.15
 */
public interface UserService {

    public void addUser(User user);

    public void removeUser(Long userId);

    public void updateUser(User user);

    public User getUserById(Long userId);

    public User getUserByLogin(String login);

    public List<User> getUsers();
}
