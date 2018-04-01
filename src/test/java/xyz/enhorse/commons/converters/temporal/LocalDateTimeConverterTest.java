package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class LocalDateTimeConverterTest {

    @Test
    public void convertToZoned() {
        final ZonedDateTime actual = new LocalDateTimeConverter(ZONE_ID).convert(LOCAL_DATE_TIME).orElse(null);
        assertNotNull(actual);
        assertEquals(ZONED.toInstant(), actual.toInstant());
    }


    @Test
    public void convertFromZoned() {
        final LocalDateTime actual = new LocalDateTimeConverter().convert(ZONED).orElse(null);
        assertNotNull(actual);
        assertEquals(LOCAL_DATE_TIME, actual);
    }
}