package com.epam.brest.courses.web;

import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 12.11.14. At 10.17
 */
@Controller
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String init() {

        return "redirect:/usersList";

    }

    @RequestMapping("/inputForm")
    public ModelAndView launchInputForm() {

        return new ModelAndView("inputForm", "user", new User());

    }

    @RequestMapping("/submitData")
    public String getInputForm(@RequestParam("login")String login, @RequestParam("name")String userName) {

        LOGGER.debug("login : " + login + ", name: " + userName);
        User user = new User();
        user.setLogin(login);
        user.setName(userName);
        userService.addUser(user);
        return "redirect:/usersList";

    }

    @RequestMapping("/usersList")
    public ModelAndView getListUsersView() {

        List<User> users = userService.getUsers();
        LOGGER.debug("users.size = " + users.size());

        return new ModelAndView("usersList", "users", users);

    }

    @RequestMapping("/getUserById")
    public ModelAndView getUserById(@RequestParam("id")String id) {

        LOGGER.debug("userId: " + id);
        Long userId;
        try {
            userId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            userId = 0L;
        }
        User user = userService.getUserById(userId);
        List<User> users = new ArrayList<>(1);
        users.add(user);

        return new ModelAndView("usersList", "users", users);

    }

    @RequestMapping("/getUserByLogin")
    public ModelAndView getUserByLogin(@RequestParam("login")String login) {

        LOGGER.debug("login: " + login);

        User user = userService.getUserByLogin(login);
        List<User> users = new ArrayList<>(1);
        users.add(user);

        return new ModelAndView("usersList", "users", users);

    }

    @RequestMapping("getUpdateForm")
    public ModelAndView launchUpdateForm(@RequestParam("id")String id, @RequestParam("login")String login, @RequestParam("name")String name) {

        LOGGER.debug("id : " + id + ", login : " + login + ", name: " + name);
        User user = new User();
        user.setUserId(Long.parseLong(id));
        user.setLogin(login);
        user.setName(name);

        return new ModelAndView("updateForm", "user", user);

    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("id")String id, @RequestParam("login")String login, @RequestParam("name")String name) {

        LOGGER.debug("id : " + id + ", login : " + login + ", name: " + name);
        User user = new User();
        user.setUserId(Long.parseLong(id));
        user.setLogin(login);
        user.setName(name);
        userService.updateUser(user);

        return "redirect:/usersList";

    }

    @RequestMapping("/removeUser")
    public String removeUser(@RequestParam("id") Long userId) {

        LOGGER.debug("userId: " + userId);
        userService.removeUser(userId);

        return "redirect:/usersList";
    }

}