package com.epam.brest.courses.dao;

import com.epam.brest.courses.domain.User;

import java.util.List;

/**
 * A simple DAO interface to handle the database operation required to manipulate a User entity.
 * @author Sergey Popov
 * @version 1.0, October 2014
 */
public interface UserDao {

    /**
     * Inserts the specified user to the database.
     * @param user user to be inserted to the database
     */
    public void addUser(User user);

    /**
     * Returns a list containing all of the users in the database.
     * @return a list containing all of the users in the database
     * @see List
     */
    public List<User> getUsers();

    /**
     * Returns the user with the specified userId from database.
     * @param userId id of the user to return
     * @return the user with the specified userId from the database
     */
    public User getUserById(Long userId);

    /**
     * Returns the user with the specified login from database.
     * @param login login of the user to return
     * @return the user with the specified login from the database
     */
    public User getUserByLogin(String login);

    /**
     * Replaces the user in the database with the specified user.
     * @param user user to be stored in the database
     */
    public void updateUser(User user);

    /**
     * Removes the user with the specified userId from the database.
     * @param userId id of the user to be removed
     */
    public void removeUser(Long userId);

}