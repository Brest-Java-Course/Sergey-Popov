package com.epam.brest.courses.domain.JsonJodaDate;

import com.fasterxml.jackson.databind.module.SimpleModule;

import org.joda.time.DateTime;

/**
 * Created by beast on 27.11.14. At 14.09
 */
public class CustomDateTimeModule extends SimpleModule {

    /**
     *
     */
    public CustomDateTimeModule() {
        super();
        addSerializer(DateTime.class, new CustomDateSerializer());
        addDeserializer(DateTime.class, new CustomDateDeSerializer());
    }

}
