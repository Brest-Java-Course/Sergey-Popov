package com.epam.brest.courses.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 14.11.14. At 13.41
 */
public class RestClient {

    private static final Logger LOGGER = LogManager.getLogger();

    private String host;

    private RestTemplate restTemplate = new RestTemplate();

    public RestClient(String host) {

        this.host = host;
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

    }

    public RestTemplate getRestTemplate() {

        return restTemplate;

    }

    public void setRestTemplate(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;

    }

    public String getRestVersion() {

        LOGGER.debug("getRestVersion {}", host);

        return restTemplate.getForObject(host + "/version", String.class);

    }

}