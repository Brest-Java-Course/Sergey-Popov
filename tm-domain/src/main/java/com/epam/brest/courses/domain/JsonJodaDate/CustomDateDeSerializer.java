package com.epam.brest.courses.domain.JsonJodaDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Created by beast on 27.11.14. At 14.01
 */
public class CustomDateDeSerializer extends JsonDeserializer<DateTime> {

    private static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String dateTimeAsString = jsonParser.getText().trim();
        return formatter.parseDateTime(dateTimeAsString);

    }
}