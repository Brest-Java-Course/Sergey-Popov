package com.epam.brest.courses.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void testUserConstr() throws Exception {
        User user1 = new User(99L, "login", "name");
        assertEquals(Long.valueOf(99L), user1.getUserId());
        assertEquals("login", user1.getLogin());
        assertEquals("name", user1.getName());
    }

    @Test
    public void testGetUserId() throws Exception {
        user.setUserId(1L);
        assertEquals(Long.valueOf(1L), user.getUserId());
    }

    @Test
    public void testGetName() throws Exception {
        user.setName("User Name");
        assertEquals("User Name", user.getName());
    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin("User Login");
        assertEquals("User Login", user.getLogin());
    }

}