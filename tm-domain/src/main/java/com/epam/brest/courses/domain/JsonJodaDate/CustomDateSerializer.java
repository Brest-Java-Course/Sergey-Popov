package com.epam.brest.courses.domain.JsonJodaDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.io.IOException;

/**
 * Created by beast on 27.11.14. At 13.54
 */
public class CustomDateSerializer extends JsonSerializer<DateTime> {

    /**
     *
     */
    private static DateTimeFormatter formatter = ISODateTimeFormat.dateTime();

    @Override
    public final void serialize(final DateTime dateTime,
                                final JsonGenerator jsonGenerator,
                                final SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeString(formatter.print(dateTime));
    }

}
