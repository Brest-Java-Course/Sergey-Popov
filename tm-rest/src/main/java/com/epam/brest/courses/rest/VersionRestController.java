package com.epam.brest.courses.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by beast on 22.11.14. At 12.46
 */
@RestController
@RequestMapping(value = "/rest")
public class VersionRestController {

    /**
     *
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public final ResponseEntity<String> getVersion() {

        return new ResponseEntity<>("1.0", HttpStatus.OK);

    }

}
