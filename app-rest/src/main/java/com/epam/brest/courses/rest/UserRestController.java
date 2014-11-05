package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.User;
import com.epam.brest.courses.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by beast on 5.11.14. At 11.44
 */
@Controller
@RequestMapping("/users")
public class UserRestController {

    @Resource
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Long> addUser(@RequestBody User user) {
        Long id = userService.addUser(user);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        List users = userService.getUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("User not found for id=" + id + "error:"
            + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "login/{login}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
        User user = userService.getUserByLogin(login);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity removeUser(@PathVariable Long id) {
        userService.removeUser(id);
        return new ResponseEntity("", HttpStatus.OK);
    }

}