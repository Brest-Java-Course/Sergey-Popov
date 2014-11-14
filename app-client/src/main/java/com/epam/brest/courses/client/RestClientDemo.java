package com.epam.brest.courses.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by beast on 14.11.14. At 13.48
 */
public class RestClientDemo {

    private static final Logger LOGGER = LogManager.getLogger();

    public static void main(String[] args) {

        RestClient restClient = new RestClient("http://localhost:8080");
        String version = restClient.getRestVersion();
        LOGGER.debug("Rest version is {}", version);

    }

}
