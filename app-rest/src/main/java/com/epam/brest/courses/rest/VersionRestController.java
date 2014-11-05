package com.epam.brest.courses.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by beast on 5.11.14. At 11.39
 */
@Controller
public class VersionRestController {


    @ResponseBody
    @RequestMapping(value = "/version", method = {RequestMethod.GET, RequestMethod.OPTIONS})
    public ResponseEntity<String> getVersion() {

        String version = "1.0";
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Allow","GET,OPTIONS");
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Access-Control-Allow-Methods", "GET, OPTIONS");
        responseHeaders.set("Access-Control-Allow-Headers", "Content-Type");
        responseHeaders.set("Access-Control-Max-Age", "86400");
        return new ResponseEntity<>(version, responseHeaders, HttpStatus.OK);

    }

}
