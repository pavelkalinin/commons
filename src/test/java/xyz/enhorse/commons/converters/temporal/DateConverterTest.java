package xyz.enhorse.commons.converters.temporal;

import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static xyz.enhorse.commons.converters.temporal.TemporalData.*;

public class DateConverterTest {

    @Test
    public void convertToZoned() {
        final ZonedDateTime actual = new DateConverter(ZONE_ID).convert(DATE).orElse(null);
        assertNotNull(actual);
        assertEquals(ZONED.toInstant(), actual.toInstant());
    }


    @Test
    public void convertFromZoned() {
        final Date actual = new DateConverter().convert(ZONED).orElse(null);
        assertNotNull(actual);
        assertEquals(DATE.toInstant(), actual.toInstant());
    }
}