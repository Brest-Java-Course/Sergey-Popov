package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

/**
 * Created by beast on 27.10.14. At 11.15
 */
public interface UserService {
    public void addUser(User user);

    public User getUserByLogin(String login);
}
