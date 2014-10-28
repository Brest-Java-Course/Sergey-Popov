package com.epam.brest.courses.service;

import com.epam.brest.courses.domain.User;

/**
 * Created by beast on 28.10.14. At 19.56
 */
public class UserDataFixture {

    public static User getNewUser() {
        User user = new User();
        user.setName("name");
        user.setLogin("login");
        return user;
    }

    public static User getExistUser(long id) {
        User user = new User();
        user.setUserId(id);
        user.setName("name");
        user.setLogin("login");
        return user;
    }
}