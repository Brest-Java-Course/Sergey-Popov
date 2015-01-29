package com.epam.brest.courses.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by beast on 22.11.14. At 13.41
 */
@RestController
@RequestMapping(value = "/rest")
public class HelloRestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> getDescription() {

        return new ResponseEntity<>("Task Manager is a simple sql-based application", HttpStatus.OK);

    }

}